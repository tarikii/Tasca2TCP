import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class ThreadServer implements Runnable {
    /* Thread que gestiona la comunicaci√≥ de ServerTCP.java i un client ClientTCP.java */
    private Socket clientSocket;
    private boolean infinite = true;

    public ThreadServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        while (infinite){
            try {
                ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

                Llista llista = (Llista) inputStream.readObject();
                System.out.println("Received object from client: " + llista.getNom() + " " + llista.getNumberList());

                List<Integer> uniqueNumbers = llista.getNumberList().stream().distinct().sorted().collect(Collectors.toList());
                Llista result = new Llista(llista.getNom(), uniqueNumbers);
                outputStream.writeObject(result);

                inputStream.close();
                outputStream.close();
                clientSocket.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}