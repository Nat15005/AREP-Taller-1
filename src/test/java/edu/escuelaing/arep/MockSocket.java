package edu.escuelaing.arep;

import java.io.*;
import java.net.*;

public class MockSocket extends Socket {
    private final String request;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    public MockSocket(String request) {
        // Asegurarse de que la solicitud esté correctamente formateada, con un final de línea
        this.request = request + "\r\n\r\n";  // Agregar un salto de línea vacío al final
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(request.getBytes());
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return outputStream;
    }

    public String getResponse() {
        return outputStream.toString();
    }
}
