package test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import test.model.Projects;

import java.util.List;
@Repository
public class ProjectsDAO {
    private final SessionFactory sessionFactory;

    public ProjectsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

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

    public Projects getProjectByProjectName(String projectName) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        @SuppressWarnings("unchecked")
        Query<Projects> query = session.createQuery("FROM Projects WHERE projectName = :projectName");
        query.setParameter("projectName", projectName);
        query.setFirstResult(0);
        query.setMaxResults(1);

        final List<Projects> project = query.list();

        transaction.commit();

        session.close();

        if(!project.isEmpty()){
            return project.get(0);
        }

        throw new RuntimeException("Can't fetch data");
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
