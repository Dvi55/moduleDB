package dao;

import models.Abonent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class AbonentDAO {
    private final SessionFactory sessionFactory;

    public AbonentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Abonent getTopAbonentByMinutesOfCalls() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Abonent a ORDER BY a.callActivities.duration DESC";
            Query<Abonent> query = session.createQuery(hql, Abonent.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        }
    }

    public Abonent getTopAbonentBySMSCount() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Abonent a ORDER BY a.smsActivities.messageCount DESC";
            Query<Abonent> query = session.createQuery(hql, Abonent.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        }
    }

    public Abonent getTopAbonentByUsingNetwork() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Abonent a ORDER BY a.networkActivity.megabytes DESC";
            Query<Abonent> query = session.createQuery(hql, Abonent.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        }
    }

    public String getMostPopularActivity() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT SUM(a.callActivities.duration) FROM Abonent a";
            Query<Integer> query = session.createQuery(hql, Integer.class);
            Integer callDuration = query.uniqueResult();
            hql = "SELECT SUM(a.smsActivities.messageCount) FROM Abonent a";
            query = session.createQuery(hql, Integer.class);
            Integer smsCount = query.uniqueResult();
            hql = "SELECT SUM(a.networkActivity.megabytes) FROM Abonent a";
            query = session.createQuery(hql, Integer.class);
            Integer networkMegabytes = query.uniqueResult();
            if (callDuration > smsCount && callDuration > networkMegabytes) {
                return "Call service is the most popular";
            } else if (smsCount > callDuration && smsCount > networkMegabytes) {
                return "SMS service is the most popular";
            } else {
                return "Network service is the most popular";
            }
        }
    }
    public String getMostPopularDevice() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT d.brandName FROM Abonent a JOIN a.devices d GROUP BY d.brandName ORDER BY COUNT(d) DESC";
            Query<String> query = session.createQuery(hql, String.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        }
    }
    public boolean containsSMSWithKeyword(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT COUNT(m) FROM Abonent a JOIN a.messages m WHERE m.text LIKE :keyword";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("keyword", "%" + keyword + "%");
            Long count = query.uniqueResult();
            return count > 0;
        }
    }
    public String searchSMSByKeyword(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Abonent a JOIN a.messages m WHERE m.text LIKE :keyword";
            Query<Abonent> query = session.createQuery(hql, Abonent.class);
            query.setParameter("keyword", "%" + keyword + "%");
            List<Abonent> abonents = query.getResultList();
            StringBuilder result = new StringBuilder();
            for (Abonent abonent : abonents) {
                result.append(abonent.toString()).append("\n");
            }
            return result.toString();
        }
    }
}