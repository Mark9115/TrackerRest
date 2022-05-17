package test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import test.model.Roles;

import java.util.List;
@Repository
public class RolesDAO {
    private final SessionFactory sessionFactory;

    public RolesDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Roles addRole(Roles role){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(role);
        transaction.commit();

        session.close();
        return role;
    }

    public Roles updateRole(Roles role){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(role);
        transaction.commit();

        session.close();
        return role;
    }

    public Roles deleteRole(Integer id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Roles role = session.get(Roles.class, id);
        session.delete(role);

        transaction.commit();

        session.close();
        return role;
    }

    public Roles getRole(Integer id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Roles role = session.get(Roles.class, id);
        transaction.commit();

        session.close();
        return role;
    }

    public List<Roles> getRoles(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Roles> roles = session.createQuery("FROM Roles").list();
        transaction.commit();

        session.close();
        return roles;
    }
}
