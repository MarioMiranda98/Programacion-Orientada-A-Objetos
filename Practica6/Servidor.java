import java.net.*;
import java.io.*;

public class Servidor{	
	public static void main(String args[]){
		final int puerto= 9999;
		
		String res[]={"Me encanto desde que lo escuche","Bare Knucle II","Me encanta, es mi vida","13 Años, algo joven","La guitarra, siempre se me facilito","Claro, mientras no se mescle con fisica","Si, me mantiene lejos de malos pasos","El futbol, no se quien use los pies","Mas viejo xD, nahh con alguna carrera o familia","Siiiiiiii, regular forever and ever"};
		
		String preg[]= {"Te gusta el blues/jazz?","El mejor beat em up que has jugado?","Te gusta ejecutar instrumentos musicales?",
		"A los cuantos años empezaste?","Con que instrumento?","Rifas en calculo?","Haces deporte?","Que se juega con el corazon?",
		"Como te imaginas de grande?","Quieres pasar todas tus materias?"};
		
		ServerSocket socketS;
		Socket conect;
		
		PrintStream outt;
		DataInputStream in;
		
		String str;
		
		try{
			socketS = new ServerSocket(puerto);
			while (true){
				conect = socketS.accept();
				
				in=new DataInputStream(conect.getInputStream());
				outt=new PrintStream(conect.getOutputStream());
				
				str = in.readLine();
				
				if(str.equals("Hola"))
					outt.println("Hola. Como estas?");
				
				for (int i=0;i<10;i++){
					if(str.equals(preg[i])){outt.println(res[i]);}   	
				}
				conect.close();	
			}
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
}