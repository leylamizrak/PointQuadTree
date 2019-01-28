package prolab;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * Point Quadtree ile ilgili islemleri iceren siniftir.
 */

public class Agac {
	public int renk;// Dugumun rengini tutar.

	public static int nokta_say = 0;// Olusturulan herbir dugumu sayar.

	public int x;// Herbir dugumun koordinatlarini tutar.
	public int y;

	public int kacinci;// Dugumun kacinci dugum oldugunu tutar.

	public ArrayList<Integer> kac_x;// Sorgu dairesinin icine giren dugumlerin
								// koordinatlarini tutar.
	public ArrayList<Integer> kac_y;

	public Agac NE;// Herbir dugumun cocuklaridir.
	public Agac SE;
	public Agac NW;
	public Agac SW;

	public int k1, k2, k3, k4, k5, k6, k7, k8;// Herbir dugumun kose koordinatlarinin
										// tutulmasini saglar.
	public static int a1, a2, a3, a4, a5, a6, a7, a8, a9, a10;// Herbir dugumden onceki
														// dugumlerin koordinat
														// bilgilerini tutar.

	/**
	 * Agac sinifinin Constructor'idir.
	 * Aldigi parametrelere gore dugum ekler .
	 * Dugumun quadtrantlarini cizer.
	 * Dugumun kacinci dugum oldugunu ve renk degeri atar.
	 * @param x noktanin x kordiinati
	 * 
	 * @param y noktanin y koordinati
	 * 
	 * @param sayac hangi dugum
	 * 
	 * @param g Graphics degiskeni
	 */

	public Agac(int x, int y, int sayac, Graphics g) {

		kac_x = new ArrayList<Integer>();
		kac_y = new ArrayList<Integer>();

		nokta_say++;

		this.kacinci = nokta_say;

		this.renk = 0;

		this.x = x;
		this.y = y;

		this.NE = null;
		this.SE = null;
		this.NW = null;
		this.SW = null;

		if (sayac == 0) {// ilk dugum oldugunu belirtir.

			this.k1 = 64;
			this.k2 = 44;
			this.k3 = 576;
			this.k4 = 44;
			this.k5 = 64;
			this.k6 = 556;
			this.k7 = 576;
			this.k8 = 556;

		}

		else if (sayac == 1) {// NW taraftaki cocuk oldugunu belirtir.

			this.k1 = a1;
			this.k2 = a2;
			this.k3 = a9;
			this.k4 = a4;
			this.k5 = a1;
			this.k6 = a10;
			this.k7 = a9;
			this.k8 = a10;

		} else if (sayac == 2) {// SW taraftaki cocuk oldugunu belirtir.

			this.k1 = a1;
			this.k2 = a10;
			this.k3 = a9;
			this.k4 = a10;
			this.k5 = a5;
			this.k6 = a6;
			this.k7 = a9;
			this.k8 = a8;

		} else if (sayac == 3) {// NE taraftaki cocuk oldugunu belirtir.

			this.k1 = a9;
			this.k2 = a2;
			this.k3 = a3;
			this.k4 = a4;
			this.k5 = a9;
			this.k6 = a10;
			this.k7 = a3;
			this.k8 = a10;

		} else if (sayac == 4) {// SE taraftaki cocuk oldugunu belirtir.

			this.k1 = a9;
			this.k2 = a10;
			this.k3 = a3;
			this.k4 = a10;
			this.k5 = a9;
			this.k6 = a6;
			this.k7 = a7;
			this.k8 = a8;

		}

		g.setColor(Color.GRAY);// Quadrantlar cizilir.
		g.drawRect(this.k1, this.k2, this.k7 - this.k1, this.k8 - this.k2);
		g.drawRect(this.k1, this.k2, this.x - this.k1, this.y - this.k2);
		g.drawRect(this.x, this.y, this.k7 - this.x, this.k8 - this.y);

		g.setColor(Color.BLUE);

	}

