package br.edu.insper.musicmeter.user.service;

import br.edu.insper.musicmeter.common.exception.ObjectNotFoundException;
import br.edu.insper.musicmeter.user.User;
import br.edu.insper.musicmeter.user.dto.UserSaveDTO;
import br.edu.insper.musicmeter.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    private User user;

    @BeforeEach
    void setup() {
        user = new User(1, "joao", "Joãozinho", new HashSet<>(), new HashSet<>(), null);
    }

    @Test
    void test_getUsers() {
        Mockito.when(repository.findAll()).thenReturn(List.of(user));

        List<User> result = service.getUsers();

        assertEquals(1, result.size());
        assertEquals("joao", result.get(0).getName());
    }

    @Test
    void test_getUser_found() throws Exception {
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(user));

        User result = service.getUser(1);

        assertEquals("Joãozinho", result.getDisplayName());
    }

    @Test
    void test_getUser_notFound() {
        Mockito.when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> service.getUser(1));
    }

    @Test
    void test_saveUser() {
        UserSaveDTO dto = new UserSaveDTO("ana", "Aninha", new HashSet<>(), new HashSet<>(), null);
        User newUser = UserSaveDTO.to(dto);

        Mockito.when(repository.save(Mockito.any(User.class))).thenReturn(newUser);

        User result = service.saveUser(dto);

        assertEquals("ana", result.getName());
        assertEquals("Aninha", result.getDisplayName());
    }
}
