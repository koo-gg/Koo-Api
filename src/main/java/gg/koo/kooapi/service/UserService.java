package gg.koo.kooapi.service;

import gg.koo.kooapi.entity.user.User;
import gg.koo.kooapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> getAllUser()
    {
        return userRepository.findAll();
    }
}
