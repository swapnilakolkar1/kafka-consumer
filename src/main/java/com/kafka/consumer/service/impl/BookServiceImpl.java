package com.kafka.consumer.service.impl;

import com.kafka.consumer.entities.Book;
import com.kafka.consumer.repo.BookRepository;
import com.kafka.consumer.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepo;

	@Override
	public boolean saveBook(Book book) {
		Book save = bookRepo.save(book);
		return save.getId() != null;
	}
}
