import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;
public class Tamagochi extends JFrame  implements LeeRed {
private Canvas3D canvas3D;
private Appearance ap;
private static Texture texture;
private JPanel jp1, jp2;
private JButton Feliz,Triste,Patricio,Bob,Pacman;
private EventHandler eh; 
private String nombres[]={"carafeliz.jpg","caraenfermo.jpg","patricio.jpg","bob.jpg","pacman.jpg"};
private int turno;
private Body body;
private Red r;
public Tamagochi(){
   super("Tamagochi 3D || Practica 5");
   turno=0;
 
   setSize(400, 500);
   GraphicsConfiguration config =     
   SimpleUniverse.getPreferredConfiguration();
   canvas3D = new Canvas3D(config);
   canvas3D.setSize(300, 400);
   eh = new EventHandler();  
   
   Feliz=new JButton("Feliz");
   Triste=new JButton("Triste");
   Patricio=new JButton("Patricio");
   Bob=new JButton("Bob");
   Pacman=new JButton("Pacman");
  
   Feliz.addActionListener(eh);  
   Triste.addActionListener(eh);
   Patricio.addActionListener(eh);
   Bob.addActionListener(eh);
   Pacman.addActionListener(eh);
   
   jp1=new JPanel();
  
   jp1.add(Feliz);  
   jp1.add(Triste);
   jp1.add(Patricio);
   jp1.add(Bob);
   jp1.add(Pacman);
   add("North", jp1); 
   add("Center",canvas3D); 
   setup3DGraphics();
   setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   setVisible(true);
   r=new Red(this);
   
}
void setup3DGraphics(){
   DirectionalLight light1;
   SimpleUniverse universe = new SimpleUniverse(canvas3D);
   universe.getViewingPlatform().setNominalViewingTransform();
   body=new Body(-0.4f,0.0f,0.0f,"",true, this, "Avatar1");
   body.changeTextureCab(texture, "carafeliz.jpg");
   BranchGroup group= body.mybody();
   Color3f light1Color = new Color3f(1.0f, 1.0f, 0.0f);
   BoundingSphere bounds =new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
   Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
   light1 = new DirectionalLight(light1Color, light1Direction);
   light1.setInfluencingBounds(bounds);
   group.addChild(light1);
   universe.addBranchGraph(group);
}
public static void main(String[] args) { new Tamagochi(); }
class EventHandler implements ActionListener {  
  public void actionPerformed(ActionEvent e) { 
     Object obj=e.getSource();
     if(obj instanceof JButton){ 
     	JButton btn=(JButton)e.getSource();
     	if(btn==Feliz){ turno=0; }
		else if (btn==Triste){turno=1;}
		else if (btn==Patricio){turno=2;}
		else if (btn==Bob){turno=3;}
		else if (btn==Pacman){turno=4;}
     	body.changeTextureCab(texture, nombres[turno]);
     	r.escribeRed(new Icono("Tamagochi", turno)); 
     }  
  }
}
public void leeRed(Object obj){
	if(obj instanceof Icono){      
        	Icono i=(Icono)obj;  
                System.out.println("Recibi"+nombres[i.getTurno()]);
                body.changeTextureCab(texture, nombres[i.getTurno()]);                 	
	}
}
}