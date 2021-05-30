package com.ferreira.kotlinapi.service

import com.ferreira.kotlinapi.datasource.BankDataSource
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class BankServiceTest{

    private val dataSource: BankDataSource = mockk(relaxed = true);
    private val service: BankService = BankService(dataSource);

    @Test
    fun `should call its data source to retrieve banks`(){
        // when
        val banks = service.getBanks();

        // then
        verify(exactly = 1) { dataSource.retrieveBanks() }
    }
}