package com.cdw.Credit_Debit_Wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdw.Credit_Debit_Wallet.entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long>{

	List<Transfer> findAllByUserId(Long userId);

}
