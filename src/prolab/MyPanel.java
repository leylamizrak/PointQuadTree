package prolab;


/**
 * Kullanilan java paketleri
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Progamin arayuz kismi ile ilgili islevleri yerine getirir.
 */

public class MyPanel extends JPanel implements ActionListener, MouseListener {

	public int x, y;// En son olusturulan noktalarin koordinatlarini tutar.

    public Agac root;// Point QuadTree'ye ait bilgileri tutar.

    public int sayac;// Arayuz islemlerinde kontrolu saglar.

	public int kontrol;// Random veya mouse ile olusturulan noktanin ilk defa
				// olusturulup olusturulmadigini kontrol eder.

    public int sil_kontrol;// Silme isleminin gerceklesip gerceklesmedigine ait durum
					// kontrolu yapar.

	public int boyut;// Sorgu dairesinin boyutlarini ayarlar.

	public int sorgu_indis;// Sorgu dairesinin indis degerlerini ayarlar.

	public ArrayList<Integer> listex;
    public ArrayList<Integer> listey;

	public ArrayList<Integer> sorgux;
    public ArrayList<Integer> sorguy;
	public ArrayList<Integer> buyukluk;

	int buton_kontrol;
	
	/**
	 * Olusturulan sinifa ait constructor'dir. Sinifa ait degiskenlere ilk
	 * degerler atanir.
	 */

	MyPanel() {

		kontrol = 0;

		sil_kontrol = 0;

		boyut = 30;

		root = null;

		sorgu_indis = 0;

		sayac = 0;

		listex = new ArrayList<Integer>();
		listey = new ArrayList<Integer>();

		sorgux = new ArrayList<Integer>();
		sorguy = new ArrayList<Integer>();
		buyukluk = new ArrayList<Integer>();

		addMouseListener(this);

	}

	/**
	 * Daha onceden olusturulmamis random 4 veya 5 nokta olusturur.
	 * @param g Graphics degiskeni
	 */

	public void rastgele(Graphics g) {
		int sayi = 0;

		while (true) {
			x = (int) (69 + Math.random() * 503);
			y = (int) (49 + Math.random() * 503);

			int m, n = 0;

			for (m = 0; m < listex.size(); m++) {
				if (x == listex.get(m) && y == listey.get(m)) {
					n = 1;
				}
			}

			if (n == 0)// Eðer n=0 ise daha onceden hic olusturulmamis
						// koordinatlar demektir.
			{
				listex.add(x);
				listey.add(y);

				g.setColor(Color.GREEN);
				g.fillOval(x - 5, y - 5, 10, 10);

				root = root.ekle(x, y, g);

				sayi++;

				if (sayi == 4 && kontrol == 0)
					break;

				else if (sayi == 5 && kontrol == 1)
					break;
			}

		}
	}

	/**
	 * Random olarak ilk noktalarin olusmasini saglar.
	 * @param g Graphics degiskeni
	 */
	public void rndNokta(Graphics g) {

		x = (int) (69 + Math.random() * 503);
		y = (int) (49 + Math.random() * 503);

		listex.add(x);
		listey.add(y);

		root = new Agac(x, y, 0, g);// Agacin ilk elemani olusturuluyor.

		g.setColor(Color.GREEN);
		g.fillOval(x - 5, y - 5, 10, 10);

		rastgele(g);
		root.nokta_goruntule(root, g);

		

	}

	/**
	 * Kullanici her seferinde random butonuna bastiginda random sayi uretir.
	 * @param g Graphics degiskeni
	 */
   public void rndNokta2(Graphics g) {

		g.setColor(Color.BLUE);

		root = new Agac(listex.get(0), listey.get(0), 0, g);
		g.fillOval(listex.get(0) - 5, listey.get(0) - 5, 10, 10);

		for (int i = 1; i < listex.size(); i++) {

			g.fillOval(listex.get(i) - 5, listey.get(i) - 5, 10, 10);

			root = root.ekle(listex.get(i), listey.get(i), g);
		}

		rastgele(g);

		g.setColor(Color.BLUE);
		root.nokta_goruntule(root, g);
	}

