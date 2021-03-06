package com.ferreira.kotlinapi.datasource

import com.ferreira.kotlinapi.model.Bank

interface BankDataSource {
    fun retrieveBanks() : Collection<Bank>
    fun retrieveBank(accountNumber: String): Bank
    fun createBank(bank: Bank): Bank
}