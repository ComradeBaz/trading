package com.domrade.user.entity.service.impl;

import com.domrade.entity.User;
import com.domrade.user.entity.repository.IUserRepository;
import com.domrade.user.entity.service.api.IUserService;
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
