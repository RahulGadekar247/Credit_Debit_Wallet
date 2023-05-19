package com.cdw.Credit_Debit_Wallet.dao;

import com.cdw.Credit_Debit_Wallet.entity.User;

public interface UserService {
    User createUser(User user);
    User loginUser(String username, String password);
    double getBalance(Long userId);
    User addMoney(Long userId, double amount);
    User withdrawMoney(Long userId, double amount);
	User getUserById(Long userId);
}
