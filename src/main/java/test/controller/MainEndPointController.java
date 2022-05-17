package test.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import test.dao.ProjectsDAO;
import test.dao.TasksDAO;
import test.dao.UsersDAO;
import test.model.Projects;
import test.model.Tasks;
import test.model.Users;


import java.sql.Timestamp;

import java.util.Map;

@RestController
public class MainEndPointController {
    private final ProjectsDAO projectsDAO;
    private final UsersDAO usersDAO;
    private final TasksDAO tasksDAO;

    public MainEndPointController(ProjectsDAO projectsDAO, UsersDAO usersDAO, TasksDAO tasksDAO) {
        this.projectsDAO = projectsDAO;
        this.usersDAO = usersDAO;
        this.tasksDAO = tasksDAO;
    }

    @RequestMapping(value = "/tracker", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public String tracker(@RequestBody Map<String, String> test) {
        Users user = new Users();
        Projects project = new Projects();
        Tasks task = new Tasks();

        for (Map.Entry<String, String> entry : test.entrySet()) {
            if (entry.getKey().equals("firstName")) {
                user.setFirstName(entry.getValue());
            }
            if (entry.getKey().equals("lastName")) {
                user.setLastName(entry.getValue());
            }
            if (entry.getKey().equals("issue")) {
                task.setIssue(entry.getValue());
            }
            if (entry.getKey().equals("projectName")) {
                project.setProjectName(entry.getValue());
            }

        }

        try {
            if (task.getIssue() == null) {
                throw new RuntimeException("Please enter the information about issue!");
            }
            if (user.getFirstName() != null && user.getLastName() != null) {
                Users fullUser = usersDAO.getUserByFirstNameAndLastName(user.getFirstName(), user.getLastName());
                task.setUser(fullUser);
            } else
                throw new RuntimeException("Please enter your first name and last name!");

            if (project.getProjectName() != null) {
                Projects fullProject = projectsDAO.getProjectByProjectName(project.getProjectName());
                task.setProject(fullProject);
            } else
                throw new RuntimeException("Please enter project name!");
        } catch (RuntimeException exception) {
            return exception.getMessage();
        }

        task.setTime(new Timestamp(System.currentTimeMillis()));


        tasksDAO.addTask(task);
        return "DONE!";
    }
}
