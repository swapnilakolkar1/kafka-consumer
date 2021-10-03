package com.kafka.consumer.eventHandlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.entities.Book;
import com.kafka.consumer.service.BookService;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookEventConsumer {
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    BookService bookService;
    
    @KafkaListener(groupId = "topic-book-groupId-1", topics = {"topic-book"})
    public void getBookEventMessage(ConsumerRecord<Integer, String> bookMessage) throws JsonMappingException, JsonProcessingException {
    	String message=bookMessage.value();
        Book book = objectMapper.readValue(message, Book.class);
        log.info("Message consumed book name= " +book.getBookName()+" book id= "+book.getId()+" book author ="+ book.getAuthor() );
        boolean saveBook = bookService.saveBook(book);
        log.info("Book saved Successfully"+String.valueOf(saveBook));
    }
}
