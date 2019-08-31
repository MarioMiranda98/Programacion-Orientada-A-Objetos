import java.rmi.*;
import java.rmi.server.*;
import java.rmi.RMISecurityManager;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class cliente extends JFrame implements ActionListener{

	String respuesta;
	String pregunta;
	String s,nick,host;
				JTextArea texto;
				JTextArea out;
				JScrollPane scroll;
				JButton envia;
				ImageIcon icono,bott;
				JLabel bot;


	public cliente(){

					setTitle("KANGUBOT-ESCOM || POO || Practica RMI");
                    setSize(520,430);
                    setLocation(440,180);
                    setResizable(false);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    setVisible(true);
					 nick = JOptionPane.showInputDialog("Ingresa tu nick name", "");
                    componentes();


	}

	public void componentes()
					{
								setLayout(null);
								add(bot = new JLabel());
								icono=new ImageIcon("submit.png");
								bott=new ImageIcon("bot.gif");
								add(texto= new JTextArea());
								add(out= new JTextArea());
								add(scroll= new JScrollPane(out,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
								envia=new JButton("Enviar");
								envia.addActionListener(this);
								add(envia);
								bot.setIcon(bott);
								envia.setIcon(icono);
								envia.setBounds(340,340,120,30);
								out.setBounds(12,80,340,210);
								scroll.setBounds(12,80,300,210);
								out.setBorder(BorderFactory.createLineBorder(Color.black));
								out.setEditable(false);
								out.append("\n");
								texto.setBounds(12,330,300,50);
								texto.setBorder(BorderFactory.createLineBorder(Color.black));
								bot.setBounds(320,10,240,300);
								out.append("   Kangubot: Hola "+nick+" !!!\n");
								//bot.setBorder(BorderFactory.createLineBorder(Color.black));


					}

					public void actionPerformed(ActionEvent a)
						{
							System.setSecurityManager(new RMISecurityManager());
							pregunta = texto.getText();

							if (pregunta.length() != 0){

								out.append("   "+nick+": " +texto.getText()+"\n");
								texto.setText(null);
								try{
								IPregunta Q = (IPregunta) Naming.lookup ("questions");
								respuesta = Q.LeePregunta(pregunta);
								out.append("   Kangubot: "+respuesta+"\n");
								}catch(Exception e){
								return;}


								}



						}

	public static void main(String[] args) {

		cliente obj =new cliente();

	}
}
