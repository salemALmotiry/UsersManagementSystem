package com.example.usersmanagementsystem.Service;

import com.example.usersmanagementsystem.ApiResponse.ApiException;
import com.example.usersmanagementsystem.Model.User;
import com.example.usersmanagementsystem.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;



    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Integer userId) {
        User oldUser = userRepository.findUserById(userId);
        if (oldUser == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(oldUser);

    }
    public void updateUser(Integer userId,User user) {

        User oldUser = userRepository.findUserById(userId);
        if (oldUser == null) {
            throw new ApiException("User not found");
        }
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setUserName(user.getUserName());
        oldUser.setRole(user.getRole());

        userRepository.save(oldUser);

    }



    public void loginUser(String userName, String password) {
        Boolean login = userRepository.loginUser(userName, password);
        if (!login) {
            throw new ApiException("username or password is incorrect");
        }

    }

    public User findUserByEmail(String email) {

        User user =  userRepository.findUserByEmail(email);
        if (user == null) {
            throw new ApiException("User not found");
        }
        return user;
    }

    public List<User> findUserByRole(String role) {
        List<User> users = userRepository.findUsersByRole(role);

        if (users == null || users.isEmpty()) {
            throw new ApiException("Users with "+role+" role not found");
        }
        return users;
    }

    public List<User> findUserByAgeRange(Integer min, Integer max) {
        List<User> users = userRepository.findUsersByAge(min, max);

        if (users == null || users.isEmpty()) {
            throw new ApiException("Users with "+min+" and "+max+" not found");
        }
        return users;

    }
}
