package com.cdw.Credit_Debit_Wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdw.Credit_Debit_Wallet.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);

}
