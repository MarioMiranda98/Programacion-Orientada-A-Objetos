import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClienteIF extends Remote{
	void RecuperaMensaje(String Mensaje) throws RemoteException;
}