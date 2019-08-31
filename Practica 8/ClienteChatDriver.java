import java.rmi.Namning;
import ServChatInterfaz;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

public class ClienteChatDriver{
	public static void main(String s[]) throws MalformedURLException, RemoteException, NotBoundException{
		String ServidorChatURL="rmi://localhost/RMIServidorChat";
		ServChatInterfaz ServidorChat=(ServChatInterfaz)Namning.lookup(ServidorChatURL);
		new Thread(new ClienteChat(s[0], ServidorChat)).start();
	}
}