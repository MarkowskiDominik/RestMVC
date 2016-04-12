package pl.spring.demo.web.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

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

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public List<BookTo> findBooksByCriteria(@RequestBody BookTo book) {
        return bookService.findBooksByTitleAndAuthor(book.getTitle(), book.getAuthors());
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public BookTo saveBook(@RequestBody BookTo book) {
        return bookService.saveBook(book);
    }

	@RequestMapping(value = "/book", method = { RequestMethod.PATCH, RequestMethod.PUT })
	public BookTo updateBook(@RequestBody BookTo book, Map<String, Object> parameters) {
		return bookService.updateBook(book);
	}

    @RequestMapping(value = "/book", method = RequestMethod.DELETE)
    public BookTo deleteBook(@RequestBody BookTo book) {
    	return bookService.deleteBook(book);
    }
}
