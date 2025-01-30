package edu.escuelaing.arep;

import java.io.*;
import java.net.*;

/**
 * Servidor HTTP simple que escucha en un puerto específico y atiende solicitudes de clientes.
 * Se encarga de aceptar conexiones entrantes y delegarlas a un manejador de solicitudes.
 */
public class HttpServer {

    /** Puerto en el que el servidor escuchará las solicitudes entrantes. */
    private static final int PORT = 35000;

    /** Directorio base donde se encuentran los archivos estáticos del servidor. */
    private static final String BASE_DIRECTORY = "src/main/resources/static";

    /**
     * Metodo principal que inicia el servidor HTTP.
     * Crea un socket de servidor y entra en un bucle infinito aceptando conexiones de clientes.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en este caso).
     * @throws IOException Si ocurre un error al iniciar el servidor o al aceptar conexiones.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor escuchando en el puerto " + PORT);

        boolean running = true;
        while (running) {
            // Acepta una conexión entrante de un cliente
            Socket clientSocket = serverSocket.accept();

            RequestHandler.handleClient(clientSocket);
        }
    }
}
