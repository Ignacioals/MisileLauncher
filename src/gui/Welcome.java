package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gui.Welcome.Botonstart;

public class Welcome extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9066286418824705054L;
	Container w;
	public Welcome() {
		w = getContentPane();
		this.setLayout(null);
		this.setSize(550, 300);
		this.setLocationRelativeTo(null);
		w.setBackground(Color.BLACK);
		this.setTitle("Lanzamisiles©™");
		
		JButton start;
		JLabel titulo;
		
		titulo= new JLabel();
		titulo.setIcon(new ImageIcon("logo.jpg"));
		titulo.setBounds(120, 15 , 300, 125); 

		start= new JButton();
		start.setText("START");
		start.setBounds(230, (this.getHeight()-start.getHeight())/2, 75, 50);
		start.addActionListener(new Botonstart());
		start.setBackground(Color.BLACK);

		
		
		
		w.add(start);
		w.add(titulo);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}


		class Botonstart implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					new Map().setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
}
