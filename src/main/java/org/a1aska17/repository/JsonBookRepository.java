package org.a1aska17.repository;

import org.a1aska17.model.Book;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class JsonBookRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("Books.json");

    public void saveToJsonFile(List<Book> books) {
        try {
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, books);
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
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.readValue(file, new TypeReference<>() {});
        } catch (IOException ex) {
            out.println("Error upon reading: " + ex);
        }
        return books;
    }
}
