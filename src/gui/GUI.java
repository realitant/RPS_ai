package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import main.RPS;

public class GUI extends JFrame {

	JFrame frame;

	private final ImageIcon ai_rock = new ImageIcon("C:\\Users\\McKay\\Documents\\STS_Workspaces\\Workspace0\\RPS_ai\\src\\resources\\ai_rock_big.png");
	private final ImageIcon ai_paper = new ImageIcon("C:\\Users\\McKay\\Documents\\STS_Workspaces\\Workspace0\\RPS_ai\\src\\resources\\ai_paper_big.png");
	private final ImageIcon ai_scissors = new ImageIcon("C:\\Users\\McKay\\Documents\\STS_Workspaces\\Workspace0\\RPS_ai\\src\\resources\\ai_scissors_big.png");
	private final ImageIcon rock_small = new ImageIcon("C:\\Users\\McKay\\Documents\\STS_Workspaces\\Workspace0\\RPS_ai\\src\\resources\\rock_small.png");
	private final ImageIcon paper_small = new ImageIcon("C:\\Users\\McKay\\Documents\\STS_Workspaces\\Workspace0\\RPS_ai\\src\\resources\\paper_small.png");
	private final ImageIcon scissors_small = new ImageIcon("C:\\Users\\McKay\\Documents\\STS_Workspaces\\Workspace0\\RPS_ai\\src\\resources\\scissors_small.png");
	private final ImageIcon rock_big = new ImageIcon("C:\\Users\\McKay\\Documents\\STS_Workspaces\\Workspace0\\RPS_ai\\src\\resources\\rock_big.png");
	private final ImageIcon paper_big = new ImageIcon("C:\\Users\\McKay\\Documents\\STS_Workspaces\\Workspace0\\RPS_ai\\src\\resources\\paper_big.png");
	private final ImageIcon scissors_big = new ImageIcon("C:\\Users\\McKay\\Documents\\STS_Workspaces\\Workspace0\\RPS_ai\\src\\resources\\scissors_big.png");



	private RPS game_engine = new RPS();
	private JLabel player_choice = new JLabel();
	private JLabel ai_choice = new JLabel();

	private JPanel text = new JPanel();
	private JLabel wins = new JLabel("PLAYER WINS: "+game_engine.getPlayer_wins());
	private JLabel draws = new JLabel("DRAWS: "+game_engine.getDraws());
	private JLabel losses = new JLabel("AI WINS: "+game_engine.getAi_wins());

	JPanel choices = new JPanel();

	JButton pick_rock = new JButton(rock_small);
    JButton pick_paper = new JButton(paper_small);
    JButton pick_scissors = new JButton(scissors_small);


	public GUI()
	{
		setBackground(new Color(255,255,255));
		//this doesn't work. not sure why

	    choices.setLayout(new GridLayout(3,0));

	    pick_rock.setBackground(new Color(255, 255, 255));
	    pick_rock.setBorder(BorderFactory.createEmptyBorder());
	    pick_paper.setBackground(new Color(255, 255, 255));
	    pick_paper.setBorder(BorderFactory.createEmptyBorder());
	    pick_scissors.setBackground(new Color(255, 255, 255));
	    pick_scissors.setBorder(BorderFactory.createEmptyBorder());

	    choices.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
	    choices.add(pick_rock);
	    choices.add(pick_paper);
	    choices.add(pick_scissors);


	    JPanel game = new JPanel();
	    game.setLayout(new GridLayout(0,2));

	    player_choice.setSize(300,300);
	    ai_choice.setSize(300, 300);



	    game.add(player_choice);
	    game.add(ai_choice);

	    game.setBorder(BorderFactory.createEmptyBorder(100,70,100,65));

	    pick_rock.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		player_choice.setIcon(rock_big);
	    		ai_choice.setIcon(getAiMove(game_engine.GUIplay('R')));
	    		wins.setText("PLAYER WINS: "+game_engine.getPlayer_wins());
	    		draws.setText("DRAWS: "+game_engine.getDraws());
	    		losses.setText("AI WINS: "+game_engine.getAi_wins());
	    	}
	    });
	    pick_paper.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		player_choice.setIcon(paper_big);
	    		ai_choice.setIcon(getAiMove(game_engine.GUIplay('P')));
	    		wins.setText("PLAYER WINS: "+game_engine.getPlayer_wins());
	    		draws.setText("DRAWS: "+game_engine.getDraws());
	    		losses.setText("AI WINS: "+game_engine.getAi_wins());
	    	}
	    });
	    pick_scissors.addActionListener(new ActionListener() {
	    	@Override
			public void actionPerformed(ActionEvent e) {
	    		player_choice.setIcon(scissors_big);
	    		ai_choice.setIcon(getAiMove(game_engine.GUIplay('S')));
	    		wins.setText("PLAYER WINS: "+game_engine.getPlayer_wins());
	    		draws.setText("DRAWS: "+game_engine.getDraws());
	    		losses.setText("AI WINS: "+game_engine.getAi_wins());
	    	}
	    });





	    text.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
	    text.setLayout(new GridLayout(0,3));
	    text.add(wins);
	    text.add(draws);
	    text.add(losses);



	    add(text,BorderLayout.NORTH);
	    add(choices,BorderLayout.WEST);
	    add(game,BorderLayout.CENTER);


	    setTitle("RPS");
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    setSize(1000,600);
	    setVisible(true);
	}

	private ImageIcon getAiMove(char move)
	{
		switch (move) {
		case 'R':
			return ai_rock;
		case 'P':
			return ai_paper;
		case 'S':
			return ai_scissors;
		default:
			return null;
		}
	}

	public static void main(String args[]){
	       GUI gui = new GUI();
	    }
}
