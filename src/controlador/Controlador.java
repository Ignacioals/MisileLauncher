/**
 * 
 */
package controlador;



public class Controlador {
	public Barco[] barcos;
	public Canon canon;
	public Misil misil;
	public Jugador juego;
	public Mira mira;
	
	public final int ALTURA = 820;
	public final int ANCHO = 700;
	public final int LIMITE = 49;
	
	private static Controlador instancia;
	
	public static Controlador getInstancia() {
		if(instancia == null) instancia = new Controlador();
		return instancia;
	}
	
	public void setup() {
	
		barcos = new Barco[10];
		setupBarcos();
		canon = new Canon();
		misil = new Misil();
		juego = new Jugador();
		mira = new Mira();
	}
	
	private void setupBarcos() {
		for(int i = 0; i <= 9; i++) {
			barcos[i] = new Barco();
		} 
	}

	
	public void setBarcosX(int x, int i) {
		barcos[i].setX(x);
	}
	
	//public void setBarcosY(int y, int i) {
		//barcos[i].setY(y);
	//}
	
	public int getBarcosX(int i) {
		return barcos[i].getX();
	}
	
	public int getBarcosY(int i) {
		return barcos[i].getY();
	}
	
	public void setMiraX(int x) {
		mira.setX(x);
	}
	
	public int getMiraX() {
		return mira.getX();
	}
	
	public int getMiraY() {
		return mira.getY();
	}
	
	public void setCanonX(int x) {
		canon.setX(x);
	}
	
	public void setCanonY(int y) {
		canon.setY(y);
	}
	
	public int getCanonX() {
		return canon.getX();
	}
	
	public int getCanonY() {
		return canon.getY();
	}
	
	public void setMisilX(int x) {
	    misil.setX(x);
	}
	
	public void setMisilY(int y) {
		misil.setY(y);
	}
	
	public int getMisilX() {
		return misil.getX();
	}
	
	public int getMisilY() {
		return misil.getY();
	}
	
	public int getCanonVidas() {
		return canon.getVidas();
	}
	
	public void dameAumentoNivel() {
		juego.aumentarNivel();
	}
	
	
	public void setCanonVidas(int i) {
		canon.setVida(i); 
	}
	
	public void setVivos(int i) {
		juego.setVivos(i);
	}
	
	public void setMuertos(int i) {
		juego.setMuertos(i);
	}
	
	public int getPuntos() {
		return juego.getPuntos();
	}
	
	public void setPuntos() {
		juego.addPuntos();
	}
	
	public boolean ImpactoEnemigo(int misilX, int misilY, int i) {
		return barcos[i].Impacto(misilX, misilY);
		
	}
	
	public int MoverBarcos(int i) {
		return barcos[i].MoverBarcos();
	}
	
	public void moverCanonIzquierda(){
		
		mira.moverIzquierda();
		
	}
	
	public void moverCanonDerecha(){
		
		
		mira.moverDerecha();
		
	}
	
	public int barcosMuertos() {
		return juego.getMuertos();
	}

	
	public boolean BarcoVivo(int i) {
		return barcos[i].sigueVivo();
	}
	
	public void matarBarco(int i) {
		barcos[i].muerto();
	}
	
	public int dameNivel() {
		return juego.getNiveles();
		}
	
	public void setNivel(int j) {
		juego.setNiveles(j);
	}
	
	public boolean todosMuertos() {
		return juego.todosMuertos();
	}
	
	public void setFin() {
		juego.setFin();
	}
	
	public int getVivos() {
		return juego.getVivos();
	}
	
	public void setPuntosVida() {
		juego.setPtosVida();
	}
	
	public int getPuntosVida() {
		return juego.getPtosVida();
	}
	
	public boolean NivelFinalizado() {
		boolean res = true;
		int vivos = 0 ;
		for(int i = 0; i <= 9; i++) {
			if(BarcoVivo(i) == true) {
				res = false;
				vivos++;
				
			}
		}
		if(res == true || vivos<5 || vivos == 0) {
			
			res = true;
		
		}
		
		return res;
	}
	
	public void setPuntosNivel() {
		juego.addPuntosNivel();
	}
	
	
}
