import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class ClientTCP {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5556;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Llista llista = new Llista("probando", Arrays.asList(1, 2, 3, 2, 1, 4, 5));
        System.out.println("Enviant els numeros al server: " + llista.getNom() + " " + llista.getNumberList());

        Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

        outputStream.writeObject(llista);
        outputStream.flush();

        Llista result = (Llista) inputStream.readObject();
        System.out.println("El servidor ha terminat d'ordenar i esborrar: " + result.getNom() + " " + result.getNumberList());

        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
