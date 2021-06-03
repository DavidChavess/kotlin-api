package com.ferreira.kotlinapi.datasource.mock

import com.ferreira.kotlinapi.datasource.BankDataSource
import com.ferreira.kotlinapi.exception.BusinessException
import com.ferreira.kotlinapi.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource : BankDataSource {

    private val listOfBanks = listOf(
        Bank("1234", 3.14,17),
        Bank("1234", 17.0,0),
        Bank("1234", 0.0,100),
    );

    override fun retrieveBanks() = listOfBanks.sortedBy { it.accountNumber };

    override fun retrieveBank(accountNumber: String): Bank =
        listOfBanks.firstOrNull{ it.accountNumber == accountNumber }
            ?: throw BusinessException("Bank not found")
}