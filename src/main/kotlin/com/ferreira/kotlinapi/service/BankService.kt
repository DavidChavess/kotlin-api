package com.ferreira.kotlinapi.service

import com.ferreira.kotlinapi.datasource.BankDataSource
import com.ferreira.kotlinapi.model.Bank
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BankService(private val service: BankDataSource) {
    fun getBanks() : Collection<Bank> = service.retrieveBanks();
}