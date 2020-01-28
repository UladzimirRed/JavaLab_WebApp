package com.epam.lab.xxx;

import com.epam.lab.configuration.ApplicationDataConfig;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationDataConfig.class);
        AuthorDao authorDao = context.getBean(AuthorDao.class);
        System.out.println("List of authors is: ");
        for (Author author : authorDao.getAllPerson()){
            System.out.println(author);
        }
        System.out.println("\nGet person with id 0");
        Author authorById = authorDao.getAuthorById(0L);
        System.out.println(authorById);
        context.close();
    }
}
