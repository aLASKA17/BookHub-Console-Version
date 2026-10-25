package org.a1aska17.repository;

import org.a1aska17.model.Book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class JsonBookRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("Books.json");

    public void saveToJsonFile(Book book) {
        try {
            List<Book> books = readFromJsonFile();
            books.add(book);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, books);
            out.println("Book save!");
        } catch (IOException ex) {
            out.println("Error upon saving: " + ex);
        }
    }

    public List<Book> readFromJsonFile() {
        List<Book> books = new ArrayList<>();
        try {
            if (!file.exists() || file.length() == 0) {
                return books;
            }
            return mapper.readValue(file, new TypeReference<>() {});
        } catch (IOException ex) {
            out.println("Error upon reading: " + ex);
        }
        return books;
    }
}
