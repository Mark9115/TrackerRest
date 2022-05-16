package test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import test.model.Projects;

import java.util.List;
@Repository
public class ProjectsDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Projects> getProjects(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        @SuppressWarnings("unchecked")
        List<Projects> projects = session.createQuery("FROM Projects").list();
        transaction.commit();

        session.close();
        return projects;
    }

    public Projects getProject(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Projects project = session.get(Projects.class, id);
        transaction.commit();

        session.close();
        return project;
    }

    public Projects addProject(Projects project){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(project);
        transaction.commit();

        session.close();
        return project;
    }

    public Projects updateProject(Projects project){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.update(project);
        transaction.commit();

        session.close();
        return project;
    }

    public Projects deleteProject(Integer id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Projects project = session.get(Projects.class, id);
        session.delete(project);

        transaction.commit();

        session.close();
        return project;
    }
}
