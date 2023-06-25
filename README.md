# Sistema de Comunicação para Controle de Cortina Eletrônica

Este é um sistema de comunicação desenvolvido para abrir e fechar uma cortina eletrônica com base na luminosidade do ambiente externo. O sistema é composto pelos componentes: ClienteSensor, ClienteAtuador, Servidor e TrataCliente.

![Diagrama do sistema](https://imagizer.imageshack.com/img924/241/oad7gj.png)
**Figura 1:** Atores e comunicação entre eles.

## Funcionamento

O ClienteSensor é responsável por se conectar ao servidor a cada 5 segundos e publicar valores de luminosidade gerados aleatoriamente entre 0 e 100.

O ClienteAtuador se conecta ao servidor a cada 10 segundos e solicita valores de luminosidade. Com base no valor recebido, o atuador decide se deve abrir ou fechar a cortina. Se a luminosidade for maior ou igual a 60, a cortina é aberta. Caso contrário, se a luminosidade for menor que 60, a cortina é fechada. O estado atual da cortina é impresso na tela do ClienteAtuador.

O Servidor recebe as solicitações dos clientes e trata cada conexão individualmente. Ele armazena os valores de luminosidade em um dicionário, associando-os a tópicos específicos. O servidor suporta duas operações:

- **PUBLICAR, TOPICO, VALOR:** O cliente envia uma string contendo três informações separadas por vírgula. O servidor armazena o valor fornecido no tópico correspondente.

- **ASSINAR, TOPICO:** O cliente envia uma string contendo duas informações separadas por vírgula. O servidor responde com o valor associado ao tópico solicitado.

## Como Rodar a Aplicação

Siga as instruções abaixo para executar a aplicação:

1. Certifique-se de ter o JDK (Java Development Kit) instalado em seu sistema.

2. Faça o download dos arquivos do projeto.

3. Abra um terminal ou prompt de comando e navegue até o diretório onde os arquivos estão localizados.

4. Compile os arquivos Java executando os seguintes comandos:

```shell
javac Servidor.java
javac TrataCliente.java
javac ClienteSensor.java
javac ClienteAtuador.java
```

5. Inicie o servidor executando o seguinte comando:

```shell
java Servidor
```

6. Em outro terminal ou prompt de comando, inicie o ClienteSensor executando o seguinte comando:

```shell
java ClienteSensor
```

7. Em um terceiro terminal ou prompt de comando, inicie o ClienteAtuador executando o seguinte comando:

```shell
java ClienteAtuador
```

O sistema de comunicação agora está em execução. O ClienteSensor publicará valores de luminosidade aleatórios, e o ClienteAtuador solicitará esses valores e controlará a abertura e o fechamento da cortina com base na luminosidade recebida.

Certifique-se de que o endereço IP e a porta do servidor (127.0.0.1:3333) estejam corretos nos arquivos `ClienteSensor.java` e `ClienteAtuador.java`.

## Executando a partir de uma IDE ou Visual Studio Code

Se você estiver usando uma IDE como Eclipse, IntelliJ IDEA ou o Visual Studio Code com a extensão Java instalada, você também pode executar a aplicação diretamente da IDE. Basta importar os arquivos do projeto e executar a classe Servidor, ClienteSensor e ClienteAtuador individualmente.

## Observações

- O intervalo de tempo para publicação de valores de luminosidade pelo ClienteSensor e para solicitação de valores pelo ClienteAtuador pode ser ajustado alterando as constantes `INTERVALO` nos respectivos arquivos.

- O servidor armazena os valores de luminosidade em um dicionário, representado pela variável `valoresTopicos` na classe `Servidor.java`. Você pode modificar ou expandir esse dicionário conforme necessário para suportar outros tópicos.

- Certifique-se de ter as bibliotecas necessárias para a execução do código, como a biblioteca Java Socket.

- Este sistema foi desenvolvido para a Atividade Prática 2 da disciplina de Redes de Computadores.
