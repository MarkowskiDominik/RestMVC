package pl.spring.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.Map;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String bookList(Map<String, Object> params, @ModelAttribute("title") String title,
			@ModelAttribute("authors") String authors) {
		params.put("books", bookService.findBooksByTitleAndAuthor(title, authors));
		return "bookList";
	}
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBookPage(Map<String, Object> params, @ModelAttribute("newBook") BookTo bookTo) {
    	return "addBook";
    }

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addBook(Map<String, Object> params, @ModelAttribute("newBook") BookTo bookTo) {
	   	bookService.saveBook(bookTo);
		return "redirect:/books";
	}

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String confirmedDeletionPage(Map<String, Object> params, @PathVariable("id") Long id) {
        params.put("deleteBook", bookService.getOne(id).getTitle());
    	bookService.deleteBook(bookService.getOne(id));
        return "confirmedDeletion";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateBookpage(Map<String, Object> params, @PathVariable("id") Long id) {
    	params.put("updateBook", bookService.getOne(id));
        return "updateBook";
    }

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateBook(Map<String, Object> params, @ModelAttribute("updateBook") BookTo bookTo) {
	   	bookService.updateBook(bookTo);
		return "redirect:/books";
	}
	
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String error404() {
		return "error404";
	}
}
