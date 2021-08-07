package com.myrestapi.api.controllers;

import com.myrestapi.api.models.Book;
import com.myrestapi.api.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @Controller
@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    // @RequestMapping(value = "/books", method = RequestMethod.GET)
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBook()
    {
        List<Book> list= this.bookService.getAllBook();
        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id)
    {
        Book book= bookService.getBookById(id);
        if(book==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book)
    {
        Book b= null;
        try{
            b= this.bookService.addBook(book);
            return ResponseEntity.of(Optional.of(b));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id)
    {
        try {
            this.bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping("/books/{id}")
    public ResponseEntity<Void> updateBook(@RequestBody Book book, @PathVariable("id") int id)
    {
        try {
            this.bookService.updateBook(book, id);
            return ResponseEntity.ok().build();
        }
        catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
