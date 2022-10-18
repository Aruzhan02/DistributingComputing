package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Simple web server.
 */
public class WebServer {
    public static void main(String[] args) {
        // Port number for http request
        int port = args.length > 1 ? Integer.parseInt(args[1]) : 8080;
        // The maximum queue length for incoming connection
        int queueLength = args.length > 2 ? Integer.parseInt(args[2]) : 50;;

        try (ServerSocket serverSocket = new ServerSocket(port, queueLength)) {
            System.out.println("Web Server is starting up, listening at port " + port + ".");
            System.out.println("You can access http://localhost:" + port + " now.");

            while (true) {
                // Make the server socket wait for the next client request
                Socket socket = serverSocket.accept();

                System.out.println("Got connection!");

                // To read input from the client
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

                try {
                    // Get request
                    HttpRequest request = HttpRequest.parse(input);
                    String data = request.getRequestLine();
                    String data1 = data.substring(5,11);
                    String data4 = data.substring(5,9);
                    if(data1.equals("create")){
                        String name_of_the_file1 = data.substring(12,(data.length()-9));
                        System.out.println(name_of_the_file1);
                        createFile(name_of_the_file1);
                        Processor proc1 = new Processor(socket, request);
                        proc1.create();
                    }

                    if(data1.equals("delete")){
                        String name_of_the_file1 = data.substring(12,(data.length()-9));
                        System.out.println(name_of_the_file1);
                        deleteFile(name_of_the_file1);
                        Processor proc2  = new Processor(socket, request);
                        proc2.delete();
                    }
                    if(data4.equals("exec")){
                        computeFile();
                        Processor proc3 = new Processor(socket, request);
                        proc3.compute();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            System.out.println("Server has been shutdown!");
        }
    }
    private static void createFile(String name_of_the_file1) {
        try {
            File myObj = new File(name_of_the_file1);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    private static void deleteFile(String name_of_the_file1) throws IOException {
        Files.delete(Paths.get(name_of_the_file1));
    }

    private static void computeFile() throws Exception {
        Encode.mainFunction();
        Decode.main();

    }





}
