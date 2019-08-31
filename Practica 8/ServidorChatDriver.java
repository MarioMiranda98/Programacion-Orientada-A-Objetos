import java.rmi.Naming;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class ServidorChatDriver{
	public static void main(String s[]) throws RemoteException, MalformedURLException{
		Naming.rebind("RMIServidorChat", new ServidorChat());

	}
}