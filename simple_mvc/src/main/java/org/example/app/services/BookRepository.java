package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class BookRepository implements ProjectRepository<Book> {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retrieveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(book.hashCode());
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(Integer bookIdToRemove) {
        for(Book book : retrieveAll()){
            if (book.getId().equals(bookIdToRemove)) {
                logger.info("remove book completed: " + book);
                return repo.remove(book);
            }
        }
        return false;
    }

    @Override
    public boolean removeItemByRegex(String queryRegex) {
        if (!queryRegex.isEmpty()){
            Pattern p = Pattern.compile(queryRegex);
            for(Book book : retrieveAll()){
                Matcher author_matcher = p.matcher(book.getAuthor());
                Matcher title_matcher = p.matcher(book.getTitle());
                Matcher size_matcher = p.matcher(book.getSize().toString());

                if (author_matcher.find() || title_matcher.find() || size_matcher.find()){
                    logger.info("remove book by regex completed: " + book);
                    repo.remove(book);
                }
            }
            return true;
        }
        return false;
    }
}
