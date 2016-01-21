package pl.spring.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    

    @RequestMapping(value = "/books/remove/{bookId}/", method = RequestMethod.GET)
    public String bookRemove(Map<String, Object> params, @PathVariable("bookId") long bookId) {
        BookTo bookToDelete = bookService.getBook(bookId);
        params.put("bookToDelete", bookToDelete);
        return "confirmation";
    }
    
    @RequestMapping(value = "/books/remove/", method = RequestMethod.DELETE)
    public String confirmBookRemove(Map<String, Object> params, @RequestBody BookTo book) {
        Long bookId = book.getId();
        if(bookId != 0) {
            bookService.deleteBook(bookId);
            bookId = 0L;
        }
        return bookList(params);
    }
    
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String bookList(Map<String, Object> params) {
        final List<BookTo> allBooks = bookService.findAllBooks();
        params.put("books", allBooks);
        return "bookList";
    }
    
    @RequestMapping(value = "/books/", method = RequestMethod.POST)
    public String addBook(Map<String, Object> params, @RequestParam("title") String title, @RequestParam("authors") String authors) {
        if(!authors.isEmpty() || !title.isEmpty()) {
            bookService.saveBook(title, authors);
            title = null;
            authors = null;
        }
        return bookList(params);
    }
}
