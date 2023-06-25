import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

public class TrataCliente extends Thread {
    private Socket socketCliente;
    private Map<String, String> valoresTopicos;

    public TrataCliente(Socket socketCliente, Map<String, String> valoresTopicos) {
        this.socketCliente = socketCliente;
        this.valoresTopicos = valoresTopicos;
    }

    @Override
    public void run() {
        try {
            // Obtendo os fluxos de entrada e saída do socket
            InputStream entradaStream = socketCliente.getInputStream();
            OutputStream saidaStream = socketCliente.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesLidos = entradaStream.read(buffer);

            if (bytesLidos > 0) {
                // Convertendo os bytes recebidos em uma string
                String mensagem = new String(buffer, 0, bytesLidos);

                // Separando a mensagem em partes usando a vírgula como delimitador
                String[] partes = mensagem.split(",");
                String operacao = partes[0];

                if (operacao.equals("PUBLICAR")) {
                    // Se a operação for "PUBLICAR", armazena o valor no tópico correspondente
                    String topico = partes[1];
                    String valor = partes[2];
                    valoresTopicos.put(topico, valor);
                    saidaStream.write("Valor armazenado com sucesso.".getBytes());
                } else if (operacao.equals("ASSINAR")) {
                    // Se a operação for "ASSINAR", recupera o valor do tópico solicitado
                    String topico = partes[1];
                    String valor = valoresTopicos.getOrDefault(topico, "0");
                    saidaStream.write(valor.getBytes());
                } else {
                    // Se a operação for inválida, envia uma mensagem de erro
                    saidaStream.write("Operação inválida.".getBytes());
                }
            }

            // Fechando o socket
            socketCliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
