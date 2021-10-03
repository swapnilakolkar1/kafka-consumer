package com.kafka.consumer.service;

import com.kafka.consumer.entities.Book;

public interface BookService {
    public boolean saveBook(Book book);
}
