package br.edu.insper.musicmeter.user.service;

import br.edu.insper.musicmeter.common.exception.ObjectNotFoundException;
import br.edu.insper.musicmeter.user.User;
import br.edu.insper.musicmeter.user.dto.UserDTO;
import br.edu.insper.musicmeter.user.dto.UserSaveDTO;
import br.edu.insper.musicmeter.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream().map(UserDTO::from).toList();
    }

    public UserDTO saveUser(UserSaveDTO user) {
        User s = userRepository.save(UserSaveDTO.to(user));
        return UserDTO.from(s);
    }

    public UserDTO updateUser(int id, UserSaveDTO user) throws ObjectNotFoundException {
        User model = getUser(id);
        User updated = UserSaveDTO.to(user);
        updated.setId(model.getId());
        saveUser(UserSaveDTO.from(updated));
        return UserDTO.from(updated);
    }

    public UserDTO deleteUser(int id) throws ObjectNotFoundException {
        User user = getUser(id);
        userRepository.deleteById(id);
        return UserDTO.from(user);
    }

    public User getUser(Integer id) throws ObjectNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new ObjectNotFoundException("");
        }
        return user.get();
    }
}
