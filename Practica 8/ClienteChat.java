import java.rmi.server.UnicastRemoteObject;
import java.RemoteException;
import ServChatInterfaz;
import java.util.Scanner;

public class ClienteChat extends UnicastRemoteObject implements ChatCLienteIF, Runnable{
	private static final long serialVersionUID=IL;
	private ServChatInterfaz ServChat;
	private String nombre=null;
	protected ClienteChat(String nombre, ServChatInterfaz ServChat) throws RemoteException{
		this.nombre=nombre;
		this.ServChat=ServChat;
		ServChat.RegistraChatCliente(this);
	}

	public void RecuperaMensaje(String mensaje) throws RemoteException{
		System.out.println(mensaje);
	}

	public void run(){
		Scanner scanner=new Scanner(System.in);
		String mensaje;
		while(true){
			mensaje=scanner.nextLine();
			try{
			ServidorChat.BroadCastMessage(nombre+" : "+mensaje);
			}catch(RemoteException e){
				e.printStackTrace();
			}
		}
	}
}