package com.example.app;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class App {
    public static String message() {
        return "Java DevSecOps application is running!";
    }

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(
            System.getenv().getOrDefault("PORT", "8080")
        );

        HttpServer server = HttpServer.create(
            new InetSocketAddress("0.0.0.0", port), 0
        );

        server.createContext("/", exchange -> {
            byte[] response = message().getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set(
                "Content-Type", "text/plain; charset=UTF-8"
            );
            exchange.sendResponseHeaders(200, response.length);

            try (var output = exchange.getResponseBody()) {
                output.write(response);
            }
        });

        server.start();
        System.out.println("Application started on port " + port);
    }
}
