package edu.escuelaing.arep;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    void testBookCreation() {
        Book book = new Book("1984", "George Orwell");
        assertNotNull(book, "El objeto Book no debe ser nulo");
        assertEquals("1984", book.getTitle(), "El título debe ser '1984'");
        assertEquals("George Orwell", book.getAuthor(), "El autor debe ser 'George Orwell'");
    }

    @Test
    void testToString() {
        Book book = new Book("Cien años de soledad", "Gabriel García Márquez");
        String expectedJson = "{\"title\":\"Cien años de soledad\", \"author\":\"Gabriel García Márquez\"}";
        assertEquals(expectedJson, book.toString(), "La representación JSON del libro no es la esperada");
    }
}
