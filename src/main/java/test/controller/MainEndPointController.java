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

import java.util.List;
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
    public Tasks tracker(@RequestBody Map<String, Object> test) {

        for (Map.Entry<String, Object> entry : test.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        return null;
    }
}
