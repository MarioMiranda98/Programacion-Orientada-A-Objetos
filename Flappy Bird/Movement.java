import java.awt.Rectangle;
import java.awt.geom.Area;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.applet.AudioClip;


public class Movement implements Runnable {
    private Pintado panel;
    private int xone,xtwo,xtree;
    private int xground;
    public int aleteo;
    private int sum;
    int a,p=0, aux=0,electo=0,auxi=0,velocidad=10;
    private boolean flag;
    private Area[] tubix;
    private Area flappy;
    private Area[] succes;
    public boolean game,move;
    private Rectangle[] rect;
    public int u,x;
    private Connection conn=null;
    private Statement stm=null;
    private ResultSet res=null;
    private AudioClip audio,audiohit;
    public Movement(Pintado p1, int eleccion){
        u=0;
        electo=eleccion;
        if(electo!=0) aux=25;
        tubix=new Area[6];
        flappy=new Area();
        succes=new Area[3];
        rect=new Rectangle[3];
        panel=p1;
        if(electo==0){
            for (int i=0; i<panel.imperialdramon.length;i++){
                            panel.imperialdramon[i].setVisible(false);
            }
            for (int i=0; i<panel.lugia.length;i++){
                            panel.lugia[i].setVisible(false);
            }


        }
        else if( electo==1){
            for (int i=0; i<panel.flappys.length;i++){
                            panel.flappys[i].setVisible(false);
            }
            for (int i=0; i<panel.lugia.length;i++){
                            panel.lugia[i].setVisible(false);
            }

        }

        else if( electo==2){
            for (int i=0; i<panel.flappys.length;i++){
                            panel.flappys[i].setVisible(false);
            }
            for (int i=0; i<panel.imperialdramon.length;i++){
                            panel.imperialdramon[i].setVisible(false);
            }
        }

        flag=true;
        xone=250;
        xtwo=500;
        xtree=750;
        xground=0;
        aleteo=250;
        a=0;
        sum=0;
        game=true;
        move=true;
        audio=java.applet.Applet.newAudioClip(getClass().getResource("sfx_point.wav"));
        audiohit=java.applet.Applet.newAudioClip(getClass().getResource("sfx_hit.wav"));
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Movement.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/flappyscore","root","");
            stm=conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Movement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        while(game){
            if(electo==1){
                panel.imperialdramon[p].setVisible(true);
                for (int i=0; i<panel.imperialdramon.length;i++){
                    if (panel.imperialdramon[i]!=panel.imperialdramon[p])
                        panel.imperialdramon[i].setVisible(false);
                }

                if(p==7) p=0;
                if(auxi%37==0) p++;
                auxi++;

                if(auxi%473==0&&velocidad>4) velocidad--;
                try {
                    Thread.sleep(velocidad);
                } catch (InterruptedException ex) {
                    return;
                }
                if(move==true){
                xone-=1;
                xtwo-=1;
                xtree-=1;
                xground-=1;
                if(xone==-250){
                    xone=500;
                }
                if(xtwo==-250){
                    xtwo=500;
                }
                if(xtree==-250){
                    xtree=500;
                }
                panel.pins[0].setBounds(xone,300+aux,55,200);
                panel.pins[1].setBounds(xone,-0-aux,55,200);
                panel.pins[2].setBounds(xtwo,250+aux,55,210);
                panel.pins[3].setBounds(xtwo,-50-aux,55,210);
                panel.pins[4].setBounds(xtree,200+aux,55,210);
                panel.pins[5].setBounds(xtree,-100-aux,55,210);
                tubix[0]=new Area(panel.pins[0].getBounds());
                tubix[1]=new Area(panel.pins[1].getBounds());
                tubix[2]=new Area(panel.pins[2].getBounds());
                tubix[3]=new Area(panel.pins[3].getBounds());
                tubix[4]=new Area(panel.pins[4].getBounds());
                tubix[5]=new Area(panel.pins[5].getBounds());
                rect[0]=new Rectangle(xone+2,0,1,800);
                rect[1]=new Rectangle(xtwo+2,0,1,800);
                rect[2]=new Rectangle(xtree+2,0,1,800);
                succes[0]=new Area(rect[0].getBounds2D());
                succes[1]=new Area(rect[1].getBounds2D());
                succes[2]=new Area(rect[2].getBounds2D());

                if(xground==-250){
                    xground=-10;
                }
                panel.tierra[0].setBounds(xground,300,270,300);
                panel.tierra[1].setBounds(xground+264,300,270,300);
                }

                if(panel.comenzar==true){
                if(panel.subir==false){
                    if(aleteo<=375){
                    aleteo+=1;
                    panel.imperialdramon[p].setBounds(20,aleteo,60,60);
                    if(move==false){
                        aleteo+=3;
                        panel.imperialdramon[p].setBounds(20,aleteo,60,60);
                        audiohit.play();
                    }
                    }else{
                        panel.reiniciar.setVisible(true);
                        game=false;
                        try {
                            stm.executeUpdate("update users set points="+panel.scoreint+" where user like '"+panel.name+"'");
                            System.out.println("update users set points="+panel.scoreint+" where user like '"+panel.name+"'");
                        } catch (SQLException ex) {
                            Logger.getLogger(Movement.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                if(panel.subir==true){
                    aleteo-=30;
                    panel.imperialdramon[p].setBounds(20,aleteo,60,60);
                }

                if(flappy.intersects(tubix[0].getBounds2D()) || flappy.intersects(tubix[1].getBounds2D())
                   || flappy.intersects(tubix[2].getBounds2D()) || flappy.intersects(tubix[3].getBounds2D())
                   || flappy.intersects(tubix[4].getBounds2D()) || flappy.intersects(tubix[5].getBounds2D())
                   || aleteo>=375){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        return;
                    }
                    panel.subir=false;
                    move=false;
                    panel.removeKeyListener(panel);
                }

                if(flappy.intersects(succes[0].getBounds2D()) || flappy.intersects(succes[1].getBounds2D())
                   || flappy.intersects(succes[2].getBounds2D())){
                    sum++;
                    if(sum==1){
                        panel.scoreint++;
                        panel.score.setText(""+panel.scoreint);
                        audio.play();
                    }
                }else{
                    sum=0;
                }

                flappy=new Area(panel.imperialdramon[p].getBounds());
                panel.subir=false;
            }
        }
        else if(electo==0){

                for (int i=0; i<panel.flappys.length;i++){
                    if (panel.flappys[i]!=panel.flappys[p])
                        panel.flappys[i].setVisible(false);
                }
                panel.flappys[p].setVisible(true);
                if(p==2) p=0;
                if(auxi%53==0) p++;
                auxi++;
                if(auxi%473==0&&velocidad>4) velocidad--;

                try {
                    Thread.sleep(velocidad);
                } catch (InterruptedException ex) {
                    return;
                }
                if(move==true){
                xone-=1;
                xtwo-=1;
                xtree-=1;
                xground-=1;
                if(xone==-250){
                    xone=500;
                }
                if(xtwo==-250){
                    xtwo=500;
                }
                if(xtree==-250){
                    xtree=500;
                }
                panel.pins[0].setBounds(xone,320+aux,55,200);
                panel.pins[1].setBounds(xone,-20-aux,55,200);
                panel.pins[2].setBounds(xtwo,270+aux,55,210);
                panel.pins[3].setBounds(xtwo,-70-aux,55,210);
                panel.pins[4].setBounds(xtree,220+aux,55,210);
                panel.pins[5].setBounds(xtree,-120-aux,55,210);
                tubix[0]=new Area(panel.pins[0].getBounds());
                tubix[1]=new Area(panel.pins[1].getBounds());
                tubix[2]=new Area(panel.pins[2].getBounds());
                tubix[3]=new Area(panel.pins[3].getBounds());
                tubix[4]=new Area(panel.pins[4].getBounds());
                tubix[5]=new Area(panel.pins[5].getBounds());
                rect[0]=new Rectangle(xone+2,0,1,800);
                rect[1]=new Rectangle(xtwo+2,0,1,800);
                rect[2]=new Rectangle(xtree+2,0,1,800);
                succes[0]=new Area(rect[0].getBounds2D());
                succes[1]=new Area(rect[1].getBounds2D());
                succes[2]=new Area(rect[2].getBounds2D());

                if(xground==-250){
                    xground=-10;
                }
                panel.tierra[0].setBounds(xground,300,270,300);
                panel.tierra[1].setBounds(xground+264,300,270,300);
                }

                if(panel.comenzar==true){
                if(panel.subir==false){
                    if(aleteo<=375){
                    aleteo+=1;
                    panel.flappys[p].setBounds(20,aleteo,60,60);
                    if(move==false){
                        aleteo+=3;
                        panel.flappys[p].setBounds(20,aleteo,60,60);
                        audiohit.play();
                    }
                    }else{
                        panel.reiniciar.setVisible(true);
                        game=false;
                        try {
                            stm.executeUpdate("update users set points="+panel.scoreint+" where user like '"+panel.name+"'");
                            System.out.println("update users set points="+panel.scoreint+" where user like '"+panel.name+"'");
                        } catch (SQLException ex) {
                            Logger.getLogger(Movement.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                if(panel.subir==true){
                    aleteo-=30;
                    panel.flappys[p].setBounds(20,aleteo,60,60);
                }

                if(flappy.intersects(tubix[0].getBounds2D()) || flappy.intersects(tubix[1].getBounds2D())
                   || flappy.intersects(tubix[2].getBounds2D()) || flappy.intersects(tubix[3].getBounds2D())
                   || flappy.intersects(tubix[4].getBounds2D()) || flappy.intersects(tubix[5].getBounds2D())
                   || aleteo>=375){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        return;
                    }
                    panel.subir=false;
                    move=false;
                    panel.removeKeyListener(panel);
                }

                if(flappy.intersects(succes[0].getBounds2D()) || flappy.intersects(succes[1].getBounds2D())
                   || flappy.intersects(succes[2].getBounds2D())){
                    sum++;
                    if(sum==1){
                        panel.scoreint++;
                        panel.score.setText(""+panel.scoreint);
                        audio.play();
                    }
                }else{
                    sum=0;
                }

                flappy=new Area(panel.flappys[p].getBounds());
                panel.subir=false;
            }
        }

        else if (electo==2){

                for (int i=0; i<panel.lugia.length;i++){
                    if (panel.lugia[i]!=panel.lugia[p])
                        panel.lugia[i].setVisible(false);
                }
                panel.lugia[p].setVisible(true);
                if(p==11) p=0;
                if(auxi%37==0) p++;
                auxi++;

                if(auxi%473==0&&velocidad>4) velocidad--;
                try {
                    Thread.sleep(velocidad);
                } catch (InterruptedException ex) {
                    return;
                }
                if(move==true){
                xone-=1;
                xtwo-=1;
                xtree-=1;
                xground-=1;
                if(xone==-250){
                    xone=500;
                }
                if(xtwo==-250){
                    xtwo=500;
                }
                if(xtree==-250){
                    xtree=500;
                }
                panel.pins[0].setBounds(xone,300+aux,55,200);
                panel.pins[1].setBounds(xone,-0-aux,55,200);
                panel.pins[2].setBounds(xtwo,250+aux,55,210);
                panel.pins[3].setBounds(xtwo,-50-aux,55,210);
                panel.pins[4].setBounds(xtree,200+aux,55,210);
                panel.pins[5].setBounds(xtree,-100-aux,55,210);
                tubix[0]=new Area(panel.pins[0].getBounds());
                tubix[1]=new Area(panel.pins[1].getBounds());
                tubix[2]=new Area(panel.pins[2].getBounds());
                tubix[3]=new Area(panel.pins[3].getBounds());
                tubix[4]=new Area(panel.pins[4].getBounds());
                tubix[5]=new Area(panel.pins[5].getBounds());
                rect[0]=new Rectangle(xone+2,0,1,800);
                rect[1]=new Rectangle(xtwo+2,0,1,800);
                rect[2]=new Rectangle(xtree+2,0,1,800);
                succes[0]=new Area(rect[0].getBounds2D());
                succes[1]=new Area(rect[1].getBounds2D());
                succes[2]=new Area(rect[2].getBounds2D());

                if(xground==-250){
                    xground=-10;
                }
                panel.tierra[0].setBounds(xground,300,270,300);
                panel.tierra[1].setBounds(xground+264,300,270,300);
                }

                if(panel.comenzar==true){
                if(panel.subir==false){
                    if(aleteo<=375){
                    aleteo+=1;
                    panel.lugia[p].setBounds(20,aleteo,100,70);
                    if(move==false){
                        aleteo+=3;
                        panel.lugia[p].setBounds(20,aleteo,100,70);
                        audiohit.play();
                    }
                    }else{
                        panel.reiniciar.setVisible(true);
                        game=false;
                        try {
                            stm.executeUpdate("update users set points="+panel.scoreint+" where user like '"+panel.name+"'");
                            System.out.println("update users set points="+panel.scoreint+" where user like '"+panel.name+"'");
                        } catch (SQLException ex) {
                            Logger.getLogger(Movement.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                if(panel.subir==true){
                    aleteo-=30;
                    panel.lugia[p].setBounds(20,aleteo,100,70);
                }

                if(flappy.intersects(tubix[0].getBounds2D()) || flappy.intersects(tubix[1].getBounds2D())
                   || flappy.intersects(tubix[2].getBounds2D()) || flappy.intersects(tubix[3].getBounds2D())
                   || flappy.intersects(tubix[4].getBounds2D()) || flappy.intersects(tubix[5].getBounds2D())
                   || aleteo>=375){
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException ex) {
                        return;
                    }
                    panel.subir=false;
                    move=false;
                    panel.removeKeyListener(panel);
                }

                if(flappy.intersects(succes[0].getBounds2D()) || flappy.intersects(succes[1].getBounds2D())
                   || flappy.intersects(succes[2].getBounds2D())){
                    sum++;
                    if(sum==1){
                        panel.scoreint++;
                        panel.score.setText(""+panel.scoreint);
                        audio.play();
                    }
                }else{
                    sum=0;
                }

                flappy=new Area(panel.lugia[p].getBounds());
                panel.subir=false;
            }
        }


        }
    }

}
