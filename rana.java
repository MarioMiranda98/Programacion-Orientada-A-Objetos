import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class rana extends Applet implements ActionListener{
	
	Button b;
	TextField t;
	Label l1, l2, l3;
	int aleatorio=(int)Math.floor(Math.random()*(1000-1)+1);

	public void init(){
		b=new Button("Ingresa tu numero");
		t=new TextField(4);
		l1=new Label("******");
		l2=new Label("");
		add(t); add(b); add(l1); add(l2); 

		b.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
			
			int reu=Integer.parseInt(t.getText());		
			System.out.print(""+aleatorio);

			if(reu==aleatorio){
				l1.setText("Ganaste");
			}
			else{
				int valor=(aleatorio/100)*100;
				l2.setText("El numero esta entre "+valor+" y "+(valor+100));
				if(reu>aleatorio){	
					l1.setText("Te pasaste");
				}
				if(reu<aleatorio){
					l1.setText("Te falta");
				}
			}
	}
}