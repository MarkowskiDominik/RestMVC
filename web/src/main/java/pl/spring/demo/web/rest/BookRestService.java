package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class BookRestService {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books-by-title/{titlePrefix}", method = RequestMethod.GET)
    public List<BookTo> findBooksByTitle(@PathVariable("titlePrefix") String titlePrefix) {
        return bookService.findBooksByTitle(titlePrefix);
    }
    
    @RequestMapping(value = "/books-by-author/{authorPrefix}", method = RequestMethod.GET)
    public List<BookTo> findBooksByAuthor(@PathVariable("authorPrefix") String authorPrefix) {
    	return bookService.findBooksByAuthor(authorPrefix);
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public BookTo saveBook(@RequestBody BookTo book) {
        return bookService.saveBook(book);
    }
    
    @RequestMapping(value = "/book", method = RequestMethod.PATCH)
    public BookTo updateBook(@RequestBody BookTo book, Map<String, Object> parameters) {
        parameters.put("updateBook", book);
    	return bookService.updateBook(book);
    }
    
    @RequestMapping(value = "/book", method = RequestMethod.DELETE)
    public void deleteBook(@RequestBody BookTo book) {
    	bookService.deleteBook(book);
    }
}
