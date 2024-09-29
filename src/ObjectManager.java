import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship rocket2;
	ArrayList<Projectile> projectiles;
	ArrayList<Alien> aliens;
	Random random = new Random();

	ObjectManager(Rocketship ship){
		rocket2 = ship;
		projectiles = new ArrayList<Projectile>();
		aliens = new ArrayList<Alien>();
	}

	void checkCollision() {
		for(int i=0;i<aliens.size();i++) {
			if(rocket2.collisionBox.intersects((aliens.get(i)).collisionBox)==true) {
				aliens.get(i).isActive=false;
			}
			if((aliens.get(i)).y>(LeagueInvaders.HEIGHT)) {
				(aliens.get(i)).isActive=false;	
			}
		}
		for(int q=0;q<projectiles.size();q++) {
			if((projectiles.get(q)).collisionBox.intersects((aliens.get(q)).collisionBox)==true) {
				projectiles.get(q).isActive=false;
			}
		}
	}

	void addProjectile(Projectile e) {
		projectiles.add(e);
	}
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}

	void update(){
		for(int i=0;i<aliens.size();i++) {
			(aliens.get(i)).update();
			if((aliens.get(i)).y>(LeagueInvaders.HEIGHT)) {
				(aliens.get(i)).isActive=false;
			}
			checkCollision();
			purgeObjects();
		}
		for(int i=0;i<projectiles.size();i++) {
			(projectiles.get(i)).update();
			if((projectiles.get(i)).y<0) {
				(projectiles.get(i)).isActive=false;
			}
		}
	}

	void draw(Graphics g) {
		rocket2.draw(g);
		for(int i=0;i<aliens.size();i++) {
			(aliens.get(i)).draw(g);
		}
		for(int i=0;i<projectiles.size();i++) {
			(projectiles.get(i)).draw(g);
		}
	}

	void purgeObjects() {
		for(int i=0;i<aliens.size();i++) {
			if((aliens.get(i)).isActive==false) {
				aliens.remove(i);
			}
		}
		for(int i=0;i<projectiles.size();i++) {
			if((projectiles.get(i)).y<0) {
				projectiles.remove(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
	}


}

