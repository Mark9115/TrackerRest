package test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import test.model.Users;

import java.util.List;
@Repository
public class UsersDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Users> getUsers(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Users> users = session.createQuery("FROM Users").list();
        transaction.commit();

        session.close();
        return users;
    }
    public Users getUser(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Users user = session.get(Users.class, id);
        transaction.commit();

        session.close();
        return user;
    }

    public Users addUser(Users user){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(user);
        transaction.commit();

        session.close();
        return user;
    }

    public Users updateUser(Users user){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(user);
        transaction.commit();

        session.close();
        return user;
    }

    public Users deleteUser(Integer id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Users user = session.get(Users.class, id);
        session.delete(user);

        transaction.commit();

        session.close();
        return user;
    }
}
