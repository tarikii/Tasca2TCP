import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ServerTCP {
    private static final int PORT = 5556;
    private void listen() {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try{
            serverSocket = new ServerSocket(PORT);
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client connectat: " + clientSocket.getInetAddress().getHostAddress());
                ThreadServer FilServidor = new ThreadServer(clientSocket);
                Thread client = new Thread(FilServidor);
                client.start();
            }
        }catch (IOException ex){
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Server s'ha connectat al port " + PORT);
        ServerTCP serverTCP = new ServerTCP();
        serverTCP.listen();
    }
}