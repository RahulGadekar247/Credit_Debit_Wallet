package com.cdw.Credit_Debit_Wallet.daoImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cdw.Credit_Debit_Wallet.dao.UserService;
import com.cdw.Credit_Debit_Wallet.entity.User;
import com.cdw.Credit_Debit_Wallet.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; 
    }

    @Override
    public double getBalance(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return (user != null) ? user.getBalance() : 0;
    }

    @Override
    public User addMoney(Long userId, double amount) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setBalance(user.getBalance() + amount);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User withdrawMoney(Long userId, double amount) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            double currentBalance = user.getBalance();
            if (currentBalance >= amount) {
                user.setBalance(currentBalance - amount);
                return userRepository.save(user);
            }
        }
        return null;
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

}
