package com.ferreira.kotlinapi.controller

import com.ferreira.kotlinapi.model.Bank
import com.ferreira.kotlinapi.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks")
class BankController(
    private val service: BankService
){
    @GetMapping
    fun getBanks():Collection<Bank> = service.getBanks();

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String): Bank = service.getBank( accountNumber );

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank): Bank = service.createBank(bank);
}