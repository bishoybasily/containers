package com.gmail.bishoybasily.containers;

import java.io.*;
import java.net.ServerSocket;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.printf("Got: %s%n", Arrays.toString(args));

        var main = new Main();


        if (args.length > 0) {
            switch (args[0]) {
                case "server":
                    main.server(Integer.parseInt(args[1]));
                    return;
                case "echo":
                    if (args.length == 4 && args[2].equals(">")) {
                        main.write(args[1], args[3]);
                    }
                    return;
                case "print":
                    System.getenv().forEach((k, v) -> {
                        System.out.printf("%s=%s%n", k, v);
                    });
                    break;
            }
        }

    }

    private void write(String content, String path) throws Exception {

        var file = new File(path);
        file.getParentFile().mkdirs();
        var writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));

        writer.write(content);
        writer.newLine();
        writer.flush();
        writer.close();

    }

    private void server(int port) throws Exception {

        var server = new ServerSocket(port);
        System.out.printf("Server started and listening on port: %d%n", port);
        var client = server.accept();
        System.out.printf("New client connected%n");

        var reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        var writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        var line = reader.readLine();

        writer.write(String.format("Thank you for sending me (%s)", line));
        writer.newLine();
        writer.flush();

        System.out.printf("Cleaning up...%n");

        reader.close();
        writer.close();
        client.close();
        server.close();

    }

}
