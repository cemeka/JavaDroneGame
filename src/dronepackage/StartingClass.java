package dronepackage;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {
   private Drone drone; 
   private Image image, character;
   private URL base; //url allows us to use addresses (such as C:\\Users\\Desktop\\image1.jpg)
   private Graphics second;
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
   }
   
   public void start(){
	   super.start();
	   drone = new Drone();
	   
	   Thread thread = new Thread(this);
	   thread.start();
   }
   
   public void stop(){
	   super.stop();
   }
   
   public void destroy(){
	   super.destroy();
   }
   @Override
	public void run() {
		// TODO Auto-generated method stub
	   while(true){
		   drone.update();
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
		g.drawImage(character, drone.getCenterX() - 15, drone.getCenterY() - 15, this);
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
    	drone.stop();
        break;

    case KeyEvent.VK_RIGHT:
    	drone.stop();
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
