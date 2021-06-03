package com.ferreira.kotlinapi.controller

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

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest {

    private val BASE_URL = "/api/banks";

    @Autowired
    lateinit var mockMvc: MockMvc

    @Nested
    @DisplayName("getBanks()")
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
    @DisplayName("getBank()")
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

}