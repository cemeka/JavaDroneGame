package dronepackage;

import java.util.ArrayList;
import dronepackage.Background;
import dronepackage.StartingClass;

public class Drone {
	final int MOVESPEED = 5;
	  final int GROUND = 382;
	  
	  private int centerX = 100;
	  private int centerY = 382; 
	  private boolean movingLeft = false;
	  private boolean movingRight = false; 
	  
	  private int speedX = 0;
	  private int speedY = 1;
	  private static Background bg1 = StartingClass.getBg1();
	  private static Background bg2 = StartingClass.getBg2();
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>(5);
		
	  public int getCenterX() {
		return centerX;
	}


	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}


	public int getCenterY() {
		return centerY;
	}


	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}


	public int getSpeedX() {
		return speedX;
	}


	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}


	public int getSpeedY() {
		return speedY;
	}


	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void update(){
		  //Moves character or scrolls back accordingly
		  if (speedX < 0){
			  centerX += speedX;
		  }
		  
		  if (speedX < 0) {
	          centerX += speedX;
	      }
	      if (speedX == 0 || speedX < 0) {
	          bg1.setSpeedX(0);
	          bg2.setSpeedX(0);

	      }
	      if (centerX <= 200 && speedX > 0) {
	          centerX += speedX;
	      }
	      if (speedX > 0 && centerX > 200){
	          bg1.setSpeedX(-MOVESPEED);
	          bg2.setSpeedX(-MOVESPEED);
	      }
		  
		// Updates Y Position
	      centerY += speedY;
		  if (centerY + speedY >= GROUND) {
			centerY = GROUND;
		  }
				// Handles Jumping
				

				// Prevents going beyond X coordinate of 0
				if (centerX + speedX <= 60) {
					centerX = 61;
				}
			}
	
	public ArrayList getProjectiles(){
		return projectiles; 
	}
	public void shoot(){
		Projectile p = new Projectile(centerX + 50, centerY - 25);
		projectiles.add(p);
	}
	
	       public void moveLeft() {
		    speedX = -MOVESPEED;
	       }
	       
			public void moveRight() {
				speedX = MOVESPEED;
			}

			public void moveUp(){
				//move up means that y decreases
				speedY = - 5;
			}
			
			public void moveDown(){
				//move down means that y increases
				speedY = +5;
			}

			private void stop() {
		        if (isMovingRight() == false && isMovingLeft() == false) {
		            speedX = 0;
		        }

		        if (isMovingRight() == false && isMovingLeft() == true) {
		            moveLeft();
		        }

		        if (isMovingRight() == true && isMovingLeft() == false) {
		            moveRight();
		        }

		    }
			public void stopRight(){
				setMovingRight(false);
				stop();
			}
			public void stopLeft(){
				setMovingLeft(false);
				stop();
			}
			
			public boolean isMovingRight() {
		        return movingRight;
		    }

		    public void setMovingRight(boolean movingRight) {
		        this.movingRight = movingRight;
		    }

		    public boolean isMovingLeft() {
		        return movingLeft;
		    }

		    public void setMovingLeft(boolean movingLeft) {
		        this.movingLeft = movingLeft;
		    }

		}