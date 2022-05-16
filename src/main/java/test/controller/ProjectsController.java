package test.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import test.dao.ProjectsDAO;
import test.model.Projects;

import java.util.List;

@RestController
public class ProjectsController {
    private final ProjectsDAO projectsDAO;
    public ProjectsController(ProjectsDAO projectsDAO) {
        this.projectsDAO = projectsDAO;
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Projects> getProjects() {
        return projectsDAO.getProjects();
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Projects getProject(@PathVariable("id") String id) {
        return projectsDAO.getProject(Integer.valueOf(id));
    }

    @RequestMapping(value = "/project", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Projects addProject(@RequestBody Projects project) {
        return projectsDAO.addProject(project);
    }

    @RequestMapping(value = "/project", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Projects updateProject(@RequestBody Projects project) {
        return projectsDAO.updateProject(project);
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Projects deleteProject(@PathVariable("id") String id) {
        return projectsDAO.deleteProject(Integer.valueOf(id));
    }
}
