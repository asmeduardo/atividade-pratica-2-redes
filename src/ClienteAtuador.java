import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClienteAtuador {
    private static final String TOPICO = "LUMINOSIDADE";
    private static final int INTERVALO = 10000;

    public static void main(String[] args) throws InterruptedException {
        boolean cortinaAberta = false;

        while (true) {
            // Criando a mensagem para solicitar o valor de luminosidade do tópico
            String mensagem = "ASSINAR," + TOPICO;

            try (Socket socket = new Socket("127.0.0.1", 3333);
                 InputStream entradaStream = socket.getInputStream();
                 OutputStream saidaStream = socket.getOutputStream()) {
                // Enviando a mensagem para o servidor
                saidaStream.write(mensagem.getBytes());

                // Lendo a resposta do servidor
                byte[] buffer = new byte[1024];
                int bytesRead = entradaStream.read(buffer);

                if (bytesRead > 0) {
                    // Convertendo a resposta para uma string
                    String resposta = new String(buffer, 0, bytesRead);

                    // Convertendo a resposta em um valor de luminosidade
                    int luminosidade = Integer.parseInt(resposta);
                    System.out.println("Luminosidade recebida: " + luminosidade);

                    // Tomando ação com base no valor de luminosidade
                    if (luminosidade >= 60) {
                        if (!cortinaAberta) {
                            System.out.println("Abrindo a cortina...");
                            cortinaAberta = true;
                        } else {
                            System.out.println("A cortina já está aberta. Mantendo ela aberta.");
                        }
                    } else {
                        if (cortinaAberta) {
                            System.out.println("Fechando a cortina...");
                            cortinaAberta = false;
                        } else {
                            System.out.println("A cortina já está fechada. Mantendo ela fechada.");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Aguardando o intervalo de tempo antes de fazer uma nova requisição
            Thread.sleep(INTERVALO);
        }
    }
}
