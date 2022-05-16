package test.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import test.dao.RolesDAO;
import test.model.Roles;

import java.util.List;

@RestController
public class RolesController {
    private final RolesDAO rolesDAO;
    public RolesController(RolesDAO rolesDAO) {
        this.rolesDAO = rolesDAO;
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Roles getRole(@PathVariable("id") String id) {
        return rolesDAO.getRole(Integer.valueOf(id));
    }

    @RequestMapping(value = "/roles", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Roles> getRoles() {
        return rolesDAO.getRoles();
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Roles addRole(@RequestBody Roles role) {
        return rolesDAO.addRole(role);
    }

    @RequestMapping(value = "/role", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Roles updateRole(@RequestBody Roles role) {
        return rolesDAO.updateRole(role);
    }

    @RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Roles deleteRole(@PathVariable("id") String id) {
        return rolesDAO.deleteRole(Integer.valueOf(id));
    }

}
