package com.cdw.Credit_Debit_Wallet.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdw.Credit_Debit_Wallet.daoImpl.TransferService;
import com.cdw.Credit_Debit_Wallet.entity.Transfer;

@RestController
@RequestMapping("/transactions")
public class TransferController {
    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/{userId}")
    public void recordTransaction(@PathVariable Long userId, @RequestParam String type, @RequestParam double amount) {
    	transferService.recordTransaction(userId, type, amount);
    }

    @GetMapping("/{userId}")
    public List<Transfer> getTransactionHistory(@PathVariable Long userId) {
        return transferService.getTransactionHistory(userId);
    }
}
