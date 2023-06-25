import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;

public class ClienteSensor {
    private static final String TOPICO = "LUMINOSIDADE";
    private static final int INTERVALO = 5000;

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();

        while (true) {
            // Gerando um valor aleatório de luminosidade entre 0 e 100
            int luminosidade = random.nextInt(101);

            // Criando a mensagem a ser enviada no formato "PUBLICAR,TOPICO,luminosidade"
            String mensagem = "PUBLICAR," + TOPICO + "," + luminosidade;

            try (Socket socket = new Socket("127.0.0.1", 3333);
                 OutputStream saidaStream = socket.getOutputStream()) {
                // Enviando a mensagem para o servidor
                saidaStream.write(mensagem.getBytes());
                System.out.println("Valor de luminosidade publicado: " + luminosidade);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Aguardando o intervalo de tempo antes de enviar a próxima mensagem
            Thread.sleep(INTERVALO);
        }
    }
}
