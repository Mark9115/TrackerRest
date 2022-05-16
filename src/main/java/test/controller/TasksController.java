package test.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import test.dao.TasksDAO;
import test.model.Tasks;

import java.util.List;

@RestController
public class TasksController {
    private final TasksDAO tasksDAO;
    public TasksController(TasksDAO tasksDAO) {
        this.tasksDAO = tasksDAO;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Tasks> getTasks() {
        return tasksDAO.getTasks();
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Tasks getTask(@PathVariable("id") String id) {
        return tasksDAO.getTask(Integer.valueOf(id));
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Tasks addTask(@RequestBody Tasks task) {
        return tasksDAO.addTask(task);
    }

    @RequestMapping(value = "/task", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Tasks updateProject(@RequestBody Tasks task) {
        return tasksDAO.updateTask(task);
    }

    @RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Tasks deleteProject(@PathVariable("id") String id) {
        return tasksDAO.deleteTask(Integer.valueOf(id));
    }
}
