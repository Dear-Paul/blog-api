package com.example.blog.servicesImpl;

import com.example.blog.enums.AccountStatus;
import com.example.blog.enums.UserType;
import com.example.blog.models.Users;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
@Component
@Slf4j
public class UserServiceImpl implements UserService {




    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<Users> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<Users> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Users saveUser(Users users) {
        users.setUserType(UserType.USERS);
        users.setAccountStatus(AccountStatus.ACTIVE);
        return userRepository.save(users);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(long userId) throws InterruptedException {
        Users user = userRepository.getById(userId);
        user.setAccountStatus(AccountStatus.DELETED);
        removeUserBySchedule();

       // restoreUserAccount(userId);
        if(user.getAccountStatus().equals(AccountStatus.DELETED)){
            userRepository.delete(user);
        } else {
            userRepository.save(user);
        }

    }
    @Scheduled(fixedDelay = 100000)
    @Override
    public void removeUserBySchedule() throws InterruptedException {

        log.info("I am waiting for 1min before executing");
        TimeUnit.MINUTES.sleep(1);


    }

    @Override
    public Users restoreUserAccount(long userId) {
        Users user = userRepository.getById(userId);
        user.setAccountStatus(AccountStatus.ACTIVE);
        return userRepository.save(user);
    }
}
