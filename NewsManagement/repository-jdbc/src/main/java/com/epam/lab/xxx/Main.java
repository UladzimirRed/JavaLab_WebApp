package com.epam.lab.xxx;

import com.epam.lab.configuration.ApplicationDataConfig;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.repository.NewsDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationDataConfig.class);
        AuthorDao authorDao = context.getBean(AuthorDao.class);
        NewsDao newsDao = context.getBean(NewsDao.class);

//        System.out.println("\nCreating person: ");
//        Author author = new Author("Marvin", "Carroll");
//        authorDao.createAuthor(author);

        System.out.println("\nGet person with id 1");
        Author authorById = authorDao.getAuthorById(1L);
        System.out.println(authorById);

        //        Author author1 = authorDao.getAuthorById(4L);
//        author1.setAuthorName("Margot ");
//        author1.setAuthorSurname("Archer");
//        authorDao.updateAuthor(author1);
//        System.out.println("\nList of person is:");
//        for (Author a : authorDao.getAllAuthors()) {
//            System.out.println(a);
//        }

//        System.out.println("\nDeleting author with ID 6");
//        Author author1 = authorDao.getAuthorById(6L);
//        authorDao.deleteAuthor(author1);

        System.out.println("\nList of authors is: ");
        for (Author a : authorDao.getAllAuthors()){
            System.out.println(a);
        }




        String title = "Hundreds of fishermen stranded on gigantic ice floe";
        String shortText = "Over 500 ice fishermen have been saved in an emergency operation " +
                "when a fissure occurred about 2 kilometers from the coast, stranding them. " +
                "People tried using smaller pieces of ice as rafts to row back to shore." ;
        String fullText = "Emergency services in Russia rescued over 500 ice fishermen after they became trapped on a giant " +
                "sheet of floating ice that broke-off an island in far-eastern Siberia, officials said." +
                "The rescue mission took place off the coast of the island of Sakhalin in the Sea of Okhotsk, just north of Japan. " +
                "The operation lasted approximately seven hours.";

        String title2 = "Coronavirus: Timeline of the deadly virus in China and worldwide";
        String shortText2 = "In less than a month, the flu-like 2019-nCoV virus exploded into a major health emergency, " +
                "killing 132 people and infecting nearly 6,000. More than 50 million Chinese are on lockdown as scientists scramble for a vaccine.";
        String fullText2 = "On January 28, Japan and the US are the first countries to evacuate some of their citizens from Wuhan. " +
                "Four of the Japanese passengers are taken to the hospital with fevers on arrival. " +
                "Australia and New Zealand say they will also send planes to bring their citizens home. " +
                "Global cases mount to nearly 6,000 infections, more than the 2002-03 SARS outbreak that killed roughly 800 people.";

        String title3 = "Germany, Europe react to Trump's Middle East peace plan";
        String shortText3 = "Following Trump's release of his Israeli-Palestinian plan, world leaders have responded " +
                "to his proposal of a two-state solution with Israeli settlements. The plan calls for a minimum four-year freeze in settlements.";
        String fullText3 = "Leaders from around the world have responded with mixed reactions to US President Donald Trump's long-awaited Middle East plan unveiled on Tuesday. " +
                "The proposal was released alongside Israeli leader Benjamin Netanyahu in Washington. Palestinian leaders said they were not invited to attend the talks and preemptively rejected the plan." +
                "Trump's proposal made concessions to Palestinians — but under terms that they have previously ruled out, such as accepting West Bank settlements. " +
                "Europe and the UN were lukewarm and cautious after the peace plan was released.";

//        /*Create news*/
//        System.out.println("\nCreating news: ");
//        News news = new News(title3, shortText3, fullText3, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
//        newsDao.createNews(news);

        /*Get news by id*/
        News newsById = newsDao.getNewsById(1L);
        System.out.println("\nNews by id 1 is: \n" + newsById);

        /*Update news*/
        System.out.println("\nUpdate news with ID 3");
        News news1 = newsDao.getNewsById(4L);
        news1.setModificationDate(Timestamp.from(Instant.now()));
        newsDao.updateNews(news1);

//        /*Delete news*/
//        System.out.println("\nDeleting news with ID 2");
//        News news2 = newsDao.getNewsById(2L);
//        newsDao.deleteNews(news2);

        /*Show all news*/
        System.out.println("\nList of news: ");
        for (News n : newsDao.getAllNews()){
            System.out.println(n);
        }

        context.close();
    }
}
