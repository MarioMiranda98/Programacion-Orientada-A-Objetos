import java.net.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Aplicacion extends JFrame implements ActionListener{
	final String host = "localhost";
	final int puerto = 9999;
	Socket conect;
	PrintStream out;
	DataInputStream in;
	String str;
	JTextField texto;
	JButton enviando;
	JLabel label;

	public Aplicacion(){
		super("CHATBOT");
		texto = new JTextField(40);
		enviar = new JButton("Enviar ");
		etiqueta = new JLabel("Escribe un mensaje");

		enviar.addActionListener(this);
		
		setLayout(new FlowLayout());
		
		add(texto);
		add(enviando);
		add(label);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(510,250);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent a){
		try{
			conect = new Socket(host, puerto);
			
			in = new DataInputStream(conexion.getInputStream());
			out = new PrintStream(conexion.getOutputStream());
			
			out.println(texto.getText());
			str = in.readLine();
			
			label.setText(s);
		}
		catch (Exception e){
			etiqueta.setText("Error: " + e.getMessage());
			System.out.print("Error: " + e.getMessage());
		}
	}
	
public static void main(String args[]){
		new Aplicacion();
	}
}