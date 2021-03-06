package com.ferreira.kotlinapi.service

import com.ferreira.kotlinapi.datasource.BankDataSource
import com.ferreira.kotlinapi.exception.BusinessException
import com.ferreira.kotlinapi.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService(private val dataSource: BankDataSource) {

    fun getBanks() : Collection<Bank> = dataSource.retrieveBanks();

    fun getBank(accountNumber: String): Bank = dataSource.retrieveBank(accountNumber);

    fun createBank(bank: Bank): Bank = dataSource.createBank(bank);
}