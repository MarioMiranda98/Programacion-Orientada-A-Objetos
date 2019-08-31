import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

public class mosaico extends JApplet implements ActionListener{
	JLabel imgfin;
	JButton imgselec[];
	Container c, c1;
	ImageIcon imag[];

	public void init(){
		c=getContentPane();
		c1=getContentPane();
		c.setLayout(new FlowLayout());
		imgselec=new JButton[40];
		imag=new ImageIcon[40];

		for(int i=0; i<imgselec.length; i++){
			imgselec[i]=new JButton((i+1)+".jpg");
			imag[i]=new ImageIcon((i+1)+".jpg");
			imgselec[i].setIcon(imag[i]);
		}
		imgfin=new JLabel("");

		c.add("Center", imgfin);

		for(int i=0; i<imgselec.length; i++){
		imgselec[i].addActionListener(this);
		}
		
		c1.setLayout(new GridLayout(6,6));
		for(int i=0; i<imgselec.length; i++){
			c1.add(imgselec[i]);
		}

	}	

	public void actionPerformed(ActionEvent e){
		JButton elec=(JButton)e.getSource();
		imgfin.setIcon(elec.getIcon());
	}

	public static void main(String s[]){
		mosaico ms=new mosaico();
		ms.init(); ms.start();

		JFrame f=new JFrame("Practica 2");
		f.add("Center", ms);
		f.setSize(900,900);
		f.setVisible(true);

	}
}
