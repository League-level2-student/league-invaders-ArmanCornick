import java.security.DomainCombiner;

import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame frame;
	GamePanel game;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	LeagueInvaders(){
		frame = new JFrame("Invaders");
		game = new GamePanel();
	}
	public static void main(String[] args) {
		LeagueInvaders league = new LeagueInvaders();
		league.setup();
	}
	void setup() {
		frame.add(game);
		frame.setSize(WIDTH,HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(game);
	}
}