	/**
	 * Aldigi koordinat degerlerine gore Point QuadTree'ye eleman ekler.
	 * 
	 * @param x noktanin x koordinati
	 * @param y   noktanin y koordinati
	 * @param g   Graphics degiskeni
	 * @return dugum
	 */ 
	public Agac ekle(int x, int y, Graphics g) {

		a1 = this.k1;
		a2 = this.k2;
		a3 = this.k3;
		a4 = this.k4;
		a5 = this.k5;
		a6 = this.k6;
		a7 = this.k7;
		a8 = this.k8;
		a9 = this.x;
		a10 = this.y;

		if (y == this.y && x < this.x) {
			if (this.NW == null)
				this.NW = new Agac(x, y, 1, g);

			else
				this.NW.ekle(x, y, g);

		}

		else if (y == this.y && x > this.x) {
			if (this.NE == null)
				this.NE = new Agac(x, y, 3, g);

			else
				this.NE.ekle(x, y, g);
		}

		else if (x == this.x && y < this.y) {
			if (this.NW == null)
				this.NW = new Agac(x, y, 1, g);

			else
				this.NW.ekle(x, y, g);

		} else if (x == this.x && y > this.y) {
			if (this.SW == null)
				this.SW = new Agac(x, y, 2, g);

			else

				this.SW.ekle(x, y, g);
		}

		else if (this.x > x && this.y > y) {
			if (this.NW == null)
				this.NW = new Agac(x, y, 1, g);

			else
				this.NW.ekle(x, y, g);

		}

		else if (this.x > x && this.y < y) {
			if (this.SW == null)
				this.SW = new Agac(x, y, 2, g);

			else

				this.SW.ekle(x, y, g);
		}

		else if (this.x < x && this.y > y) {
			if (this.NE == null)
				this.NE = new Agac(x, y, 3, g);

			else
				this.NE.ekle(x, y, g);
		}

		else if (this.x < x && this.y < y) {
			if (this.SE == null)
				this.SE = new Agac(x, y, 4, g);

			else
				this.SE.ekle(x, y, g);
		}

		return this;
	}

	/**
	 * Point Quadtree'deki dugumlerden sorgu dairesinin icinde olanlari listede tutar.
	 * @param root Point Quadtree'nin dugumu
	 * @param g Graphics degiskeni
	 * @param x sorgu dairesinin icindeki noktanin x degeri
	 * @param y sorgu dairesinin icindeki noktanin y degeri
	 * @param boyut sorgu dairesinin boyutu
	 */
	public void sorgu_yap(Agac root,Graphics g,int x,int y,int boyut)
	{
		
		if(root!=null)
		{
			boolean kontrol=false;
			
			int sayacx=0,sayacy=0;

			//Dugumun dikdortgeni ile sorgu dairesinin kesisip kesismedigi kontrol edilir.
			
			for(int i=x;i<=(x+boyut);i++)
			{
				for(int j=root.k1;j<=root.k3;j++)
				{
					if(i==j)
						sayacx=1;
				}
			}
			for(int i=y;i<=(y+boyut);i++)
			{
				for(int j=root.k2;j<=root.k6;j++)
				{
					if(i==j)
						sayacy=1;
				}
			}
			
			if(sayacx==1 && sayacy==1)//sorgu dairesi ile dugumun dikdortgeninin kesistigini gosterir.
				kontrol=true;

			if(kontrol==true)
			{
				int yaricap=boyut/2;
				
				int uzaklik=(int)(Math.sqrt(Math.pow(x+yaricap-root.x, 2)+Math.pow(y+yaricap-root.y, 2)));
				
				if(uzaklik<=yaricap)
				{
					root.renk=1;//Sorgu dairesinin icindeki dugumun renk odu degisir.
					g.setColor(Color.RED);
					g.fillOval(root.x-5, root.y-5, 10, 10);
	
					kac_x.add(root.x);
					kac_y.add(root.y);
					
				}
			
				//Sorgu dairesi ile dikdortgeni kesisen dugumun cocuklarinda ayni rekursif olarak kesisip kesismeme kontrolu yapilir.
				sorgu_yap(root.NE, g, x, y, boyut);
				sorgu_yap(root.NW, g, x, y, boyut);
				sorgu_yap(root.SE, g, x, y, boyut);
				sorgu_yap(root.SW, g, x, y, boyut);
				
				
			}

		}
		
	}
	
