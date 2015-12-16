package dronepackage;

import java.applet.Applet;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;


import dronepackage.Background;

public class StartingClass extends Applet implements Runnable, KeyListener {
   private Drone drone; 
   private Image image, character, background, badDroneImage;
   private URL base; //url allows us to use addresses (such as C:\\Users\\Desktop\\image1.jpg)
   private Graphics second;
   private static Background bg1, bg2;
   private BadDrone badDrone1, badDrone2;
   
	public static Background getBg1() {
	return bg1;
}

public static void setBg1(Background bg1) {
	StartingClass.bg1 = bg1;
}

public static Background getBg2() {
	return bg2;
}

public static void setBg2(Background bg2) {
	StartingClass.bg2 = bg2;
}

	public void init(){
	   setSize(800, 400);
	   setBackground(Color.BLACK);
	   setFocusable(true);
	   Frame frame = (Frame) this.getParent().getParent();
	   frame.setTitle("A Drone Game");
	   addKeyListener(this);
	   try{
		   base = getDocumentBase();
	   }catch(Exception e){
		   //handle exception
	   }
	   
	   //Image setups
	   character = getImage(base, "data/character.png");
	   background = getImage(base, "data/background.png");
	   badDroneImage = getImage(base, "data/heliboy.png");
   }
   
   public void start(){
	   
	   bg1 = new Background(0, 0);
	   bg2 = new Background(2160, 0);
	   badDrone1 = new BadDrone(340, 360);
	   badDrone2 = new BadDrone(700, 360);
	   drone = new Drone();
	   
	   Thread thread = new Thread(this);
	   thread.start();
   }
   
   public void stop(){
	   
   }
   
   public void destroy(){
	
   }
   @Override
	public void run() {
		// TODO Auto-generated method stub
	   while(true){
		   drone.update();
		   
		   java.util.ArrayList projectiles = drone.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				if (p.isVisible() == true) {
					p.update();
				} else {
					projectiles.remove(i);
				}
			}
		   badDrone1.update();
		   badDrone2.update();
		  
		   bg1.update();
		   bg2.update();
		   repaint();
		  
		   try{
			   Thread.sleep(17);
		   }catch (InterruptedException e){
			   e.printStackTrace();
		   }
		   
	   }
		
	}
   
 //the update method is used for double buffering. 
   @Override
	public void update(Graphics g) {
		// TODO Auto-generated method stub
		if (image == null){
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		
		second.setColor(getBackground());
		second.fillRect(0,  0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		
		g.drawImage(image, 0, 0 , this);
	}
   
   @Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
	    g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
	    g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
	    
	    java.util.ArrayList projectiles = drone.getProjectiles();
	    
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.YELLOW);
			g.fillRect(p.getX(), p.getY(), 10, 5);
		}

		g.drawImage(character, drone.getCenterX() - 61, drone.getCenterY() - 63, this);
	  
		g.drawImage(badDroneImage,  badDrone1.getCenterX() - 48, badDrone2.getCenterY(), this);
		g.drawImage(badDroneImage,  badDrone2.getCenterX() - 48, badDrone2.getCenterY(), this);
   }



@Override
public void keyPressed(KeyEvent e) {

    switch (e.getKeyCode()) {
    case KeyEvent.VK_UP:
        System.out.println("Move up");
        break;

    case KeyEvent.VK_DOWN:
        System.out.println("Move down");
        break;

    case KeyEvent.VK_LEFT:
    	
    	drone.moveLeft();
        break;

    case KeyEvent.VK_RIGHT:
    	drone.moveRight();
        break;

    case KeyEvent.VK_SPACE:
        System.out.println("Jump - no jumping though");
        break;
    case KeyEvent.VK_CONTROL:
		drone.shoot();
		break;

    }

}

@Override
public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
    case KeyEvent.VK_UP:
        System.out.println("Stop moving up");
        break;

    case KeyEvent.VK_DOWN:
        System.out.println("Stop moving down");
        break;

    case KeyEvent.VK_LEFT:
    	drone.stopLeft();
        break;

    case KeyEvent.VK_RIGHT:
    	drone.stopRight();
        break;

    case KeyEvent.VK_SPACE:
        System.out.println("Stop jumping - no jumping");
        break;

    }

}

@Override
public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

}
   
   
   
}
