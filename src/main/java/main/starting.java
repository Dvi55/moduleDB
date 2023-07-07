package main;

import dao.AbonentDAO;
import org.apache.maven.cli.MavenCli;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class starting {

    public static void main(String[] args) {
        MavenCli cli = new MavenCli();
        cli.doMain(new String[]{"compile"}, System.getProperty("user.dir"), System.out, System.err);
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/moduledb", "flyway", "111")
                .locations("classpath:db/migrations")
                .load();
        flyway.migrate();
        System.out.println("Welcome to DB console.");
        Scanner scanner = new Scanner(System.in);
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        AbonentDAO abonentDAO = new AbonentDAO(sessionFactory);
        int command;
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1. Take TOP-5 abonents which consume most of calls, sms, internet.");
            System.out.println("2. Find out most popular service.");
            System.out.println("3. Find out most popular device which is used on the network.");
            System.out.println("4. Search through sms storage by any combination of words");
            System.out.println("5. Exit");
            command = scanner.hasNextInt() ? scanner.nextInt() : -1;
            scanner.nextLine();
            if (command == -1) {
                System.out.println("Wrong input. Try again.");
                continue;
            }
            if (command == 1) {
                System.out.println("Top abonent by minutes of calls: " + abonentDAO.getTopAbonentByMinutesOfCalls());
                System.out.println("Top abonent by count of sms: " + abonentDAO.getTopAbonentBySMSCount());
                System.out.println("Top abonent by internet usage: " + abonentDAO.getTopAbonentByUsingNetwork());
            } else if (command == 2) {
                System.out.println("Most popular service: " + abonentDAO.getMostPopularActivity());
            } else if (command == 3) {
                System.out.println("Most popular device: " + abonentDAO.getMostPopularDevice());
            } else if (command == 4) {
                System.out.println("Enter words to search: ");
                String words = scanner.next();
                if (abonentDAO.containsSMSWithKeyword(words)) {
                    System.out.println("Abonent, who sent sms with keyword: " + abonentDAO.searchSMSByKeyword(words));
                } else {
                    System.out.println("There is no sms with such keyword.");
                }
            }
            if (command == 5) {
                System.out.println("Goodbye! See you soon");
                break;
            }
        }
    }
}