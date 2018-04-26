package bookstore.service.user;

import bookstore.dto.UserDto;
import bookstore.entity.User;
import bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDto userDto) {
        String password = new ShaPasswordEncoder().encodePassword(userDto.password, "SHA-1");
        User user = new User(userDto.username, password, userDto.role);
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User update(Integer id, UserDto userDto) {
        User user = userRepository.findOne(id);
        user.setId(id);
        user.setUsername(userDto.username);
        user.setPassword(userDto.password);
        user.setRole(userDto.role);
        return userRepository.save(user);
    }
}
