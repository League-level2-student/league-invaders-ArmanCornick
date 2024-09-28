import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font normalFont;
	Timer frameDraw;
	Rocketship rocket = new Rocketship(250,700,50,50);
	ObjectManager manager;
	BufferedImage space;
	Timer alienSpawn;
	

	GamePanel(){
		titleFont = new Font("Arial", Font.PLAIN, 48);
		normalFont = new Font("Arial", Font.PLAIN, 24);
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
		manager = new ObjectManager(rocket);
		try {
			space = ImageIO.read(this.getClass().getResourceAsStream("space.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void startGame(){
		alienSpawn = new Timer(1000 , manager);
	    alienSpawn.start();
	}

	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
			drawMenuState(g);
		}else if(currentState == GAME){
			drawGameState(g);
		}else if(currentState == END){
			drawEndState(g);
		}		
	}
	void updateMenuState() { 

	}
	void updateGameState() {  
		manager.update();
	}
	void updateEndState()  { 

	}
	void drawMenuState(Graphics g) {  
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont	);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 30, 100);
		g.setFont(normalFont);
		g.drawString("Press ENTER to start", 130, 350);
		g.drawString("Press SPACE for instructions", 100, 500);
	}
	void drawGameState(Graphics g) {  
		g.drawImage(space, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		manager.draw(g);
	}
	void drawEndState(Graphics g)  {  
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont	);
		g.setColor(Color.YELLOW);
		g.drawString("Game Over", 100, 100);
		g.setFont(normalFont);
		g.drawString("You killed enemies", 130, 350);
		g.drawString("Press ENTER to restart", 100, 500);g.setFont(titleFont);
		g.setColor(Color.YELLOW);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
			updateMenuState();
		}else if(currentState == GAME){
			updateGameState();
		}else if(currentState == END){
			updateEndState();
		}
		System.out.println("action");
		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
			} else {
				currentState++;
				if(currentState==GAME) {
					startGame();
				}
				if(currentState==END) {
				alienSpawn.stop();
				}
			}

		}   
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			if(rocket.y>0){
				rocket.up();	
			}
		}
		if (e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(currentState==GAME) {
				manager.addProjectile(rocket.getProjectile());
			}	
		}
		if (e.getKeyCode()==KeyEvent.VK_DOWN) {
			if(rocket.y<712){
				rocket.down();	
			}		}
		if (e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(rocket.x>0){
				rocket.left();	
			}		}
		if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(rocket.x<450){
				rocket.right();	
			}}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
