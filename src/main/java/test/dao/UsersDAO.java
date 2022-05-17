package test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import test.model.Roles;
import test.model.Users;

import java.util.List;
@Repository
public class UsersDAO {
    private final SessionFactory sessionFactory;
    private final RolesDAO rolesDAO;

    public UsersDAO(RolesDAO rolesDAO, SessionFactory sessionFactory) {
        this.rolesDAO = rolesDAO;
        this.sessionFactory = sessionFactory;
    }

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

    public Users getUserByFirstNameAndLastName(String firstName, String lastName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Users WHERE firstName = :firstName AND lastName = :lastName";

        @SuppressWarnings("unchecked")
        Query<Users> query = session.createQuery(hql);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.setFirstResult(0);
        query.setMaxResults(1);

        final List<Users> user = query.list();

        transaction.commit();

        session.close();

        if(!user.isEmpty()){
            return user.get(0);
        }

        throw new RuntimeException("Can't fetch data");
    }

    public Users addUser(Users user){
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();
        session.save(user);

        Roles role = rolesDAO.getRole(user.getRoles().getId());
        user.setRoles(role);
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
