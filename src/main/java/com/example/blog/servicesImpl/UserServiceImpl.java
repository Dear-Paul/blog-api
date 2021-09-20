package com.example.blog.servicesImpl;

import com.example.blog.enums.AccountStatus;
import com.example.blog.enums.UserType;
import com.example.blog.exceptions.BlogApiException;
import com.example.blog.models.Users;
import com.example.blog.repositories.UserRepository;
import com.example.blog.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Component
@Slf4j
public class UserServiceImpl implements UserService {




    @Autowired
    private UserRepository userRepository;

    @Override
    public Users findByUserName(String userName) {

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
        if(users.getEmail() == null){
            throw new BlogApiException("The email has been used by another user");
        } else {
            users.setUserType(UserType.USERS);
            users.setAccountStatus(AccountStatus.ACTIVE);
            return userRepository.save(users);
        }
    }

    @Override
    public List<Users> getAllUsers(long userId) {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(long userId) throws InterruptedException {
        Users user = userRepository.getById(userId);
        user.setAccountStatus(AccountStatus.DELETED);
//        removeUserBySchedule();
//
//       // restoreUserAccount(userId);
//        if(user.getAccountStatus().equals(AccountStatus.DELETED)){
//            userRepository.delete(user);
//        } else {
//          restoreUserAccount(userId);
        userRepository.save(user);
        }


    @Scheduled(fixedDelay = 150000)
    @Override
    public void removeUserBySchedule() throws InterruptedException {

       log.info("I am waiting for 1min before executing");
        TimeUnit.MINUTES.sleep(1);
        List<Users> users = userRepository.findByAccountStatus(AccountStatus.DELETED);
        userRepository.deleteAll(users);


    }

    @Override
    public Users restoreUserAccount(long userId) {
        Users user = userRepository.getById(userId);
        if(user.getAccountStatus().equals(AccountStatus.DELETED)) {
            user.setAccountStatus(AccountStatus.ACTIVE);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public Users updateUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<Users> findUserById(long userId) {
        return userRepository.findById(userId);
    }
}
