/**
 * 
 */
package controlador;



public class Misil {

	public final int ALTURA = 800;
	public final int ANCHO = 700;
	public final int LIMITE = 49;
	
	public int x;
	public int y;
	public boolean disparonave = false;
	
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	
	public void miDisparo() {
		disparonave = true;
	}
	
	public boolean esMiDisparo() {
		return disparonave;
	}
   
	
	

}
