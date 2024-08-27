package ru.solomka.client.controller.http;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@AllArgsConstructor
@Getter
public class ConnectionSocket {
    private final String host;
    private final int port;

    public Server server() {
        return new Server(port);
    }

    public Client client() {
        return new Client(host, port);
    }

    public static class Server {

        private final ServerSocket socket;
        private final BufferedReader in;
        private final BufferedWriter out;

        private Server(int port) {

            Socket connectionSocket;
            try {
                this.socket = new ServerSocket(port);
                socket.setSoTimeout(10000);
                System.out.println("server started on port " + port);
                connectionSocket = this.socket.accept();

                System.out.println("accepted connection from " + connectionSocket.getRemoteSocketAddress());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                this.in = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                this.out = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void await() {
            String word;
            try {
                word = this.in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(word);
            try {
                out.write("Привет, это Сервер! Подтверждаю, вы написали: " + word + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void terminate() {
            try {
                this.socket.close();
                this.in.close();
                this.out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @NotNull
        @Contract("_ -> new")
        public static Server create(int port) {
            return new Server(port);
        }
    }

    public static class Client {

        private final Socket socket;
        private final BufferedReader reader;
        private final BufferedReader in;
        private final BufferedWriter out;

        private Client(String host, int port) {
            try {
                this.socket = new Socket(host, port);

                System.out.println("client started on port " + port);
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            this.reader = new BufferedReader(new InputStreamReader(System.in));
        }

        public void listen() throws IOException {

            System.out.println("Вы что-то хотели сказать? Введите это здесь:");
            // если соединение произошло и потоки успешно созданы - мы можем
            //  работать дальше и предложить клиенту что то ввести
            // если нет - вылетит исключение
            String word = reader.readLine(); // ждём пока клиент что-нибудь
            // не напишет в консоль
            out.write(word + "\n"); // отправляем сообщение на сервер
            out.flush();
            String serverWord = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverWord); // получив - выводим на экран

        }

        public void terminate() {
            try {
                this.socket.close();
                this.in.close();
                this.out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @NotNull
        @Contract("_, _ -> new")
        public static Client create(String host, int port) {
            return new Client(host, port);
        }
    }
}