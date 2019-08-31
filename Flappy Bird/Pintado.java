import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Pintado extends JPanel implements ActionListener,KeyListener{
    Movement move;
    boolean subir,comenzar;
    AudioClip intro,volar;
    JLabel flappys[],pins[],backers[],tierra[] , imperialdramon[],lugia[];
    JLabel getready;
    BufferedImage birds[], pipes[], fondos[], ground , imperial[],lugi[];
    BufferedImage init,play,share,reinit;
    BufferedImage spriteSheet=null,spriteSheet1=null,spriteSheet2=null;
    private BufferedImage getSprite(BufferedImage spriteSheet, int i, int j, 
         int width, int height){      
    return  spriteSheet.getSubimage(j , i , width, height);
    }
    int longi,scoreint,electo=0;
    Thread hilo;
    Thread hilo2;
    JButton reiniciar;
    JButton jugar;
    JButton logg;
    JButton escoge;
    JLabel score;
    Rectangle2D rect;
    Font fuente;
    JFrame ventana;
    String name;
    Escoge esc;
    JFrame frm2;
    
    public Pintado(JFrame frm){
        comenzar=false;
        
        intro=java.applet.Applet.newAudioClip(getClass().getResource("intro.wav"));
        volar=java.applet.Applet.newAudioClip(getClass().getResource("sfx_wing.wav"));
        try {   
       spriteSheet = ImageIO.read(getClass().getResource("Flappy-Graphics.png")); 
       spriteSheet1 = ImageIO.read(getClass().getResource("imperialdramon1.png"));
       spriteSheet2 = ImageIO.read(getClass().getResource("Lugia1.png"));
       
        }catch(IOException e){  System.out.println("Imagen No Encontrada"); }
        
        addKeyListener(this);
        
        scoreint=0;
        fuente = new Font("Arial",Font.BOLD,32);
        score = new JLabel(""+scoreint);
        score.setBounds(120,50,100,50);
        score.setFont(fuente);
        score.setForeground(Color.WHITE);
        add(score);
        score.setVisible(false);
        
        frm2=new JFrame("Escoje un personaje");
        esc=new Escoge(frm2);
        frm2.setVisible(false);


        subir=false;
        lugi = new BufferedImage[12];
        imperial = new BufferedImage[8];
        fondos = new BufferedImage[2]; 
        birds = new BufferedImage[3];  
        pipes = new BufferedImage[6];
        
        imperialdramon = new JLabel[8];
        backers = new JLabel[2];
        flappys = new JLabel[3];
        pins = new JLabel[6];
        tierra=new JLabel[2];
        lugia = new JLabel[12];

        fondos[0] =getSprite(spriteSheet,0, 0, 290, 512);  //dia
        
        imperial [7] = getSprite(spriteSheet1,113,596 , 71, 85);
        imperial [6] = getSprite(spriteSheet1,124,524 ,71 , 74);
        imperial [5] = getSprite(spriteSheet1,146, 446,75 , 52);
        imperial [4] = getSprite(spriteSheet1,146, 371,74 , 52);
        imperial [3] = getSprite(spriteSheet1,146, 297,70 , 63);
        imperial [2] = getSprite(spriteSheet1,146, 224,70 , 52);
        imperial [1] = getSprite(spriteSheet1,134, 148, 71, 64);
        imperial [0] = getSprite(spriteSheet1,125, 70,75 , 73);

        lugi [0] = getSprite(spriteSheet2,116, 94,80 , 74);
        lugi [1] = getSprite(spriteSheet2,120, 175,87 , 70);
        lugi [2] = getSprite(spriteSheet2,125, 263,95 ,65 );
        lugi [3] = getSprite(spriteSheet2,142, 360,102 , 48);
        lugi [4] = getSprite(spriteSheet2,190, 102,92 , 48);
        lugi [5] = getSprite(spriteSheet2,190, 197,81 , 52);
        lugi [6] = getSprite(spriteSheet2,190, 282,84 ,55 );
        lugi [7] = getSprite(spriteSheet2,190, 197,81 , 52);
        lugi [8] = getSprite(spriteSheet2,190, 102,92 , 48);
        lugi [9] = getSprite(spriteSheet2,142, 360,102 , 48);
        lugi [10] = getSprite(spriteSheet2,125, 263,95 ,65 );
        lugi [11] = getSprite(spriteSheet2,120, 175,87 , 70);

        birds [0] =getSprite(spriteSheet,750, 220, 50, 50);//alas arriba
        birds [1] =getSprite(spriteSheet,800, 220, 50, 50);//alas enmedio
        birds [2] =getSprite(spriteSheet,850, 220, 50, 50);//alas abajo
        
        pipes[0] =getSprite(spriteSheet,640, 0, 55, 210);
        pipes[1] =getSprite(spriteSheet, 760, 110, 55, 210);
        init = getSprite(spriteSheet,115,590,200,50);
        ground =getSprite(spriteSheet,0,580,400,100);
        play =getSprite(spriteSheet,230,650,220,100);
        share =getSprite(spriteSheet,270,535,220,50);
        reinit =getSprite(spriteSheet,170,540,200,50);
        
        getready=new JLabel(new ImageIcon(init));
        getready.setBounds(40,110,200,50);
        add(getready);
        
        logg=new JButton(new ImageIcon(share));
        logg.setBounds(70,280,125,30);
        add(logg);
        logg.setBorderPainted(false);
        logg.setOpaque(false);
        logg.setContentAreaFilled(false);
        logg.addActionListener(this);
        
        escoge=new JButton("Escoge Personaje");
        escoge.setBounds(45,380,170,30);
        add(escoge);
        escoge.addActionListener(this);

        reiniciar = new JButton(new ImageIcon(reinit));
        reiniciar.setBounds(170,420,50,55);
        add(reiniciar);
        reiniciar.addActionListener(this);
        reiniciar.setBorderPainted(false);
        reiniciar.setOpaque(false);
        reiniciar.setContentAreaFilled(false);
        reiniciar.setVisible(false);
        
        jugar=new JButton(new ImageIcon(play));
        jugar.setBounds(70,180,110,100);
        add(jugar);
        jugar.setBorderPainted(false);
        jugar.setOpaque(false);
        jugar.setContentAreaFilled(false);
        jugar.addActionListener(this);
        
        tierra[0]=new JLabel(new ImageIcon(ground));
        tierra[0].setBounds(0,300,270,300);
        add(tierra[0]);
        tierra[1]=new JLabel(new ImageIcon(ground));
        tierra[1].setBounds(264,300,270,300);
        add(tierra[1]);
        
        flappys[0]=new JLabel(new ImageIcon(birds[0]));
        flappys[0].setBounds(20,250,30,30);
        add(flappys[0]);
        flappys[0].setVisible(false);
        flappys[1]= new  JLabel(new ImageIcon(birds[1]));
        add(flappys[1]);
        flappys[1].setBounds(20,250,30,30);
        add(flappys[1]);
        flappys[1].setVisible(false);
        flappys[2]= new  JLabel(new ImageIcon(birds[2]));
        add(flappys[2]);
        flappys[2].setBounds(20,250,30,30);
        add(flappys[2]);
        flappys[2].setVisible(false);
        
        imperialdramon[0] = new JLabel (new ImageIcon (imperial[0]));
        imperialdramon[0].setBounds(20,250,60,60);
        add(imperialdramon[0]);
        imperialdramon[0].setVisible(false);
        imperialdramon[1] = new JLabel (new ImageIcon (imperial[1]));
        imperialdramon[1].setBounds(20,250,60,60);
        add(imperialdramon[1]);
        imperialdramon[1].setVisible(false);
        imperialdramon[2] = new JLabel (new ImageIcon (imperial[2]));
        imperialdramon[2].setBounds(20,250,60,60);
        add(imperialdramon[2]);
        imperialdramon[2].setVisible(false);
        imperialdramon[3] = new JLabel (new ImageIcon (imperial[3]));
        imperialdramon[3].setBounds(20,250,60,60);
        add(imperialdramon[3]);
        imperialdramon[3].setVisible(false);
        imperialdramon[4] = new JLabel (new ImageIcon (imperial[4]));
        imperialdramon[4].setBounds(20,250,60,60);
        add(imperialdramon[4]);
        imperialdramon[4].setVisible(false);
        imperialdramon[5] = new JLabel (new ImageIcon (imperial[5]));
        imperialdramon[5].setBounds(20,250,60,60);
        add(imperialdramon[5]);
        imperialdramon[5].setVisible(false);
        imperialdramon[6] = new JLabel (new ImageIcon (imperial[6]));
        imperialdramon[6].setBounds(20,250,60,60);
        add(imperialdramon[6]);
        imperialdramon[6].setVisible(false);
        imperialdramon[7] = new JLabel (new ImageIcon (imperial[7]));
        imperialdramon[7].setBounds(20,250,60,60);
        add(imperialdramon[7]);
        imperialdramon[7].setVisible(false);
        
    
        lugia[0] = new JLabel (new ImageIcon (lugi[0]));
        lugia[0].setBounds(20,250,100,70);
        add(lugia[0]);
        lugia[0].setVisible(false);
        lugia[1] = new JLabel (new ImageIcon (lugi[1]));
        lugia[1].setBounds(20,250,100,70);
        add(lugia[1]);
        lugia[1].setVisible(false);
        lugia[2] = new JLabel (new ImageIcon (lugi[2]));
        lugia[2].setBounds(20,250,100,70);
        add(lugia[2]);
        lugia[2].setVisible(false);
        lugia[3] = new JLabel (new ImageIcon (lugi[3]));
        lugia[3].setBounds(20,250,100,70);
        add(lugia[3]);
        lugia[3].setVisible(false);
        lugia[4] = new JLabel (new ImageIcon (lugi[4]));
        lugia[4].setBounds(20,250,100,70);
        add(lugia[4]);
        lugia[4].setVisible(false);
        lugia[5] = new JLabel (new ImageIcon (lugi[5]));
        lugia[5].setBounds(20,250,100,70);
        add(lugia[5]);
        lugia[5].setVisible(false);
        lugia[6] = new JLabel (new ImageIcon (lugi[6]));
        lugia[6].setBounds(20,250,100,70);
        add(lugia[6]);
        lugia[6].setVisible(false);
        lugia[7] = new JLabel (new ImageIcon (lugi[7]));
        lugia[7].setBounds(20,250,100,70);
        add(lugia[7]);
        lugia[7].setVisible(false);
        lugia[8] = new JLabel (new ImageIcon (lugi[8]));
        lugia[8].setBounds(20,250,100,70);
        add(lugia[8]);
        lugia[8].setVisible(false);
        lugia[9] = new JLabel (new ImageIcon (lugi[9]));
        lugia[9].setBounds(20,250,100,70);
        add(lugia[9]);
        lugia[9].setVisible(false);
        lugia[10] = new JLabel (new ImageIcon (lugi[10]));
        lugia[10].setBounds(20,250,100,70);
        add(lugia[10]);
        lugia[10].setVisible(false);
        lugia[11] = new JLabel (new ImageIcon (lugi[11]));
        lugia[11].setBounds(20,250,100,70);
        add(lugia[11]);
        lugia[11].setVisible(false);
        
        


        pins[0]=new JLabel(new ImageIcon(pipes[0]));
        pins[0].setBounds(510,300,55,210);
        add(pins[0]);//primer tubo rojo
        pins[1]=new JLabel(new ImageIcon(pipes[1]));
        pins[1].setBounds(510,0,55,210);
        add(pins[1]);//primer tubo verde
        pins[2]=new JLabel(new ImageIcon(pipes[0]));
        pins[2].setBounds(520,250,55,210);
        add(pins[2]);//segundo tubo rojo
        pins[3]=new JLabel(new ImageIcon(pipes[1]));
        pins[3].setBounds(520,-50,55,210);
        add(pins[3]);//segundo tubo verde
        pins[4]=new JLabel(new ImageIcon(pipes[0]));
        pins[4].setBounds(530,200,55,210);
        add(pins[4]);
        pins[5]=new JLabel(new ImageIcon(pipes[1]));
        pins[5].setBounds(530,-100,55,210);
        add(pins[5]);                
        
        backers[0]=new JLabel(new ImageIcon(fondos[0]));
        backers[0].setBounds(0,0,260,510);
        add(backers[0]);
        
        setLayout(null);
        setPreferredSize(new Dimension(250,480));
        setFocusable(true);
        setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        move=new Movement(this,0);
        hilo=new Thread(move);
        hilo.start();
        intro.loop();
        
    }


    public void actionPerformed(ActionEvent ae) {
        JButton btn=(JButton)ae.getSource();
        if(btn==jugar){
            electo=esc.eleccion;
            name=JOptionPane.showInputDialog("Inserta tu nombre de usuario");
            intro.stop();
            move.game=false;
            
            scoreint=0;
            score.setText(""+scoreint);
            score.setVisible(true);
            move=new Movement(this,electo);
            hilo=new Thread(move);
            hilo.start();
            comenzar=true;
            jugar.setVisible(false);
            getready.setVisible(false);
            reiniciar.setVisible(false);
            escoge.setVisible(false);
            logg.setVisible(false);
        }

        if(btn==reiniciar){
            scoreint=0;
            addKeyListener(this);
            score.setText(""+scoreint);
            move=new Movement(this,electo);
            hilo=new Thread(move);
            hilo.start();
            reiniciar.setVisible(false);
        }
        
        if(btn==logg){
            JFrame frm2=new JFrame("Jugadores");
            Score loggin=new Score(frm2);
            frm2.add(loggin);
            frm2.setLocation(500,10);
            frm2.setSize(300,500);
            frm2.setVisible(true);
            frm2.setResizable(false);
        }
        if(btn==escoge){
            
            frm2.add(esc);
            frm2.setLocation(500,10);
            frm2.setSize(300,500);
            frm2.setVisible(true);
            frm2.setResizable(false);

        }
        
    }


    public void keyTyped(KeyEvent ke) {
        char r=ke.getKeyChar();
        if(r==' '){
            subir=true;
        }
        volar.play();
    }


    public void keyPressed(KeyEvent ke) {
        
    }

    public void keyReleased(KeyEvent ke) {
        
    }
}
