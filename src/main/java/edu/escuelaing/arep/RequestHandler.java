package edu.escuelaing.arep;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Clase encargada de manejar las solicitudes HTTP recibidas por el servidor.
 * Procesa solicitudes GET, POST y DELETE para la gestión de libros y archivos estáticos.
 */
public class RequestHandler {

    /** Lista de libros almacenados en memoria. */
    private static final List<Book> books = new ArrayList<>();

    /**
     * Maneja la solicitud de un cliente, determinando el tipo de método y recurso solicitado.
     *
     * @param clientSocket Socket del cliente que realizó la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida durante el manejo de la solicitud.
     */
    public static void handleClient(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        OutputStream out = clientSocket.getOutputStream();

        String requestLine = in.readLine();
        if (requestLine == null || requestLine.trim().isEmpty()) {
            sendBadRequest(out);
            clientSocket.close();
            return;
        }

        System.out.println("Solicitud recibida: " + requestLine);
        String[] requestParts = requestLine.split(" ");
        if (requestParts.length < 2) {
            sendBadRequest(out);
            clientSocket.close();
            return;
        }

        String method = requestParts[0]; // GET, POST o DELETE
        String resource = requestParts[1]; // /getBooks, /addBook, /deleteBook

        // Leer encabezados adicionales
        HashMap<String, String> headers = new HashMap<>();
        String line;
        while (!(line = in.readLine()).isEmpty()) {
            String[] headerParts = line.split(": ", 2);
            if (headerParts.length == 2) {
                headers.put(headerParts[0], headerParts[1]);
            } else {
                // Si un encabezado no está bien formado, respondemos con Bad Request
                sendBadRequest(out);
                clientSocket.close();
                return;
            }
        }

        // Manejo de rutas específicas
        if (method.equals("GET")) {
            if (resource.equals("/getBooks")) {
                serveBooksJson(out);
            } else {
                FileHandler.serveFile(resource, out);
            }
        } else if (method.equals("POST") && resource.equals("/addBook")) {
            handlePost(headers, in, out);
        } else if (method.equals("DELETE") && resource.equals("/deleteBook")) {
            handleDelete(headers, in, out);
        } else {
            // Si el metodo o la ruta no son válidos, respondemos con Bad Request
            sendBadRequest(out);
        }

        out.close();
        in.close();
        clientSocket.close();
    }


    /**
     * Envía la lista de libros en formato JSON como respuesta a una solicitud GET.
     *
     * @param out Stream de salida para enviar la respuesta al cliente.
     * @throws IOException Si ocurre un error de escritura en el flujo de salida.
     */
    private static void serveBooksJson(OutputStream out) throws IOException {
        StringBuilder json = new StringBuilder("{ \"books\": [");
        for (int i = 0; i < books.size(); i++) {
            json.append(books.get(i).toString());
            if (i < books.size() - 1) json.append(", ");
        }
        json.append("] }");

        String responseHeader = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json\r\n" +
                "Content-Length: " + json.length() + "\r\n" +
                "\r\n";

        out.write(responseHeader.getBytes());
        out.write(json.toString().getBytes());
    }

    /**
     * Maneja una solicitud POST para agregar un nuevo libro a la lista.
     *
     * @param headers Encabezados de la solicitud HTTP.
     * @param in      Stream de entrada para leer el cuerpo de la solicitud.
     * @param out     Stream de salida para enviar la respuesta al cliente.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private static void handlePost(HashMap<String, String> headers, BufferedReader in, OutputStream out) throws IOException {
        // Leer el cuerpo de la solicitud
        int contentLength = Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
        char[] body = new char[contentLength];
        in.read(body, 0, contentLength);
        String bodyContent = new String(body);

        // Extraer los valores title y author del cuerpo JSON
        String[] parts = bodyContent.replace("{", "").replace("}", "").replace("\"", "").split(",");
        String title = parts[0].split(":")[1].trim();
        String author = parts[1].split(":")[1].trim();

        if (!title.isEmpty() && !author.isEmpty()) {
            // Crear un nuevo objeto Book y agregarlo a la lista books
            books.add(new Book(title, author));
        }
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/plain\r\n" +
                "\r\n" +
                "Libro añadido: " + title + " por " + author;

        out.write(response.getBytes());
    }

    /**
     * Maneja una solicitud DELETE para eliminar un libro de la lista por su título.
     *
     * @param headers Encabezados de la solicitud HTTP.
     * @param in      Stream de entrada para leer el cuerpo de la solicitud.
     * @param out     Stream de salida para enviar la respuesta al cliente.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private static void handleDelete(HashMap<String, String> headers, BufferedReader in, OutputStream out) throws IOException {
        int contentLength = Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
        if (contentLength == 0) {
            sendBadRequest(out);
            return;
        }

        // Leer el cuerpo de la solicitud
        char[] body = new char[contentLength];
        in.read(body, 0, contentLength);
        String bodyContent = new String(body);

        // Extraer el nombre del libro
        String title = bodyContent.replace("{\"title\":\"", "").replace("\"}", "").trim();

        // Eliminar libro de la lista
        boolean removed = books.removeIf(book -> book.getTitle().equals(title));

        // Construir la respuesta HTTP
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/plain\r\n" +
                "\r\n" +
                (removed ? "Libro eliminado: " + title : "Libro no encontrado");

        out.write(response.getBytes());
    }

    /**
     * Envía una respuesta HTTP con código 400 Bad Request.
     *
     * @param out Stream de salida para enviar la respuesta al cliente.
     * @throws IOException Si ocurre un error de escritura en el flujo de salida.
     */
    private static void sendBadRequest(OutputStream out) throws IOException {
        String response = "HTTP/1.1 400 Bad Request\r\n" +
                "Content-Type: text/plain\r\n" +
                "\r\n" +
                "400 Bad Request";
        out.write(response.getBytes());
    }
}