	/**
	 * Sorgu dairesi icinde olan noktalarin koordinatlarini ekrana siralanmis sekilde yazar.
	 * @param g Graphics degiskeni
	 */
	public void sirala(Graphics g) 
	{

		int dizi_x[] = new int[kac_x.size()];//Sorgu dairesinin icindeki noktalarin x koordinatlarini tutar.
		int dizi_y[] = new int[kac_x.size()];//Sorgu dairesinin icindeki noktalarin y koordinatlarini tutar.

		for (int i = 0; i < kac_x.size(); i++) {
			dizi_x[i] = kac_x.get(i);
			dizi_y[i] = kac_y.get(i);

		}
	
		int koord_y=75;
		
		kac_x.clear();//Koordinatlari tutan bagli liste tamamen temizlenir.
		kac_y.clear();
		
         
		int yedek,yedek2;
		
		for(int i=1;i<dizi_x.length;i++)//Dizilerde olan degerler once x'e eger degerler esitse y'ye gore siralanir.
		{
			for(int j=0;j<dizi_x.length-1;j++)
			{
				if(dizi_x[j]>dizi_x[j+1])
				{
					yedek=dizi_x[j];
					dizi_x[j]=dizi_x[j+1];
					dizi_x[j+1]=yedek;
					
					yedek2=dizi_y[j];
					dizi_y[j]=dizi_y[j+1];
					dizi_y[j+1]=yedek2;
				}
				else if(dizi_x[j]==dizi_x[j+1] && dizi_y[j]>dizi_y[j+1])
				{
					
					yedek=dizi_y[j];
					dizi_y[j]=dizi_y[j+1];
					dizi_y[j+1]=yedek;
				}
				
			}
			
			
		}
		
		
		g.setColor(Color.MAGENTA);
		
		g.drawString("Sorgu Dairesi Ýçindeki Nokta Sayýsý:"+dizi_x.length, 620, koord_y);
		
		koord_y+=15;
		
		for(int i=0;i<dizi_x.length;i++)//Noktalar frame'de gosterilir.
		{
				g.setColor(Color.BLACK);


				    g.drawString("("+dizi_x[i]+","+dizi_y[i]+")", 620, koord_y);
			          koord_y+=15;
				   
				
		         

				}
		
	
		kac_x=new ArrayList<Integer>();
		kac_y=new ArrayList<Integer>();
		
		dizi_x=null;//Diziler silinir.
		dizi_y=null;
		
		g.setColor(Color.BLUE);
		
	}
	
	/**
	 * Point Quadtree'deki  dugumlerin kacinci dugum olduklarini ekranda yazar.
	 * @param root Point Quadtree
	 * @param g Graphics degiskeni
	 */
	
	public void nokta_goruntule(Agac root,Graphics g)
	{
		
		g.setColor(Color.DARK_GRAY);
		
		if(root!=null)
		{	
			String str=""+root.kacinci;
						
            g.drawString(str, root.x+5, root.y+5);  
			
			nokta_goruntule(root.NE, g);
			nokta_goruntule(root.NW, g);
			nokta_goruntule(root.SE, g);
			nokta_goruntule(root.SW, g);

		}
		g.setColor(Color.BLUE);
 		
		nokta_say=0;
	}
	
	
/**
 * Point Quadtree'yi siler.
 * @param root Point QuadTree
 * @return dugum
 */
	public Agac  rootSil(Agac root)
	{
		if(root!=null){

		
		root.NE=rootSil(root.NE);
		root.NW=rootSil(root.NW);
		root.SE=rootSil(root.SE);
		root.SW=rootSil(root.SW);
	
		root=null;
	
		}

		return root;
	}
	
	/**
	 * Sorgu dairesi icinde olan noktalarin farkli renkte gorunmesini saglar.
	 * @param root PointQuadtre
	 * @param g Graphics degiskeni
	 */

	public void rootDolas(Agac root,Graphics g)
    {
    	if(root!=null)
    	{
    		if(root.renk==1)
    		{
    			g.setColor(Color.RED);
    			g.fillOval(root.x-5, root.y-5, 10, 10);
    		}
    		else if(root.renk==0)
    		{
    			g.setColor(Color.BLUE);
    			g.fillOval(root.x-5, root.y-5, 10, 10);
    		}
    		
    		g.setColor(Color.GRAY);
    		g.drawRect(root.k1, root.k2, root.k7-root.k1, root.k8-root.k2);
    		g.drawRect(root.k1, root.k2, root.x-root.k1, root.y-root.k2);
    		g.drawRect(root.x, root.y, root.k7-root.x, root.k8-root.y);
    		
    		g.setColor(Color.BLUE);
    		
    		rootDolas(root.NE,g);
    		rootDolas(root.NW,g);
    		rootDolas(root.SE,g);
    		rootDolas(root.SW,g);
    	}
    	
    }

}