	/**
	 * Arayuzu olusturur ve sayac degerlerine gore gerekli islemleri yapar.
	 * @param g Graphics degiskeni
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.DARK_GRAY);

		g.fillRect(50, 30, 540, 540);
		g.fillRect(600, 30, 250, 540);
		g.setColor(Color.WHITE);
		g.fillRect(64, 44, 512, 512);
		g.fillRect(614, 44, 222, 512);
       
		buton_olustur();
		


		 if (sayac == 4)// Program kapanmasini saglayan kosuldur.
		{
			System.exit(0);
		}
		 else if (sil_kontrol == 0) {

			if (sayac == 1)// Random 5 tane sayi uretilmesini saglayan kosuldur.
			{
				
				if (kontrol == 0)// Ilk defa nokta olusturulacaksa calisacak
									// kosuldur.
				{
					rndNokta(g);
			
	  				 g.setColor(Color.WHITE);
					 g.fillRect(614, 44, 222, 20);

	  				 g.setColor(Color.BLUE);
					 g.drawString("Sorgu Dairesinin Yarýçapý:"+boyut/2, 620, 60);

					
					kontrol = 1;
				} else if (kontrol == 1)// Kullanici her seferinde random
										// butonuna tikladiginda olusturulacak
										// kisimdir.
				{
					rndNokta2(g);
					
	  				 g.setColor(Color.WHITE);
					 g.fillRect(614, 44, 222, 20);

	  				 g.setColor(Color.BLUE);
					 g.drawString("Sorgu Dairesinin Yarýçapý:"+boyut/2, 620, 60);

				}
			}

			else if (sayac == 2)// Mouse ile nokta uretilmesini saglayan
								// kosuldur.
			{
				
				if (kontrol == 0)// Ilk defa nokta olusturulacaksa calisacak
									// kosuldur.
				{
					g.setColor(Color.GREEN);
					g.fillOval(x - 5, y - 5, 10, 10);

					root = new Agac(x, y, 0, g);
					listex.add(x);
					listey.add(y);

					root.nokta_goruntule(root, g);

					g.setColor(Color.BLUE);

	  				 g.setColor(Color.WHITE);
					 g.fillRect(614, 44, 222, 20);

	  				 g.setColor(Color.BLUE);
					 g.drawString("Sorgu Dairesinin Yarýçapý:"+boyut/2, 620, 60);

					
					kontrol = 1;
				} else if (kontrol == 1)// Ilk defa nokta olusturulacaksa
										// calistirilacak olan kosuldur.
				{
					g.setColor(Color.BLUE);

					int n = 0;

					root = new Agac(listex.get(0), listey.get(0), 0, g);
					g.fillOval(listex.get(0) - 5, listey.get(0) - 5, 10, 10);

					if (listex.get(0) == x && listey.get(0) == y)
						n = 1;

					for (int i = 1; i < listex.size(); i++) {

						g.fillOval(listex.get(i) - 5, listey.get(i) - 5, 10, 10);
						root = root.ekle(listex.get(i), listey.get(i), g);

						if (listex.get(i) == x && listey.get(i) == y)
							n = 1;

					}

					if (n == 0)// Tiklanilan alanda olusturulacak olan noktanin
								// ilk defa olusacagini gosterir.
					{
						listex.add(x);
						listey.add(y);
						root = root.ekle(x, y, g);

						g.setColor(Color.GREEN);
						g.fillOval(x - 5, y - 5, 10, 10);

					}

				}

				root.nokta_goruntule(root, g);
				g.setColor(Color.BLUE);

				g.setColor(Color.DARK_GRAY);/// Tiklanilan noktanin ekrandan
											/// tasmasi durumunda yapilan
											/// kosuldur.
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(15));

				g.drawRect(57, 37, 526, 526);
				
 				 g.setColor(Color.WHITE);
				 g.fillRect(614, 44, 222, 20);

  				 g.setColor(Color.BLUE);
				 g.drawString("Sorgu Dairesinin Yarýçapý:"+boyut/2, 620, 60);

			}

			else if (sayac == 3)// Sorgu dairesi islemlerini iceren kosuldur.
			{
				rndNokta3(g);
				
 				 g.setColor(Color.WHITE);
				 g.fillRect(614, 44, 222, 20);

  				 g.setColor(Color.BLUE);
  				 g.drawString("Sorgu Dairesinin Yarýçapý:"+boyut/2, 620, 60);
				 
			}

			else if (sayac == 5)// Silme islemini gerceklestiren kosuldur.
			{

				try {
					
					root=root.rootSil(root);
					
				} catch (NullPointerException e) {

					
				}

				g.setColor(Color.WHITE);// Ekranin ilk haline donmesini saglayan
										// kisimdir.
				g.fillOval(64, 44, 512, 512);
				g.fillRect(614, 44, 222, 512);
				g.setColor(Color.BLUE);

				listex.clear();// Listelerin tamamen silinmesini saglayan
								// kisimdir.
				listey.clear();

				sil_kontrol = 1;

			}
			if (sayac != 3)// Sorgu butonu disinda baska bir butona
							// tiklandiginda sorgu koordinatlarinin silinmesini
							// saglayan kisimdir.
			{
				sorgux.clear();
				sorguy.clear();
				buyukluk.clear();

				sorgu_indis = 0;
				
 				 g.setColor(Color.WHITE);
				 g.fillRect(614, 44, 222, 20);

  				 g.setColor(Color.BLUE);
				 g.drawString("Sorgu Dairesinin Yarýçapý:"+boyut/2, 620, 60);


			}

		}

		else if (sil_kontrol == 1) {

			if (sayac == 1 || sayac == 2) {
				kontrol = 0;

				sorgux = new ArrayList<Integer>();
				sorguy = new ArrayList<Integer>();
				buyukluk = new ArrayList<Integer>();

				boyut = 30;
				sil_kontrol = 0;

				sorgu_indis = 0;
				
 				 g.setColor(Color.WHITE);
				 g.fillRect(614, 44, 222, 20);

  				 g.setColor(Color.BLUE);
  				 g.drawString("Sorgu Dairesinin Yarýçapý:"+boyut/2, 620, 60);
				 

			}

			repaint();
		}

	}

	/**
	 * Ekranda gorunen butonlari olusturur.
	 */
	public void buton_olustur() {

		JButton rnd_buton = new JButton(); // Random sayilari uretmeyi saglayan
											// buton

		this.add(rnd_buton);
		rnd_buton.setBounds(100, 590, 100, 50);

		rnd_buton.setText("random");
		rnd_buton.setBackground(Color.WHITE);

		rnd_buton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sayac = 1;

				repaint();
			}

		});

		JButton fare = new JButton();// Mouse islemleri ile ilgili buton
		this.add(fare);
		fare.setBackground(Color.WHITE);
		fare.setBounds(220, 590, 100, 50);
		fare.setText("mouse");

		fare.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sayac = 2;

			}

		});

		JButton sorgu_buton = new JButton();// Sorgu yapmayi saglayan buton

		this.add(sorgu_buton);
		sorgu_buton.setBounds(340, 590, 100, 50);
		sorgu_buton.setText("srg dairesi");
		sorgu_buton.setBackground(Color.WHITE);
		sorgu_buton.addActionListener(this);

		sorgu_buton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sayac = 3;

			}

		});

		JButton arti = new JButton();// Sorgu dairesinin boyutunu artirmayi
										// saglayan buton
		this.add(arti);
		arti.setBounds(460, 590, 100, 20);
		arti.setBackground(Color.WHITE);
		arti.setText("+");

		arti.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				boyut += 10;
				

				
				 Graphics g = getGraphics();

  				 g.setColor(Color.WHITE);
				 g.fillRect(614, 44, 222, 20);

  				 g.setColor(Color.BLUE);
				 g.drawString("Sorgu Dairesinin Yarýçapý:"+boyut/2, 620, 60);
				 

			}

		});

		JButton eksi = new JButton();// Sorgu dairesinin boyutunu azaltmayi
										// saglayan buton
		this.add(eksi);
		eksi.setBackground(Color.WHITE);
		eksi.setBounds(460, 620, 100, 20);
		eksi.setText("-");

		eksi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {


				
				Graphics g=getGraphics();
				
 				 g.setColor(Color.WHITE);
				 g.fillRect(614, 44, 222, 20);

 				 g.setColor(Color.BLUE);
				 g.drawString("Sorgu Dairesinin Yarýçapý:"+boyut/2, 620, 60);

				

				if (boyut >= 20)
					boyut -= 10;

                     
			}

		});

		JButton sil_buton = new JButton();// Silme islemini gerceklestiren buton

		this.add(sil_buton);
		sil_buton.setBounds(580, 590, 100, 50);
		sil_buton.setText("sil");
		sil_buton.setBackground(Color.WHITE);
		sil_buton.addActionListener(this);

		sil_buton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sayac = 5;

				repaint();
			}

		});

		JButton cikis_buton = new JButton();// Programin kapanmasini saglayan
											// buton

		this.add(cikis_buton);
		cikis_buton.setBounds(700, 590, 100, 50);
		cikis_buton.setText("çýkýþ");
		cikis_buton.setBackground(Color.WHITE);
		cikis_buton.addActionListener(this);

		cikis_buton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				sayac = 4;

				repaint();

			}

		});

	}
            
