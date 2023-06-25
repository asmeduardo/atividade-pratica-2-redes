import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {
    private static Map<String, String> valoresTopicos = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(3333)) {
            System.out.println("Servidor iniciado. Escutando na porta 3333...");

            while (true) {
                // Aguardando a conexão de um cliente
                Socket socketCliente = serverSocket.accept();

                // Criando uma instância da classe TrataCliente para tratar a conexão
                TrataCliente trataCliente = new TrataCliente(socketCliente, valoresTopicos);

                // Iniciando a execução do tratamento do cliente em uma nova thread
                trataCliente.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
