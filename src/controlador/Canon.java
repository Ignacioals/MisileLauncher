/**
 * 
 */
package controlador;

public class Canon {

	public int x;
	public int y;
	public final int ALTURA = 800;
	public final static int ANCHO = 700;
	public final int LIMITE = 49;
	public int vidas = 3;
	

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getVidas() {
		return vidas;
	}
	
	public void setVida(int cant) {
		this.vidas = cant;
	}
	

	
	public int moverIzquierda(){
		 
		if(x + 1 < ANCHO - 100)
			x += 5;
		return x;
	}
	
	public int moverDerecha(){
		
		if(x > 1)
			x -= 5;
		return x;
	}



}
