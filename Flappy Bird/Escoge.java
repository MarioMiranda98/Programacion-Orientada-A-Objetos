import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import java.applet.AudioClip;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Escoge extends JPanel implements ActionListener{
    
    JLabel escoge;
    JButton imperial,flappy,lugia;
    BufferedImage birds[], imperialdra[],lugi[];
    BufferedImage spriteSheet=null,spriteSheet1=null,spriteSheet2=null;
    JFrame ventana;
    public int eleccion=0;
    
    private BufferedImage getSprite(BufferedImage spriteSheet, int i, int j, int width, int height){      
        return  spriteSheet.getSubimage(j , i , width, height);
    }

    public Escoge(JFrame frm){
        
        ventana=frm;
        setLayout(null);        

        try {   
       spriteSheet = ImageIO.read(getClass().getResource("Flappy-Graphics.png")); 
       spriteSheet1 = ImageIO.read(getClass().getResource("imperialdramon1.png"));
       spriteSheet2 = ImageIO.read(getClass().getResource("Lugia1.png"));
       
        }catch(IOException e){  System.out.println("Imagen No Encontrada"); }
        imperialdra = new BufferedImage[2];
        birds = new BufferedImage[2];
        lugi = new BufferedImage[2];

        imperialdra [0] = getSprite(spriteSheet1,125, 70,75 , 73);
        birds [0] = getSprite(spriteSheet,750, 220, 50, 50);
        lugi [0] = getSprite(spriteSheet2,120, 175,87 , 70);

        escoge=new JLabel("ESCOJE UN PERSONAJE");
        imperial=new JButton(new ImageIcon(imperialdra[0]));
        flappy=new JButton(new ImageIcon(birds[0]));
        lugia=new JButton(new ImageIcon(lugi[0]));

        imperial.setBorderPainted(false);
        imperial.setOpaque(false);
        imperial.setContentAreaFilled(false);
        flappy.setBorderPainted(false);
        flappy.setOpaque(false);
        flappy.setContentAreaFilled(false);
        lugia.setBorderPainted(false);
        lugia.setOpaque(false);
        lugia.setContentAreaFilled(false);
        
        escoge.setBounds(90,50,200,25);
        flappy.setBounds(20,120,100,50);
        imperial.setBounds(140,120,150,70);
        lugia.setBounds(80,270,150,150);
        add(escoge);
        add(flappy);
        add(imperial);
        add(lugia);
        flappy.addActionListener(this);
        imperial.addActionListener(this);
        lugia.addActionListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        JButton btn=(JButton)e.getSource();
        
        if(btn==flappy){
            eleccion=0;
            ventana.setVisible(false);           
        }
        
        if(btn==imperial){
            eleccion=1;
            ventana.setVisible(false);
            
        }
        if(btn==lugia){
            eleccion=2;
            ventana.setVisible(false);
        }
    }
}
