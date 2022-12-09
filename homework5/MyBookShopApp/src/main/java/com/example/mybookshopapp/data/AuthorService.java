package com.example.mybookshopapp.data;

import com.example.mybookshopapp.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Map<String, Set<Author>> getAuthorData(){
        Map<String, Set<Author>> map = new HashMap<>();
        List<Author> authors = authorRepository.findAll();
        for(Author author: authors){
            String firstLetter = author.getName().toUpperCase(Locale.ROOT).substring(0,1);
            if(!map.containsKey(firstLetter)){
                map.put(firstLetter, new TreeSet<>());
            }
            map.get(firstLetter).add(author);
        }
        return map;
    }
}
