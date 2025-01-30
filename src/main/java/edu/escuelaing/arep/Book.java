package edu.escuelaing.arep;

/**
 * Esta clase representa un libro, con su título y autor.
 * Proporciona métodos para acceder a la información del libro y representarlo como una cadena JSON.
 */
public class Book {

    /** El título del libro */
    private String title;

    /** El autor del libro */
    private String author;

    /**
     * Constructor para crear una instancia de la clase Book con un título y un autor.
     *
     * @param title El título del libro.
     * @param author El autor del libro.
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Obtiene el título del libro.
     *
     * @return El título del libro.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Obtiene el autor del libro.
     *
     * @return El autor del libro.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Devuelve una representación del libro en formato JSON.
     *
     * @return Una cadena de texto representando el libro en formato JSON.
     */
    @Override
    public String toString() {
        return "{\"title\":\"" + title + "\", \"author\":\"" + author + "\"}";
    }
}