/**
 * Sorgu yapilmasi ile ilgili islemleri ve fonksiyonlari icerir.
 * @param g Graphics degiskeni
 */
    public void rndNokta3(Graphics g)
    {    	

 	if(kontrol!=0)
    	{
    	
    	root.rootDolas(root,g);
    	
    	root.nokta_goruntule(root, g);
    	
    	sorgu_ekle(g);
   
         if((x+(boyut/2)<=576 && (y+(boyut/2)<=556)) && (x-(boyut/2)>=64) && (y-(boyut/2)>=44)){
    		
    	sorgux.add(x);
    	sorguy.add(y);
    	buyukluk.add(boyut);
    	
    	g.setColor(Color.MAGENTA);//Sorgu dairesinin cember kalinligini ayarlar.
    	Graphics2D g2= (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
     
        g2.drawOval(x-boyut/2, y-boyut/2, boyut, boyut);
        
        root.sorgu_yap(root, g2,sorgux.get(sorgu_indis)-boyut/2,sorguy.get(sorgu_indis)-boyut/2, boyut);        
        
        root.sirala(g);
        
        sorgu_indis++;
        
         }
        
    	}
    }
   
    /**
     * Bagli listedeki sorgularin ekranda gorunmesi icin koordinatlarini yazar.
     * @param g Graphics degiskeni
     */

	public void sorgu_ekle(Graphics g) {
		for (int i = 0; i < sorgux.size(); i++) {
			g.setColor(Color.MAGENTA);// Sorgu dairesinin cember kalinligini
										// ayarlar.
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));

			g2.drawOval(sorgux.get(i) - buyukluk.get(i) / 2, sorguy.get(i) - buyukluk.get(i) / 2, buyukluk.get(i),
					buyukluk.get(i));

		}
	}

	/**
	 * Override edilmis bir fonksiyondur.
	 * Tiklanilan alanin koordinatlarini alir.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		x = e.getX();
		y = e.getY();

		if ((x >= 64 && x <= 576) && (y >= 44 && y <= 556)) 
		{
			//Tiklanilan alanin sorgu dairesinin icinde olup olmadiginin kontrolu yapiliyor.

			if (sayac == 3) {

				repaint();
			}

			else {

				sayac = 2;

				repaint();
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
    }

