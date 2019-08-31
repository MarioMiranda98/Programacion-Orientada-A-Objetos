import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class ranb extends Applet implements ActionListener{
	
	Button b;
	TextField t;
	Label l1, l2, l3;
	int aleatorio=(int)Math.floor(Math.random()*(100-1)+1);
	int i=0, j=5;

	public void init(){
		b=new Button("Ingresa tu numero");
		t=new TextField(4);
		l1=new Label("******");
		l2=new Label("");
		l3=new Label("");
		add(t); add(b); add(l1); add(l2); add(l3); 

		if(i<7)
		b.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){

		if(i<7){
			int reu=Integer.parseInt(t.getText());		

			if(reu==aleatorio){
				l1.setText("Ganaste");
			}
			else{
				
				int valor=(aleatorio/10)*10;
				l2.setText("El numero esta entre "+valor+" y "+(valor+10));

				l3.setText("Te quedan "+j+" intentos");

				if(reu>aleatorio){	
					l1.setText("Te pasaste");
				}
				if(reu<aleatorio){
					l1.setText("Te falta");
				}
			}
			i=i+1;
			j=j-1;
		}
		else{
			l1.setText("Fin del juego");
		}
	}
}