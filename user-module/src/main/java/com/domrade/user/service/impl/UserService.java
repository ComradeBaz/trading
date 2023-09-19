package com.domrade.user.service.impl;

import com.domrade.user.entity.User;
import com.domrade.user.repository.IUserRepository;
import com.domrade.user.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UserService implements IUserService {

    private IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        user.getEmailAddress().toLowerCase(Locale.ROOT);
        return userRepository.save(user);
    }
}
