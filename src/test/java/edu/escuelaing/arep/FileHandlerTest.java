package edu.escuelaing.arep;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.*;

class FileHandlerTest {

    @Test
    void testServeExistingFile() throws IOException {
        // Crear un archivo temporal en el directorio de prueba
        String testContent = "Hello, this is a test file.";
        Path testFilePath = Paths.get("src/main/resources/static/test.html");
        Files.createDirectories(testFilePath.getParent());
        Files.writeString(testFilePath, testContent);

        // Capturar la salida del archivo servido
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        FileHandler.serveFile("/test.html", outputStream);

        // Convertir la salida a string
        String response = outputStream.toString();

        // Verificar que la respuesta contiene HTTP 200 OK y el contenido del archivo
        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.contains("Content-Type: text/html"));
        assertTrue(response.contains(testContent));

        // Limpiar el archivo de prueba
        Files.deleteIfExists(testFilePath);
    }

    @Test
    void testServeNonExistingFile() throws IOException {
        // Capturar la salida de la respuesta 404
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        FileHandler.serveFile("/nonexistent.html", outputStream);

        // Convertir la salida a string
        String response = outputStream.toString();

        // Verificar que la respuesta contiene HTTP 404 Not Found
        assertTrue(response.contains("HTTP/1.1 404 Not Found"));
        assertTrue(response.contains("404 Not Found"));
    }

    @Test
    void testGetContentType() {
        assertEquals("text/html", FileHandler.getContentType("index.html"));
        assertEquals("text/css", FileHandler.getContentType("styles.css"));
        assertEquals("text/javascript", FileHandler.getContentType("script.js"));
        assertEquals("image/png", FileHandler.getContentType("image.png"));
        assertEquals("image/jpeg", FileHandler.getContentType("photo.jpg"));
        assertEquals("application/octet-stream", FileHandler.getContentType("unknown.xyz"));
    }
}

