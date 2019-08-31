import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
public class planetario {	
public planetario(){	 
	BranchGroup group = new BranchGroup();
	Appearance appsol = new Appearance();
	Appearance appearth = new Appearance();
	Appearance appmars = new Appearance();
	Appearance appven = new Appearance();

        TextureLoader tex=new TextureLoader("TIERRA.JPG", null);
	appearth.setTexture(tex.getTexture());
	tex=new TextureLoader("SOL.JPG", null);
	appsol.setTexture(tex.getTexture());
	tex=new TextureLoader("MARTE.JPG", null);
	appmars.setTexture(tex.getTexture());
	tex=new TextureLoader("VENUS.JPG", null);
	appven.setTexture(tex.getTexture());
	Sphere earth = new Sphere(0.045f, Primitive.GENERATE_NORMALS | 			
	Primitive.GENERATE_TEXTURE_COORDS, 32, appearth);
	Sphere sol = new Sphere(0.35f, Primitive.GENERATE_NORMALS | 
	Primitive.GENERATE_TEXTURE_COORDS, 32, appsol);
	Sphere mars = new Sphere(0.055f, Primitive.GENERATE_NORMALS | 
	Primitive.GENERATE_TEXTURE_COORDS, 32, appmars);
	Sphere ven = new Sphere(0.025f, Primitive.GENERATE_NORMALS | 
	Primitive.GENERATE_TEXTURE_COORDS, 32, appven);
	TransformGroup earthRotXformGroup = Posi.rotate(earth, new Alpha(-1, 1250));
	TransformGroup marsRotXformGroup = Posi.rotate(mars, new Alpha(-1, 1250));
	TransformGroup solRotXformGroup = Posi.rotate(sol, new Alpha(-1, 1250));
	TransformGroup venRotXformGroup = Posi.rotate(ven, new Alpha(-1, 1250));
	TransformGroup earthTransXformGroup = Posi.translate(earthRotXformGroup, 
	new Vector3f(0.0f, 0.0f, 0.7f));
	TransformGroup marsTransXformGroup = Posi.translate(marsRotXformGroup, 
	new Vector3f(0.0f, 0.0f, 1.0f));
	TransformGroup venTransXformGroup = Posi.translate(venRotXformGroup,
	new Vector3f(0.0f, 0.0f, 0.5f));
TransformGroup earthRotGroupXformGroup = Posi.rotate(earthTransXformGroup, new Alpha(-1, 5000));
TransformGroup marsRotGroupXformGroup = Posi.rotate(marsTransXformGroup, new Alpha(-1, 4500));
TransformGroup venRotGroupXformGroup = Posi.rotate(venTransXformGroup, new Alpha(-1, 5500));
    	group.addChild(marsRotGroupXformGroup);
    	group.addChild(earthRotGroupXformGroup);
    	group.addChild(venRotGroupXformGroup);
    	group.addChild(solRotXformGroup);
    	GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
    	Canvas3D canvas = new Canvas3D(config); canvas.setSize(600, 600);
    	SimpleUniverse universe = new SimpleUniverse(canvas);
    	universe.getViewingPlatform().setNominalViewingTransform();
    	universe.addBranchGraph(group);  
    	JFrame f = new JFrame("Planetario");
    	f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
    	f.add(canvas); f.pack(); f.setVisible(true); }
public static void main(String a[]) { new planetario();}
}                                               