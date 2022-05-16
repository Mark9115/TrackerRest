package test.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import test.dao.UsersDAO;
import test.model.Users;

import java.util.List;

@RestController
public class UsersController {
    private final UsersDAO usersDAO;
    public UsersController(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Users> getRoles() {
        return usersDAO.getUsers();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Users getUser(@PathVariable("id") String id) {
        return usersDAO.getUser(Integer.valueOf(id));
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Users addUser(@RequestBody Users user) {
        return usersDAO.addUser(user);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Users updateUser(@RequestBody Users user) {
        return usersDAO.updateUser(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Users deleteRole(@PathVariable("id") String id) {
        return usersDAO.deleteUser(Integer.valueOf(id));
    }

}
