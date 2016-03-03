package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<BookTo> findAllBooks() {
		return BookMapper.map2To(bookRepository.findAll());
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		return BookMapper.map2To(bookRepository.findBookByTitle(title));
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		return BookMapper.map2To(bookRepository.findBookByAuthor(author));
	}
	
	@Override
	public List<BookTo> findBooksByTitleAndAuthor(String title, String author) {
		return BookMapper.map2To(bookRepository.findBookByTitleAndAuthor(title, author));
	}

	@Override
	public BookTo getOne(Long id) {
		return BookMapper.map(bookRepository.getOne(id));
	}

	@Override
	@Transactional(readOnly = false)
	public BookTo saveBook(BookTo book) {
		return BookMapper.map(bookRepository.save(BookMapper.map(book)));
	}

	@Override
	@Transactional(readOnly = false)
	public BookTo updateBook(BookTo book) {
		return BookMapper.map(bookRepository.save(BookMapper.map(book)));
	}

	@Override
	@Transactional(readOnly = false)
	public BookTo deleteBook(BookTo book) {
		BookTo result = BookMapper.map(bookRepository.getOne(book.getId()));
		bookRepository.delete(BookMapper.map(book));
		return result;
	}
}
