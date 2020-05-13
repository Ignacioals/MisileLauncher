/**
 * 
 */
package controlador;


public class Barco {
	public int x = 0;
	public final int y = 11;
	public boolean vivo = true;
	public Misil misil;
	
	
	public final int ALTURA = 800;
	public final int ANCHO = 700;
	public final int LIMITE = 49;
		
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	
	public void setX(int x){
		this.x = x;
	}
	
	//public void setY(int y) {
	//	this.y = y;
	//}
	
	public int i = 1;
	public int MoverBarcos(){
		/*if(x == ANCHO - LIMITE){
			i = -1;
		}*/
		if(x == 1) {
			i = 1;
		}
		x = x + i;
		return x;
		
	}
	
	public boolean Impacto(int misilX, int misilY) {
		boolean res = false;
		//System.out.println(misilX);
		for (int i = x; i <= x + 50; i++) {
			
			if(misilX == i || misilX + 16 == i) {
				if(misilY == y + 50|| misilY == y + 26|| misilY == y + 13|| misilY == y )
					res = true;
			}
		}
	return res;
	}
	
	
	
	public void muerto() {
		vivo = false;
		
	}
	
	public boolean sigueVivo() {
		return vivo;
	}
}
