import java.awt.*;
import java.awt.event.*;

public class autobus extends Frame implements ActionListener{
	
	Button ba[]=new Button[45];
	Button mostrar;
	Label mos;
	int a=0;
	String acumulado[]=new String[45]; 
	String asientos[]=new String[45];

	public autobus(){
		setLayout(new GridLayout(11,4));
		for(int i=1; i<asientos.length; i++){
			add(ba[i]=new Button(""+i));
			ba[i].addActionListener(this);
		}
		
		mostrar=new Button("Ocupados");
		add(mostrar);
		mostrar.addActionListener(this);
		mos=new Label("");
		add(mos);

		setSize(400,400);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		Button b=(Button)e.getSource();
		b.setEnabled(false);
		
		int valor=Integer.parseInt(b.getText());
		acumulado[a]=valor;
		if(b==mostrar){
			//while(a<acumulado.length){
			//mos.setText(acumulado[a].getText());
			//}
			b.setEnabled(true);
		}
		a++;
	}

	public static void main(String s[]){
		new autobus();
	}
}