package com.cdw.Credit_Debit_Wallet.daoImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cdw.Credit_Debit_Wallet.dao.UserService;
import com.cdw.Credit_Debit_Wallet.entity.Transfer;
import com.cdw.Credit_Debit_Wallet.entity.User;
import com.cdw.Credit_Debit_Wallet.repository.TransferRepository;

@Service
public class TransferService {
    private final TransferRepository transferRepository;
    private final UserService userService;

    public TransferService(TransferRepository transferRepository, UserService userService) {
        this.transferRepository = transferRepository;
        this.userService = userService;
    }

    public void recordTransaction(Long userId, String type, double amount) {
        User user = userService.getUserById(userId);

        Transfer transfer = new Transfer(userId, type, amount, null);
        transfer.setUser(user);
        transfer.setType(type);
        transfer.setAmount(amount);
        transfer.setTimestamp(LocalDateTime.now());

        transferRepository.save(transfer);
    }

    public List<Transfer> getTransactionHistory(Long userId) {
        return transferRepository.findAllByUserId(userId);
    }
}
