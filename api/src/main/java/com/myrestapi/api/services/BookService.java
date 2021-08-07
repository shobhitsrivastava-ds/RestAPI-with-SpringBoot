package com.myrestapi.api.services;

import com.myrestapi.api.models.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService {
    private static List<Book> list= new ArrayList<>();
    static{
        list.add(new Book(12, "Java12", "XYZ12"));
        list.add(new Book(13, "Java13", "XYZ13"));
        list.add(new Book(14, "Java14", "XYZ14"));
        list.add(new Book(15, "Java15", "XYZ15"));
    }
    public List<Book> getAllBook()
    {
        return list;
    }
    public Book getBookById(int id)
    {
        Book book=null;
        try {
            book = list.stream().filter(e -> e.getId() == id).findFirst().get();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return(book);
    }
    public Book addBook(Book b)
    {
        list.add(b);
        return(b);
    }

    public void deleteBook(int id) {
        list= list.stream().filter(book->{
            if(book.getId()!=id)
            {
                return(true);
            }
            else
            {
                return(false);
            }
        }).collect(Collectors.toList());
    }
    public void updateBook(Book book, int id)
    {
        list= list.stream().map(b->{
            if(b.getId()==id) {
                b.setTitle(book.getTitle());
                b.setAuthor(book.getAuthor());
            }
            return(b);
        }).collect(Collectors.toList());
    }
}
