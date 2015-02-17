package fr.iutinfo;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.ResponseTransformer;

/**
 * A simple CRUD example showing howto create, get, update and delete book resources.
 */
public class Books {

    /**
     * Map holding the books
     */
    protected static Map<String, Book> books = new HashMap<String, Book>();

    public static void main(String[] args) {
        final Random random = new Random();
        final ResponseTransformer jsonTransform = new JsonTransformer();

        Book b = new Book("Herman Hesse","Le jeu des perles de verre");
        books.put(""+b.getId(), b);

        staticFileLocation("/public");

        // Creates a new book resource, will return the ID to the created resource
        // author and title are sent as query parameters e.g. /books?author=Foo&title=Bar
        post("/books", "application/json", (request, response) -> {
                String author = request.queryParams("author");
                String title = request.queryParams("title");
                Book book = new Book(author, title);
                books.put(""+book.getId(), book);
                response.status(201); // 201 Created
                return book;
        }, jsonTransform);

        // Gets the book resource for the provided id
        get("/books/:id", "application/json", (request, response) -> {
                String id = request.params(":id");
                Book book = books.get(id);
                if (book != null) {
                    return book;
                } else {
                    response.status(404); // 404 Not found
                    return "{ error: 'Book with id="+id+" not found' }";
                }
        }, jsonTransform);

        // Updates the book resource for the provided id with new information
        // author and title are sent as query parameters e.g. /books/<id>?author=Foo&title=Bar
        put("/books/:id", "application/json", (request, response) -> {
                String id = request.params(":id");
                Book book = books.get(id);
                if (book != null) {
                    String newAuthor = request.queryParams("author");
                    String newTitle  = request.queryParams("title");
                    if (newAuthor != null) {
                        book.setAuthor(newAuthor);
                    }
                    if (newTitle != null) {
                        book.setTitle(newTitle);
                    }
                    return "{ status: sucess, message: 'Book with id=" + id + "' updated ! }";
                } else {
                    response.status(404); // 404 Not found
                    return "{ error: 'Book with id="+id+" not found ! }";
                }
        });

        // Deletes the book resource for the provided id 
        delete("/books/:id", "application/json", (request, response) -> {
                String id = request.params(":id");
                Book book = books.remove(id);
                if (book != null) {
                    return "{ deleted: "+ id +"}";
                } else {
                    response.status(404); // 404 Not found
                    return "{error: 'Book with id="+id+" not found !' }";
                }
        });

        // Gets all available book resources (id's)
        get("/books", "application/json", (request, response) -> {
                //String ids = "";
                //for (String id : books.keySet()) {
                //   ids += id + " "; 
                //}
                //return ids;
                return books.values();
        }, jsonTransform);

    }

}

