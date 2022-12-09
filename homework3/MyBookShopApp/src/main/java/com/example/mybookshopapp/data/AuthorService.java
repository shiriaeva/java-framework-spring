package com.example.mybookshopapp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.*;

@Service
public class AuthorService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Set<Author>> getAuthorData(){
        Map<String, Set<Author>> map = new HashMap<>();
        List<Author> authors = jdbcTemplate.query("SELECT * FROM authors", (ResultSet rs, int rowInt) -> {
            Author author = new Author();
            author.setId(rs.getInt("id"));
            author.setFirst_name(rs.getString("first_name"));
            author.setLast_name(rs.getString("last_name"));
            return author;
        });
        for(Author author: authors){
            String lastName = author.getLast_name().toUpperCase(Locale.ROOT);
            String firstLetter = String.valueOf(lastName.charAt(0));
            if(!map.containsKey(firstLetter)){
                map.put(firstLetter, new TreeSet<>());
            }
            map.get(firstLetter).add(author);
        }
        return map;
    }
}
