import java.io.*;
import java.net.*;
import java.util.*;

public class VerySimpleChatServer {
    ArrayList<ObjectOutputStream> clientObjectOutputStreams;

    public class ClientHandler implements Runnable {
            ObjectOutputStream writer;
	          ObjectInputStream reader;
            Socket sock;

         public ClientHandler(Socket clientSocket, ObjectOutputStream writer) {
           try {
             this.writer= writer;
             sock = clientSocket;
             reader = new ObjectInputStream(sock.getInputStream());      
           } catch(Exception ex) {
		          System.out.println("Exce Servidor reader " + ex);
             	ex.printStackTrace();
            }
          } 

        public void run() {
	   Object obj;
           try {
              while (true) {
	         	obj = (Object) reader.readObject();
                tellEveryone(obj, writer);
             }
           } catch(Exception ex) {ex.printStackTrace();}
       } 
      } 

     public static void main (String[] args) {
         new VerySimpleChatServer().go();
    }

     public void go() {
       clientObjectOutputStreams = new ArrayList<ObjectOutputStream>();
       try {
       ServerSocket serverSock = new ServerSocket(5000);
       while(true){
          Socket clientSocket = serverSock.accept();
          ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());        
          clientObjectOutputStreams.add(writer);
          Thread t = new Thread(new ClientHandler(clientSocket, writer));
          t.start();
          System.out.println("Conexion Establecida");
      }         
      }catch(Exception ex) {
         ex.printStackTrace();
      }
   }
   public void tellEveryone(Object obj, ObjectOutputStream writerp) {
      Iterator it = clientObjectOutputStreams.iterator();
      while(it.hasNext()) {
        try {
           ObjectOutputStream writer = (ObjectOutputStream) it.next();
	   if(!writer.equals(writerp)){
            writer.writeObject(obj);
           	writer.flush();
	   }
         } catch(Exception ex) {
              ex.printStackTrace();
         }
      
       } 
   } 
}
