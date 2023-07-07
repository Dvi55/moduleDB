package dao;

import models.Abonent;
import models.Appliance;
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
            Query<Long> query = session.createQuery(hql, Long.class);
            Long callDuration = query.uniqueResult();
            hql = "SELECT SUM(a.smsActivities.messageCount) FROM Abonent a";
            query = session.createQuery(hql, Long.class);
            Long smsCount = query.uniqueResult();
            hql = "SELECT SUM(a.networkActivity.megabytes) FROM Abonent a";
            query = session.createQuery(hql, Long.class);
            Long networkMegabytes = query.uniqueResult();
            if (callDuration > smsCount && callDuration > networkMegabytes) {
                return "Call service is the most popular. Count = " + callDuration;
            } else if (smsCount > callDuration && smsCount > networkMegabytes) {
                return "SMS service is the most popular. Count = " + smsCount;
            } else {
                return "Network service is the most popular. Count = " + networkMegabytes;
            }
        }
    }

    public String getMostPopularDevice() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT a.appliance FROM Abonent a GROUP BY a.appliance ORDER BY COUNT(a) DESC";
            Query<Appliance> query = session.createQuery(hql, Appliance.class);
            query.setMaxResults(1);
            Appliance mostPopularAppliance = query.uniqueResult();
            return mostPopularAppliance != null ? mostPopularAppliance.getBrandName() : "No devices found";
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