package edu.escuelaing.arep;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Clase encargada de manejar la lectura y envío de archivos en el servidor HTTP.
 * Permite servir archivos estáticos desde un directorio base y determinar su tipo MIME.
 */
public class FileHandler {

    /** Directorio base donde se encuentran los archivos estáticos del servidor. */
    private static final String BASE_DIRECTORY = "src/main/resources/static";

    /**
     * Sirve un archivo solicitado por el cliente y lo envía a través del flujo de salida.
     * Si el archivo no existe, responde con un error 404.
     *
     * @param resource El recurso solicitado (ruta del archivo).
     * @param out      El flujo de salida donde se enviará la respuesta HTTP.
     * @throws IOException Si ocurre un error al leer el archivo o escribir en el flujo de salida.
     */
    public static void serveFile(String resource, OutputStream out) throws IOException {
        if (resource.equals("/")) {
            resource = "/index.html";
        }

        Path filePath = Path.of(BASE_DIRECTORY + resource);
        if (!Files.exists(filePath)) {
            sendNotFound(out);
            return;
        }

        String contentType = getContentType(resource);
        byte[] fileBytes = Files.readAllBytes(filePath);

        String responseHeader = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: " + contentType + "\r\n" +
                "Content-Length: " + fileBytes.length + "\r\n" +
                "\r\n";

        out.write(responseHeader.getBytes());
        out.write(fileBytes);
    }

    /**
     * Envía una respuesta HTTP 404 Not Found al cliente.
     *
     * @param out El flujo de salida donde se enviará la respuesta.
     * @throws IOException Si ocurre un error al escribir en el flujo de salida.
     */
    private static void sendNotFound(OutputStream out) throws IOException {
        String response = "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/plain\r\n" +
                "\r\n" +
                "404 Not Found";
        out.write(response.getBytes());
    }

    /**
     * Determina el tipo de contenido (MIME type) de un archivo basado en su extensión.
     *
     * @param fileName El nombre del archivo.
     * @return El tipo de contenido correspondiente a la extensión del archivo.
     *         Si la extensión no está definida, retorna "application/octet-stream".
     */
    private static String getContentType(String fileName) {
        HashMap<String, String> mimeTypes = new HashMap<>();
        mimeTypes.put("html", "text/html");
        mimeTypes.put("css", "text/css");
        mimeTypes.put("js", "text/javascript");
        mimeTypes.put("png", "image/png");
        mimeTypes.put("jpg", "image/jpeg");

        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return mimeTypes.getOrDefault(extension, "application/octet-stream");
    }
}
