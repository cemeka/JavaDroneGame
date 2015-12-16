package dronepackage;

public class Drone {
	  private int centerX = 100;
	  private int centerY = 180; 
	  private int speedX = 0;
	  private int speedY = 1;
	  
	  public void update(){
		  //Moves character accordingly
		  if (speedX == 0){
			System.out.println("Do not scroll to the background");
			
		  }else {
			  if (centerX <= 150){
				  centerX += speedX;
			  } else {
				  System.out.println("Scroll background here");
			  }
		  }
		  
		// Updates Y Position

				if (centerY + speedY >= 180) {
					centerY = 180;
				}else{                       
		                     centerY += speedY;
		        }
				

				// Prevents going beyond X coordinate of 0
				if (centerX + speedX <= 60) {
					centerX = 61;
				}
			}

	  
			public void moveRight() {
				speedX = 6;
			}

			public void moveUp(){
				//move up means that y decreases
				speedY = - 5;
			}
			
			public void moveDown(){
				//move down means that y increases
				speedY = +5;
			}

			public void stop() {
				speedX = 0;
			}

		}