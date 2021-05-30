package com.ferreira.kotlinapi.datasource

import com.ferreira.kotlinapi.model.Bank

interface BankDataSource {
    fun retrieveBanks() : Collection<Bank>
}