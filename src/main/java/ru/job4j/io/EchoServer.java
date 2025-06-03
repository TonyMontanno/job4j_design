package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    String requestLine = input.readLine();
                    System.out.println(requestLine);

                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());

                    if (requestLine.contains("GET /?msg=Hello")) {
                        output.write("Hello, dear friend.".getBytes());
                    } else if (requestLine.contains("GET /?msg=Exit")) {
                        System.out.println("Завершение работы сервера по запросу клиента.");
                        output.write("Завершение работы сервера по запросу клиента,  через ключевое слово 'Exit'".getBytes());
                        output.flush();
                        server.close();
                        break;
                    } else {
                        int msgIndex = requestLine.indexOf("msg=") + 4;
                        String msgValue = requestLine.substring(msgIndex).split(" ")[0];
                        output.write(msgValue.getBytes());
                    }

                    output.flush();

                    String headerLine;
                    while ((headerLine = input.readLine()) != null && !headerLine.isEmpty()) {
                        System.out.println(headerLine);
                    }
                }
            }
        } catch (IOException e) {
            LOG.error("Ошибка при запуске сервера", e);
        }
    }
}