package com.ferreira.kotlinapi.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.ferreira.kotlinapi.model.Bank
import jdk.jfr.ContentType
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor (
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {
    private val BASE_URL = "/api/banks";

    @Nested
    @DisplayName("GET: /api/banks")
    @TestInstance(PER_CLASS)
    inner class GetBanks() {
        @Test
        fun `should return all banks`() {
            // when
            mockMvc.get(BASE_URL)
            .andDo { print() }
            .andExpect {
                content { contentType(MediaType.APPLICATION_JSON) }
                status { isOk() }
                jsonPath("[0].accountNumber") { value(1234) }
            }
        }
    }

    @Nested
    @DisplayName("GET: /api/banks/accountNumber")
    @TestInstance(PER_CLASS)
    inner class GetBank() {
        @Test
        fun `should return the bank with the given account number`() {
            // given
            val accountNumber = 1234;

            // when
            mockMvc.get(BASE_URL + "/$accountNumber")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                jsonPath("accountNumber") { value(accountNumber) }
            }
        }

        @Test
        fun `should return NOT FOUND if the account number does not exist`() {
            // given
            val accountNumber = 12345;

            // when
            mockMvc.get(BASE_URL + "/$accountNumber")
            .andDo { print() }
            .andExpect {
                status { isNotFound() }
                jsonPath("message") { value("Bank not found") }
            }
        }
    }

    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(PER_CLASS)
    inner class PostBank() {
        @Test
        fun `should post bank` () {
            // given
            val bank = Bank("123", 17.0,7);

            // when
            val perform = mockMvc.post(BASE_URL){
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bank)
            }

            // then
            perform.andDo { print() }
            .andExpect {
                status { isCreated() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("accountNumber"){value("123")}
                jsonPath("trust"){value("17.0")}
                jsonPath("transactionFee"){value("7")}
            }
        }
        
        @Test
        fun `should return BAD REQUEST if bank with given account number already exists`(){
            // given
            val invalidBank = Bank("1234", 17.0,7);

            // when
            val perform = mockMvc.post(BASE_URL){
                contentType = MediaType.APPLICATION_JSON
                accept = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            // then
            perform.andDo { print() }
            .andExpect {
                status { isBadRequest() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("message" ){ value("Bank with account number ${invalidBank.accountNumber} already exists") }
            }
        }
    }



}