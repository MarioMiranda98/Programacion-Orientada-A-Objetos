import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class temp extends Applet implements ActionListener{
	Button bc, bk;
	TextField t;
	Label l;

	public void init(){
		bc=new Button("Celsius a Kelvin");
		bk=new Button("Kelvin a Celsius");
		t=new TextField(30);
		l=new Label("******");
		add(t); add(bc); add(bk); add(l);

		bc.addActionListener(this);
		bk.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
			
			Button b=(Button)e.getSource();
				if(b==bc){
					double res=Double.parseDouble(t.getText());
					double res2=res+273.15;
					l.setText(""+res2);
				}
				if(b==bk){
					double res=Double.parseDouble(t.getText());
					double res3=res-(273.15);
						l.setText(""+res3);
				}
	}
}