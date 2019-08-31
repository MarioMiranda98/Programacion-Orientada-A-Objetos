import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import ChatClienteIF;

public class ServidorChat extends UnicastRemoteObject implements ServChatInterfaz{
	private static final long serialVersionUID = IL;
	private ArrayList<ChatClienteIF> chatClients;
	protected ServidorChat() throws RemoteException{
		chatClients=new ArrayList<ChatClienteIF>();
	} 

	public synchronized void RegistraChatCliente(ChatClienteIF ClienteChat) throws RemoteException{
		this.chatClients.add(chatCliente);
	} 

	public synchronized void BroadcastMessage(String mensaje) throws RemoteException{
		int i=0;
		while(i<chatClients.size()){
			chatClients.get(i++).RecuperaMensaje(mensaje);
		}
	}
}