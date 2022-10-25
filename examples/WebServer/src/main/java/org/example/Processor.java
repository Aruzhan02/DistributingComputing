package org.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Processor of HTTP request.
 */
public class Processor {
    private final Socket socket;
    private final HttpRequest request;

    public Processor(Socket socket, HttpRequest request) {
        this.socket = socket;
        this.request = request;
    }

    public void process() throws Exception {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Assignment1</title></head>");

        String data = request.getRequestLine();
        String data1 = data.substring(5, 11);
        String data4 = data.substring(5, 9);
        if (data.equals("GET / HTTP/1.1")) {
            //Thread.sleep(5000);
            Processor proc = new Processor(socket, request);
            proc.firstPage();
        }
        if (data1.equals("create")) {
            String name_of_the_file1 = data.substring(12, (data.length() - 9));
            System.out.println(name_of_the_file1);
            createFile(name_of_the_file1);
            Processor proc1 = new Processor(socket, request);
            proc1.create();
        }

        if (data1.equals("delete")) {
            String name_of_the_file1 = data.substring(12, (data.length() - 9));
            System.out.println(name_of_the_file1);
            deleteFile(name_of_the_file1);
            Processor proc2 = new Processor(socket, request);
            proc2.delete();
        }
        if (data4.equals("exec")) {
            computeFile();
            Processor proc3 = new Processor(socket, request);
            proc3.compute();
        }
        //}
        //inputStream.close();
        output.println("<body><p></p></body>");
        output.println("</html>");
        output.flush();

        socket.close();
    }
    public void firstPage() throws Exception {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());
        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Assignment1</title></head>");
        output.println("<body><p>This server is created by Aruzhan Amanova from IT-2004!</p></body>");
        //output.println("<p>This is check number" + i + "</p>");
        output.println("</html>");
        output.flush();

        socket.close();
    }
    public void create() throws IOException {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Assignment1</title></head>");
        output.println("<body><p>The file was created!</p></body>");
        output.println("</html>");
        output.flush();

        socket.close();
    }

    public void delete() throws IOException {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Assignment1</title></head>");
        output.println("<body><p>The file was deleted!</p></body>");
        output.println("</html>");
        output.flush();

        socket.close();
    }

    public void compute() throws IOException {
        // Print request that we received.
        System.out.println("Got request:");
        System.out.println(request.toString());
        System.out.flush();

        // To send response back to the client.
        PrintWriter output = new PrintWriter(socket.getOutputStream());

        // We are returning a simple web page now.
        output.println("HTTP/1.1 200 OK");
        output.println("Content-Type: text/html; charset=utf-8");
        output.println();
        output.println("<html>");
        output.println("<head><title>Assignment1</title></head>");
        output.println("<body><p>The file was encoded and decoded!</p></body>");
        output.println("</html>");
        output.flush();

        socket.close();
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

    }
}
