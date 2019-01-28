package prolab;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Pencereyi olusturur ve olusturulan MyPanel adli sinifi extends eder. 
 */

public class QuadTree  {

	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new QuadTree().gorunum();
			}
		});*/

		QuadTree nesne=new QuadTree();
		nesne.gorunum();
	}

	/**
	 * Pencereyi olusturur.
	 */

public void gorunum() {

		JFrame pencere = new JFrame();//Pencerenin olusumu
		
		//JLayeredPane lpane = new JLayeredPane();

		pencere.setTitle("                                                                            QUADTREE ÝLE NOKTA MODELÝ VE DAÝRESEL ARALIK SORGUSU");//Pencereyi isimlendirir.
		pencere.setBounds(400, 100, 900, 700);//Pencereyi konumlandirir.
		pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Kapatildiginda programi sonlandirir.

		//pencere.setPreferredSize(new Dimension(900, 700));

		//pencere.setLayout(new BorderLayout());
		//pencere.add(lpane, BorderLayout.CENTER);
		//lpane.setBounds(0, 0, 900, 700);

		MyPanel panel = new MyPanel();
		panel.setLayout(null);
		pencere.add(panel);//Pencereye panel ekler
		panel.setBackground(Color.PINK);//Panelin rengini degistirir.

		panel.setBounds(0, 0, 900, 700);//Panelin konumunu ayarlar.
		//panel.setOpaque(true);

		//lpane.add(panel, new Integer(0), 0);
		//pencere.pack();
		
		pencere.setLocationRelativeTo(null);//Açýlan pencerenin ortada olmasýný saglar.
		pencere.setVisible(true);//Pencerenin gorunur olmasini saglar.

	}

}
