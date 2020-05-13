/**
 * 
 */
package controlador;



public class Jugador {
	int puntos = 0;
	int puntosVida = 0;
	int nivel = 1;
	public int muertos = 0;
	public int vivos = 10;
	public boolean fin = false;

	
	public void addPuntos() {
		puntos = puntos + 20;
		
		
	}
	
	public int getMuertos() {
		return muertos;
	}
	
	
	public int getVivos() {
		return vivos;
	}
	
	public boolean todosMuertos() {
		return fin;
	}
	
	public void setFin() {
		fin = false;
	}
	
	public void setVivos(int cant) {
		this.vivos = cant;
		if(vivos == 0) {
			vivos =10;
		}
	}
	
	public void setMuertos(int cant) {
		this.muertos = cant;
		if(muertos == 10) {
			fin = true;
			muertos = 0;
		}
	}
	
	public void addPuntosNivel() {
		puntos = puntos + 100;
		puntosVida = puntosVida + 100;
		if(puntosVida >= 300) {
			puntosVida = 0;
		}
		
		}
		
	
		
	
	public void aumentarNivel() {
		nivel++;
		
	}
	
	
		
	
	public void setPtosVida() {
		puntosVida += 20;
		if(puntosVida >= 300) {
			puntosVida = 0;
		}
		
		
	}
	
	public int getPtosVida() {
		return puntosVida;
	}

	
	public int getNiveles() {
		return nivel;
	}
	
	
	public void setNiveles(int valor) {
		this.nivel = valor;
	}

	public int getPuntos() {
		return puntos;
	}

}
