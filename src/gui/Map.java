package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import controlador.Controlador;
public class Map extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1763805918180286133L;
	private JLabel fondo, puntaje, vidas,puntajeVidas, muertos, restantes;
	private JLabel misil;
	private JLabel[] barcos; 
	private JLabel canon;
	private JLabel nivel;
	private JLabel mira;
	Color color2=new Color(1, 167, 229);
public Controlador c;
   
	JMenuBar barra;
	JMenu game;
	JMenu acercaDe;
	JMenuItem resetear, pausar, cerrar;
	JMenu niveles;
	JMenuItem integrantes, nivel1, nivel2, nivel3, nivel4, nivel5;
	
	public final int ALTURA = 820;
	public final int ANCHO = 700;
	public final int LIMITE = 49;
	
	Timer t;
	Timer score;
	Timer movMisil;
	Timer checkEnem;
	Timer spawn;
	Timer check;
	
	Container con;
	int cont2 = 0;
	int cont = 0;
	
	
	
	
	public Map() throws Exception{
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JOptionPane.showMessageDialog(null, "REGLAS\n"
				+ "1. Se apunta el cañon con las flechas IZQUIERDA y DERECHA. Se dispara con la flecha ESPACIO\n"
				+ "2. Para pasar de nivel y consecuentemente ganar el juego, hay que disparar y destruir al menos 5 barcos por nivel\n"
				+ "3. Si no se destruyen al menos 5 barcos de una serie, perdemos una vida\n"
				+ "4. Si tenemos 0 vidas y no se eliminan los 5 barcos, perdemos el juego\n"
				+ "5. Hay 5 niveles, cada uno más rápido que el otro.\n\n"
				+ "Buena suerte!");
		configurar();
		eventos();
		this.setTitle("LANZAMISILES");	
		this.setSize(ANCHO, ALTURA);
		this.setVisible(true);
		
		barra= new JMenuBar();
		this.setJMenuBar(barra);
		
		game=new JMenu();
		acercaDe=new JMenu();
		niveles= new JMenu();
		
		game.setText("Juego");
		acercaDe.setText("Acerca de...");
		barra.add(game);
		barra.add(acercaDe);
		
		resetear= new JMenuItem();
		cerrar= new JMenuItem();
		pausar= new JMenuItem();
		nivel1= new JMenuItem();
		nivel2= new JMenuItem();
		nivel3= new JMenuItem();
		nivel4= new JMenuItem();
		nivel5= new JMenuItem();
		
		integrantes= new JMenuItem();
		
		nivel1.setText("Nivel 1");
		nivel1.addActionListener(this);
		nivel2.setText("Nivel 2");
		nivel2.addActionListener(this);
		nivel3.setText("Nivel 3");
		nivel3.addActionListener(this);
		nivel4.setText("Nivel 4");
		nivel4.addActionListener(this);
		nivel5.setText("Nivel 5");
		nivel5.addActionListener(this);
		
		niveles.setText("Niveles");
		barra.add(niveles);
		niveles.add(nivel1);
		niveles.add(nivel2);
		niveles.add(nivel3);
		niveles.add(nivel4);
		niveles.add(nivel5);
		
		resetear.setText("Resetear");
		game.add(resetear);
		resetear.addActionListener(this);
				
		cerrar.setText("Cerrar");
		cerrar.addActionListener(this);
		game.add(cerrar);
		
		pausar.setText("Pausar");
		pausar.addActionListener(this);
		game.add(pausar);
		
		integrantes.setText("Integrantes");
		integrantes.addActionListener(this);
		acercaDe.add(integrantes);
		eventos();
	



}
	class CheckearNivel implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(c.NivelFinalizado()) {
				t.stop();
				score.stop();
				JFrame ganar = new JFrame();
				ganar.setBounds(200, 170, 200, 170);
				ganar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				JOptionPane.showMessageDialog(null, "PASASTE EL NIVEL! \n");
				//System.exit(DISPOSE_ON_CLOSE);
				if(c.dameNivel() == 1) {
					JOptionPane.showMessageDialog(null, "SUPERASTE EL NIVEL 1 \n"
							+ "SIGUIENTE: NIVEL 2");
					
				}
				if(c.dameNivel() == 2) {
					JOptionPane.showMessageDialog(null, "SUPERASTE EL NIVEL 2 \n"
							+ "SIGUIENTE: NIVEL 3");
				}
				if(c.dameNivel() == 3) {
					JOptionPane.showMessageDialog(null, "SUPERASTE EL NIVEL 3 \n"
							+ "SIGUIENTE: NIVEL 4");
				}
				if(c.dameNivel() == 4) {
					JOptionPane.showMessageDialog(null, "SUPERASTE EL NIVEL 4 \n"
							+ "SIGUIENTE: NIVEL 5");
				}
				if(c.dameNivel() == 5) {
					
					JOptionPane.showMessageDialog(null, "SUPERASTE EL NIVEL 5 \n");
					JOptionPane.showMessageDialog(null, "GANASTE EL JUEGO. FELICITACIONES! \n");
					System.exit(DISPOSE_ON_CLOSE);
					
				}
				/*con.setVisible(false);*/
				//this.setVisible(false);
				if(c.dameNivel() < 5) {
					c.setNivel(c.dameNivel() + 1);
					t = new Timer(20 / c.dameNivel(), new MoveX());
					if(c.dameNivel() == 2) {
						score = new Timer (25000, new CheckearNivel());
					}
					
					if(c.dameNivel() == 3) {
						score = new Timer (15000, new CheckearNivel());
					}
					
					if(c.dameNivel() == 4) {
						score = new Timer (15000, new CheckearNivel());
					}
					
					if(c.dameNivel() == 5) {
						score = new Timer (15000, new CheckearNivel());
						
					}
					score.start();
					spawn.stop();
					spawn = new Timer(2000/ c.dameNivel(), new CrearBarco());
					cont = 0;
					cont2 = 0;
					barcos = new  JLabel[10];
					prepararBarcos();
					spawn.start();
					t.start();
				
					c.setPuntosNivel();
					nivel.setText("NIVEL: " + c.dameNivel());
					if(c.getPuntos() >= 300)
					{
						c.setCanonVidas(c.getCanonVidas() + 1);
					    vidas.setText("VIDAS: " + c.getCanonVidas());
					}
					
				}
			
				
			}
			if((c.NivelFinalizado())== false) {
				t.stop();
				score.stop();
				c.setCanonVidas(c.getCanonVidas() - 1);
				JOptionPane.showMessageDialog(null, "PERDISTE UNA VIDA :( \n");
				
				if(c.dameNivel() < 5) {
					
					t = new Timer(20 / c.dameNivel(), new MoveX());
					if(c.dameNivel() == 2) {
						score = new Timer (25000, new CheckearNivel());
					}
					
					if(c.dameNivel() == 3) {
						score = new Timer (15000, new CheckearNivel());
					}
					
					if(c.dameNivel() == 4) {
						score = new Timer (15000, new CheckearNivel());
					}
					
					if(c.dameNivel() == 5) {
						score = new Timer (15000, new CheckearNivel());
						
					}
					score.start();
					spawn.stop();
					spawn = new Timer(2000/ c.dameNivel(), new CrearBarco());
					cont = 0;
					cont2 = 0;
					barcos = new  JLabel[10];
					prepararBarcos();
					spawn.start();
					t.start();
				
				}
			}
			
			if(c.getCanonVidas() < 0) {
				t.stop();
				JFrame perder = new JFrame();
				perder.setBounds(200, 170, 200, 170);
				perder.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				JOptionPane.showMessageDialog(null, "GAME OVER! \n");
				System.exit(DISPOSE_ON_CLOSE);
			}
			
			
			vidas.setText("VIDAS: " + c.getCanonVidas());
		}
		
	}
	
	public void actionPerformed(ActionEvent ee) {
		if(ee.getSource()==cerrar)
			System.exit(DISPOSE_ON_CLOSE);
		if(ee.getSource()==pausar) {
			t.stop();

			String avisoPausa= new String();
			avisoPausa+="JUEGO PAUSADO";
			JOptionPane.showMessageDialog(null, avisoPausa);
			t.start();

		}
		if(ee.getSource()==resetear) {
			this.setVisible(false);
			Map reseteo = null;
			
			try {
				reseteo = new Map();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reseteo.setVisible(true);
		}
		if(ee.getSource()==niveles) {
			JCheckBoxMenuItem nivel1 = new JCheckBoxMenuItem();
			//JMenuItem nivel2 = new JMenuItem();
			//JMenuItem nivel3 = new JMenuItem();
			//JMenuItem nivel4 = new JMenuItem();
			//JMenuItem nivel5 = new JMenuItem();
			nivel1.setText("Nivel 1");
			//nivel2.setText("Nivel 2");
			//nivel3.setText("Nivel 3");
			//nivel4.setText("Nivel 4");
			//nivel5.setText("Nivel 5");
			niveles.add(nivel1);
			//niveles.add(nivel2);
			//niveles.add(nivel3);
			//niveles.add(nivel4);
			//niveles.add(nivel5);
		}
		if(ee.getSource()==integrantes) {
			t.stop();
	
			String gente= new String();
			gente+="1. Ignacio López Scala (L.U.: 1090616)\n"
					+ "2. Micaela Esquerdo (L.U.: 1088570)\n"
					+ "3. Melanie Herszman (L.U.: 1098151 )";
					
			JOptionPane.showMessageDialog(null, gente);
			t.start();

		}
		if(ee.getSource()==nivel1) {
			t.stop();
	
			c.setNivel(1);
			t = new Timer(20 /c.dameNivel() , new MoveX());

			t.start();

		}
		if(ee.getSource()==nivel2) {
			t.stop();
			

			c.setNivel(2);
			t = new Timer(20 / c.dameNivel(), new MoveX());
			spawn.stop();
			spawn = new Timer(2000/ c.dameNivel(), new CrearBarco());
			
			spawn.start();
			t.start();
		
		}
		if(ee.getSource()==nivel3) {
			t.stop();
	
			c.setNivel(3);
			t = new Timer(20 / c.dameNivel(), new MoveX());
			spawn.stop();
			spawn = new Timer(2000 / c.dameNivel(), new CrearBarco());
			
			spawn.start();
			t.start();
	
		}
		if(ee.getSource()==nivel4) {
			t.stop();
			
			c.setNivel(4);
			t = new Timer(20 / c.dameNivel(), new MoveX());
			spawn.stop();
			spawn = new Timer(2000 / c.dameNivel(), new CrearBarco());
			
			spawn.start();
			t.start();
			
		}
		if(ee.getSource()==nivel5) {
			t.stop();
			
			c.setNivel(5);
			t = new Timer(20 / c.dameNivel(), new MoveX());
			spawn.stop();
			spawn = new Timer(2000 / c.dameNivel(), new CrearBarco());
			
			spawn.start();
			t.start();
		}
	}
	private void eventos() throws Exception {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addKeyListener(new ManejoTeclas());
		this.addKeyListener(new DisparoCanon());
		
		t.start();
		score.start();
		
	}
	private void configurar() {
		con = this.getContentPane();
		con.setLayout(null);
		con.setBackground(color2);
		c = new Controlador();
		c.setup();
		t = new Timer(20 / c.dameNivel(), new MoveX());
		movMisil = new Timer(1, new MovMisil());
		score = new Timer (45000, new CheckearNivel());
		checkEnem = new Timer(1, new ChequearImpacto());
		spawn = new Timer(2000, new CrearBarco());
		spawn.start();
		
		
		mira = new JLabel();
		prepararMira();
		canon = new JLabel();
		preprararCanon();
		fondo = new JLabel();
		fondo.setIcon(new ImageIcon("fondo.jpg"));
		fondo.setBounds(0, 10, ANCHO - 1, ALTURA - 1);
		misil = new JLabel();
		misil.setVisible(false);
		misil.setIcon(new ImageIcon("misil.png"));
		barcos = new  JLabel[10];
		puntaje = new JLabel();
		puntaje.setText("SCORE: " + c.getPuntos());
		puntaje.setForeground(Color.WHITE);
		puntaje.setBounds(0, 0, 100, 20);
		puntajeVidas = new JLabel();
		puntajeVidas.setText("1UP AT 300: " + c.getPuntosVida());
		puntajeVidas.setForeground(Color.WHITE);
		puntajeVidas.setBounds(240,0,120,20);
		nivel = new JLabel();
		nivel.setText("NIVEL: " + c.dameNivel());
		nivel.setForeground(Color.WHITE);
		nivel.setBounds(360,0,100,20);
		muertos = new JLabel();
		
		muertos.setForeground(Color.WHITE);
		muertos.setBounds(480,0,100,20);
		restantes = new JLabel();
		
		restantes.setForeground(Color.WHITE);
		restantes.setBounds(600,0,100,20);
		vidas = new JLabel();
		vidas.setText("VIDAS: " + c.getCanonVidas());
		vidas.setForeground(Color.WHITE);
		vidas.setBounds(120, 0, 100, 20);
		prepararBarcos();
		muertos.setText("MUERTOS: " + c.barcosMuertos());
		restantes.setText("VIVOS: " + c.getVivos());
		con.add(misil);
		con.add(canon);
		con.add(vidas);
		con.add(puntaje);
		con.add(mira);
		con.add(puntajeVidas);
		con.add(nivel);
		con.add(muertos);
		con.add(restantes);
		/*for(int x=0;x<=4;x++) {
		con.add(barcos[x]);
		}*/
		
		
		
		con.add(fondo);
		//checkEnem.start();
	
		
	}
	
	class CrearBarco implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(cont2 <10) {
			con.add(barcos[cont2]);
			cont2 ++;}
		}
	}
	
	private void prepararMira() {
		mira.setIcon(new ImageIcon("mira.png"));
		c.setMiraX(10);
		mira.setBounds(c.getMiraX(), c.getMiraY(), 50, 50);
	}
	
	private void preprararCanon() {
		canon.setIcon(new ImageIcon("canon.png"));
		c.setCanonX(ANCHO / 2 - 100);
		c.setCanonY(ALTURA - 200);
		canon.setBounds(c.getCanonX(),c.getCanonY(), 100, 100);
	}

	private void prepararBarcos() {
		int y = 0;
		int x = 0;
		int i = 0;
		for(i = 0 ; i<10; i++) {
		barcos[cont] = new JLabel();
		c.setBarcosX(x - 150, cont);
		x = x - 140;
		
		barcos[cont].setVisible(true);
		barcos[cont].setBounds(c.getBarcosX(cont),c.getBarcosY(cont), 50, 50);
		barcos[cont].setIcon(new ImageIcon("barco.png"));
		cont++;
		}
		x = 0;
	
	} 
	
	class MovMisil implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(c.getMisilY() < 10/*|| c.getMisilY() ==0 || c.getMisilY() == 11||c.getMisilY() == 9 ||c.getMisilY() == 10*/) {
				misil.setVisible(false);
				misil.setLocation(9999, 9999);
				movMisil.stop();
			} else {
				//int x = c.getMisilX();
				//int y = c.getMisilY();
				/*int angleRadians = (int) Math.atan2(c.getMiraY()-c.getCanonY(), c.getMiraX()-c.getCanonX()); // convert from degrees to radians
				x += Math.cos(angleRadians);
				y += Math.sin(angleRadians);
		        
				c.setMisilX(x);
				c.setMisilY(y);*/
		    	int curX = c.getMisilX();
		    	int curY = c.getMisilY();
		        int deltaX =  c.getMiraX()- c.getMisilX();
		        int deltaY = c.getMiraY()- c.getMisilY();
		        
		        double mag = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
		        deltaX = (int) (deltaX / mag * 5);
		        deltaY = (int) (deltaY / mag * 5);
		        curX += deltaX;
		        curY += deltaY;
		        c.setMisilX(curX);
		        c.setMisilY(curY);
		        
		        if(c.getMisilX() < 11)
		        {
		        	c.setMisilY(c.getMisilY() -5 );
		        }
		        
		        
				misil.setLocation(c.getMisilX(), c.getMisilY() );
				//System.out.println(c.getCanonY());
				//System.out.println(c.getMisilY());
				misil.setVisible(true);
			}
		}
		
	}
	
   
	class ChequearImpacto implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			for(int j = 0; j <= 9; j++) {
				if(c.ImpactoEnemigo(c.getMisilX(),c.getMisilY(),j)) {
					c.setBarcosX(c.getBarcosX(j) + 9999, j);
					//c.setBarcosY(c.getBarcosY(j) + 9999, j);
					barcos[j].setLocation(c.getBarcosX(j), c.getBarcosY(j));
					barcos[j].setVisible(false);
					c.setMisilX(9999);
					c.setMisilY(9999);
					misil.setVisible(false);
					movMisil.stop();
					c.matarBarco(j);
					c.setPuntos();
					c.setPuntosVida();
					
					c.setVivos(c.getVivos() - 1);
					c.setMuertos(c.barcosMuertos() + 1);
					
					
					if(c.getPuntosVida() >= 300)
					{
						c.setCanonVidas(c.getCanonVidas() + 1);
					    vidas.setText("VIDAS: " + c.getCanonVidas());
					}
					restantes.setText("VIVOS: " + c.getVivos());
					muertos.setText("MUERTOS: " + c.barcosMuertos());
					puntaje.setText("SCORE: " + c.getPuntos());
					puntajeVidas.setText("1UP AT 300: " + c.getPuntosVida());
				}
				/*if(c.getMisilY() == 9 ||c.getMisilY() == 10|| c.getMisilY() == 11) {
					c.setMisilX(9999);
					c.setMisilY(9999);
					misil.setVisible(false);
					movMisil.stop();
				}*/
				
			}
			if(c.todosMuertos()) {
				
				c.setFin();
				check.stop();
				t.stop();
				
				score.stop();
				JFrame ganar = new JFrame();
				ganar.setBounds(200, 170, 200, 170);
				ganar.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				JOptionPane.showMessageDialog(null, "PASASTE EL NIVEL! \n");
				//System.exit(DISPOSE_ON_CLOSE);
				if(c.dameNivel() == 1) {
					JOptionPane.showMessageDialog(null, "SUPERASTE EL NIVEL 1 \n"
							+ "SIGUIENTE: NIVEL 2");
					
				}
				if(c.dameNivel() == 2) {
					JOptionPane.showMessageDialog(null, "SUPERASTE EL NIVEL 2 \n"
							+ "SIGUIENTE: NIVEL 3");
				}
				if(c.dameNivel() == 3) {
					JOptionPane.showMessageDialog(null, "SUPERASTE EL NIVEL 3 \n"
							+ "SIGUIENTE: NIVEL 4");
				}
				if(c.dameNivel() == 4) {
					JOptionPane.showMessageDialog(null, "SUPERASTE EL NIVEL 4 \n"
							+ "SIGUIENTE: NIVEL 5");
				}
				if(c.dameNivel() == 5) {
					
					JOptionPane.showMessageDialog(null, "SUPERASTE EL NIVEL 5 \n");
					JOptionPane.showMessageDialog(null, "GANASTE EL JUEGO. FELICITACIONES! \n");
					System.exit(DISPOSE_ON_CLOSE);
					
				}
				/*con.setVisible(false);*/
				//this.setVisible(false);
				if(c.dameNivel() < 5) {
					c.setNivel(c.dameNivel() + 1);
					t = new Timer(20 / c.dameNivel(), new MoveX());
					if(c.dameNivel() == 2) {
						score = new Timer (25000, new CheckearNivel());
					}
					
					if(c.dameNivel() == 3) {
						score = new Timer (15000, new CheckearNivel());
					}
					
					if(c.dameNivel() == 4) {
						score = new Timer (15000, new CheckearNivel());
					}
					
					if(c.dameNivel() == 5) {
						score = new Timer (15000, new CheckearNivel());
						
					}
					score.start();
					spawn.stop();
					
					spawn = new Timer(2000/ c.dameNivel(), new CrearBarco());
					
					cont = 0;
					cont2 = 0;
					barcos = new  JLabel[10];
					prepararBarcos();
					spawn.start();
					t.start();
					
					c.setPuntosNivel();
					nivel.setText("NIVEL: " + c.dameNivel());
					if(c.getPuntos() >= 300)
					{
						c.setCanonVidas(c.getCanonVidas() + 1);
					    vidas.setText("VIDAS: " + c.getCanonVidas());
					}
				}
			}
		}
	}
		
	class MoveX implements ActionListener{
			
			int i = 0;
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i <= 9; i++) {
					c.setBarcosX(c.MoverBarcos(i), i);
					
					/*if(c.getBarcosX(i) == ANCHO - LIMITE || c.getBarcosX(i) == 1) {				
							for(int j = 0; j <= 4; j++) {
								//c.setBarcosY(c.getBarcosY(j) + 35, j);;
								if(c.barcos[j].i == 1) {
									c.barcos[j].i = -1;
								} else c.barcos[j].i = 1;
							}
					}*/
					
					barcos[i].setLocation(c.getBarcosX(i), c.getBarcosY(i));
				
				}
			}
			
		}
		
	class ManejoTeclas implements KeyListener{

			@Override
			public void keyTyped(KeyEvent e) { }

			
			public void keyReleased(KeyEvent e) { }

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 39){
					c.moverCanonIzquierda();
					mira.setLocation(c.getMiraX(), c.getMiraY());
				}
				else if(e.getKeyCode() == 37){
					c.moverCanonDerecha();
					mira.setLocation(c.getMiraX(), c.getMiraY());
				}
			}
		}
	class DisparoCanon implements KeyListener {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					if(!misil.isVisible()) {
						misil.setVisible(true);
						c.setMisilX(c.getCanonX() + 25);
						c.setMisilY(c.getCanonY());
						misil.setBounds(c.getMisilX(), c.getMisilY(), 32, 50);
						misil.setIcon(new ImageIcon("misil.png"));
						misil.setVisible(true);
						misil.setLocation(c.getCanonX() + 35, c.getCanonY() - 25);
						movMisil.start();
						check = new Timer(1, new ChequearImpacto());
						check.start();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				
				
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
			
		}



	
}
	

	
		


