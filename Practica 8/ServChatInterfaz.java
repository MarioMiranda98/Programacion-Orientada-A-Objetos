import java.rmi.Remote;
import java.rmi.RemoteException;
import ChatClienteIF;

public interface ServChatInterfaz extends Remote{
	void RegistraChatCliente(ChatClienteIF Clientechat) throws RemoteException; 
	void BroadcastMessage(String mensaje) throws RemoteException;
}