package PrisonGame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Scrollbar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class GUI extends JFrame {

	JPanel contentPane;
	//panels
	private final JPanel panel = new JPanel();
	
	//labels
	private final JLabel label_select_ai = new JLabel("Select AI");
	private final JLabel label_your_decision = new JLabel("Your decision this round?");
	private final JLabel lblNewLabel_1 = new JLabel("Rounds Played");
	private final JLabel lblNewLabel_2 = new JLabel("Computer Strategy");
	private final JLabel lblNewLabel_3 = new JLabel("Player Sentence");
	private final JLabel lblNewLabel_4 = new JLabel("Computer Sentence");
	private final JLabel lblNewLabel_5 = new JLabel("Winner");
	
	//combo boxes
	private JComboBox comboBox_ai_select = new JComboBox();
	
	//buttons
	private final JButton button_start_new_game = new JButton("Start New Game");
	private final JButton button_silent = new JButton("Silent");
	private final JButton btnmodelify = new JButton("Testify");

	//text area
	private final static JTextArea textArea_game_text = new JTextArea();
	
	//text fields
	private JTextField textField_rounds_played;
	private JTextField textField_comp_strategy;
	private JTextField textField_player_sentence;
	private JTextField textField_comp_sentence;
	private final JTextField textField_winner = new JTextField();
	
	//Jlist
	private static DefaultListModel<GameStat> model = new DefaultListModel<>();  
	private final JList listBox_games_played = new JList<>(model);

	
	 //globals
	static PDGame game = new PDGame();
	static boolean start_new_game = false;
	static boolean game_running = true;
	static int selected_ai = 0;
	static int user_input = 0;
	static int round = 0;
	private final JScrollPane scrollPane = new JScrollPane();
	static List<GameStat> stats = new ArrayList<GameStat>();
	private final JScrollPane scrollPane_1 = new JScrollPane();
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 770);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel.setBackground(Color.ORANGE);
		panel.setBounds(10, 11, 708, 438);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		label_select_ai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_select_ai.setBounds(10, 11, 70, 22);
		panel.add(label_select_ai);
		
		String[] ai_strats = {"Computer Reads Strategy From Input File", "Tit-For-Tat", "Tit-For-Two-Tats", "Random Choice by Computer"};
		comboBox_ai_select = new JComboBox(ai_strats);
		comboBox_ai_select.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_ai_select.setBounds(90, 7, 392, 30);
		panel.add(comboBox_ai_select);
		button_start_new_game.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				start_new_game = true;
				game_running = true;
				 String selected = (String) comboBox_ai_select.getSelectedItem();
	                switch(selected) {
	                case "Computer Reads Strategy From Input File":
	                        selected_ai = 1;
	                    break;
	                case "Tit-For-Tat":
	                        selected_ai = 2;
	                    break;
	                case "Tit-For-Two-Tats":
	                        selected_ai = 3;
	                    break;
	                case "Random Choice by Computer":
	                        selected_ai = 4;
	                    break;
	                }
				try {
					start_game();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		button_start_new_game.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		button_start_new_game.setBounds(495, 7, 196, 31);
		panel.add(button_start_new_game);
		
		label_your_decision.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_your_decision.setBounds(249, 55, 200, 22);
		panel.add(label_your_decision);
		button_silent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(game_running == true) {
					user_input = 1;
					run_round();
				}
			}
		});
		button_silent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_silent.setBounds(249, 88, 95, 31);
		
		panel.add(button_silent);
		btnmodelify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game_running == true) {
					user_input = 2;
					run_round();
				}
			}
		});
		btnmodelify.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnmodelify.setBounds(354, 88, 108, 31);
		
		panel.add(btnmodelify);
		scrollPane.setBounds(10, 130, 681, 297);
		
		panel.add(scrollPane);
		textArea_game_text.setWrapStyleWord(true);
		textArea_game_text.setEditable(false);
		scrollPane.setViewportView(textArea_game_text);
		

		textArea_game_text.setLineWrap(true);
		textArea_game_text.setFont(new Font("Monospaced", Font.PLAIN, 18));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(10, 460, 708, 260);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("List of Games");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 108, 22);
		panel_1.add(lblNewLabel);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(391, 43, 116, 22);
		
		panel_1.add(lblNewLabel_1);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(359, 84, 148, 22);
		
		panel_1.add(lblNewLabel_2);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(383, 123, 124, 22);
		
		panel_1.add(lblNewLabel_3);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(353, 162, 154, 22);
		
		panel_1.add(lblNewLabel_4);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(452, 201, 55, 22);
		
		panel_1.add(lblNewLabel_5);
		
		textField_rounds_played = new JTextField();
		textField_rounds_played.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_rounds_played.setBounds(517, 41, 181, 28);
		panel_1.add(textField_rounds_played);
		textField_rounds_played.setColumns(10);
		
		textField_comp_strategy = new JTextField();
		textField_comp_strategy.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_comp_strategy.setColumns(10);
		textField_comp_strategy.setBounds(517, 82, 181, 28);
		panel_1.add(textField_comp_strategy);
		
		textField_player_sentence = new JTextField();
		textField_player_sentence.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_player_sentence.setColumns(10);
		textField_player_sentence.setBounds(517, 121, 181, 28);
		panel_1.add(textField_player_sentence);
		
		textField_comp_sentence = new JTextField();
		textField_comp_sentence.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_comp_sentence.setColumns(10);
		textField_comp_sentence.setBounds(517, 160, 181, 28);
		panel_1.add(textField_comp_sentence);
		textField_winner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_winner.setColumns(10);
		textField_winner.setBounds(517, 199, 181, 28);
		
		panel_1.add(textField_winner);
		scrollPane_1.setBounds(10, 44, 333, 183);
		
		panel_1.add(scrollPane_1);
		scrollPane_1.setViewportView(listBox_games_played);
		listBox_games_played.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_rounds_played.setText("5");
				textField_comp_strategy.setText(((GameStat) listBox_games_played.getSelectedValue()).get_AI());
				textField_player_sentence.setText(((GameStat) listBox_games_played.getSelectedValue()).get_player_years());
				textField_comp_sentence.setText(((GameStat) listBox_games_played.getSelectedValue()).get_comp_years());
				textField_winner.setText(((GameStat) listBox_games_played.getSelectedValue()).get_winner());
			}
		});
		listBox_games_played.setFont(new Font("Tahoma", Font.PLAIN, 18));
	}
	
	public static void start_game() throws FileNotFoundException {	
		textArea_game_text.setText("");
		textArea_game_text.append("\n What is your decision?");
		 game = new PDGame(selected_ai);
		 round = 0;
		 game_running = true;
	}
	
	public static void run_round() {
		if(round < 5) {
		textArea_game_text.append("\n Round: " + (round + 1) + "/5");
    	game.get_user_input(user_input, round);
    	textArea_game_text.append(game.play_round(round));
    	textArea_game_text.append("\n ...");
    	round ++;
		}
		if(round == 5){
			textArea_game_text.append("\n\nGame Over");
			textArea_game_text.append("\nYour prison sentence is: " + game.getUser_years());
			textArea_game_text.append("\nYour partners sentence is: " + game.getAI_years());
			textArea_game_text.append("\nClick Start new game to play again");
			stats.add(game.generate_stats());
			load_played_games();
			game_running = false;
		}
	}
	
	public static void load_played_games() {
			model.clear();
			for(int i = 0; i < stats.size(); i ++) {
				model.addElement(stats.get(i));
			}
			
			//listBox_games_played = new JList<>(model);
		}

}
