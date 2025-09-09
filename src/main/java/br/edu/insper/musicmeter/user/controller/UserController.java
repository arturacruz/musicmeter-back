package br.edu.insper.musicmeter.user.controller;

import br.edu.insper.musicmeter.user.User;
import br.edu.insper.musicmeter.user.dto.UserDTO;
import br.edu.insper.musicmeter.user.dto.UserSaveDTO;
import br.edu.insper.musicmeter.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public User getUsers(@PathVariable Integer id) {
        return service.getUser(id);
    }

    @PostMapping
    public User postUser(@RequestBody UserSaveDTO userDTO) {
        return service.saveUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable Integer id) {
        return service.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User putUser(@PathVariable Integer id, @RequestBody UserSaveDTO userDTO) {
        return service.saveUser(userDTO);
    }
}