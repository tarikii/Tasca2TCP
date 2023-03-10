import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class ClientTCP {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5556;
    private static Scanner scanner = new Scanner(System.in);
    private static boolean infinite = true;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        while (infinite){
            System.out.println("Posa 7 numeros separats per espais en blancs");
            int n1 = scanner.nextInt();
            int n2 = scanner.nextInt();
            int n3 = scanner.nextInt();
            int n4 = scanner.nextInt();
            int n5 = scanner.nextInt();
            int n6 = scanner.nextInt();
            int n7 = scanner.nextInt();

            Llista llista = new Llista("probando", Arrays.asList(n1, n2, n3, n4, n5, n6, n7));
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
}
