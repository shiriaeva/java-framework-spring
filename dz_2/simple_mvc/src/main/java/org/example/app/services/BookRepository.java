package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();
    private ApplicationContext context;

    @Override
    public List<Book> retrieveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        book.setId(context.getBean(IdProvider.class).provideId(book));
        logger.info("store new book: " + book);
        repo.add(book);
    }

    @Override
    public boolean removeItemById(String bookIdToRemove) {
        if (!bookIdToRemove.isEmpty()){
            for(Book book : retrieveAll()){
                if (book.getId().equals(bookIdToRemove)) {
                    logger.info("remove book completed: " + book);
                    repo.remove(book);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removeItemByAuthor(String author) {
        if (!author.isEmpty()) {
            for (Book book : retrieveAll()) {
                if (book.getAuthor().contains(author)) {
                    logger.info("remove books by author completed: " + book);
                    repo.remove(book);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removeItemByTitle(String title) {
        if (!title.isEmpty()){
            for (Book book : retrieveAll()) {
                if (book.getTitle().contains(title)) {
                    logger.info("remove books by title completed: " + book);
                    repo.remove(book);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean removeItemBySize(Integer size) {
        if (size != null) {
            for (Book book : retrieveAll()) {
                if (book.getSize().equals(size)) {
                    logger.info("remove books by size completed: " + book);
                    repo.remove(book);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    private void defaultInit() {
        logger.info("default INIT in book repo bean");
    }

    private void defaultDestroy() {
        logger.info("default DESTROY in book repo bean");
    }

}
