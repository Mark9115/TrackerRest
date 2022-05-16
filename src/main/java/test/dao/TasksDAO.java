package test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import test.model.Tasks;

import java.util.List;
@Repository
public class TasksDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Tasks> getTasks() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Tasks> tasks = session.createQuery("FROM Tasks").list();
        transaction.commit();

        session.close();
        return tasks;
    }

    public Tasks getTask(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Tasks task = session.get(Tasks.class, id);
        transaction.commit();

        session.close();
        return task;
    }

    public Tasks addTask(Tasks task) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(task);
        transaction.commit();

        session.close();
        return task;
    }

    public Tasks updateTask(Tasks task) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(task);
        transaction.commit();

        session.close();
        return task;
    }

    public Tasks deleteTask(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Tasks task = session.get(Tasks.class, id);
        session.delete(task);

        transaction.commit();

        session.close();
        return task;
    }
}
