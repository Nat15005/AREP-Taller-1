package edu.escuelaing.arep;

import org.junit.jupiter.api.*;
import java.io.*;
import java.net.*;
import static org.junit.jupiter.api.Assertions.*;

class RequestHandlerTest {

    @Test
    void testGetBooks() throws IOException {
        // Simular una solicitud GET a /getBooks
        Socket mockSocket = new MockSocket("GET /getBooks HTTP/1.1\r\n\r\n");
        RequestHandler.handleClient(mockSocket);

        // Verificar que la respuesta contiene los libros en formato JSON
        MockSocket mockClient = (MockSocket) mockSocket;
        String response = mockClient.getResponse();

        assertTrue(response.contains("HTTP/1.1 200 OK"));
        assertTrue(response.contains("Content-Type: application/json"));
    }

    @Test
    void testPostAddBook() throws IOException {
        // Simular una solicitud POST a /addBook
        String bookJson = "{\"title\":\"Test Book\", \"author\":\"Test Author\"}";
        Socket mockSocket = new MockSocket("POST /addBook HTTP/1.1\r\nContent-Type: application/json\r\nContent-Length: " + bookJson.length() + "\r\n\r\n" + bookJson);
        RequestHandler.handleClient(mockSocket);

        // Verificar que la respuesta contenga el libro añadido
        MockSocket mockClient = (MockSocket) mockSocket;
        String response = mockClient.getResponse();

        assertTrue(response.contains("Libro añadido"));
        assertTrue(response.contains("Test Book"));
    }

    @Test
    void testDeleteBook() throws IOException {
        // Añadir un libro antes de probar
        String bookJson = "{\"title\":\"Delete Test Book\", \"author\":\"Test Author\"}";
        Socket mockSocket = new MockSocket("POST /addBook HTTP/1.1\r\nContent-Type: application/json\r\nContent-Length: " + bookJson.length() + "\r\n\r\n" + bookJson);
        RequestHandler.handleClient(mockSocket);

        // Simular una solicitud DELETE a /deleteBook
        String deleteJson = "{\"title\":\"Delete Test Book\"}";
        Socket deleteSocket = new MockSocket("DELETE /deleteBook HTTP/1.1\r\nContent-Type: application/json\r\nContent-Length: " + deleteJson.length() + "\r\n\r\n" + deleteJson);
        RequestHandler.handleClient(deleteSocket);

        // Verificar la respuesta después de la eliminación
        MockSocket mockClient = (MockSocket) deleteSocket;
        String response = mockClient.getResponse();

        assertTrue(response.contains("Libro eliminado"));
        assertTrue(response.contains("Delete Test Book"));
    }

    @Test
    void testBadRequest() throws IOException {
        String invalidRequest = "INVALID REQUEST";  // Solicitud malformada
        MockSocket mockSocket = new MockSocket(invalidRequest);
        RequestHandler.handleClient(mockSocket);  // Procesar la solicitud

        // Verificar que la respuesta sea un 400 Bad Request
        String response = mockSocket.getResponse();
        assertTrue(response.contains("HTTP/1.1 400 Bad Request"));
    }




}
