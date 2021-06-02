package com.ferreira.kotlinapi.controller

import com.ferreira.kotlinapi.model.Bank
import com.ferreira.kotlinapi.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/banks")
class BankController(
    private val service: BankService
){
    @GetMapping
    fun get():Collection<Bank> = service.getBanks();
}