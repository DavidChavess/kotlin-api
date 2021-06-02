package com.ferreira.kotlinapi.controller

import org.junit.jupiter.api.Test
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
    
    @Test
    fun `should return all banks`(){
        // when
        mockMvc.get(BASE_URL)
            .andDo { print() }
            .andExpect {
                content { contentType(MediaType.APPLICATION_JSON) }
                status { isOk() }
                jsonPath("[0].accountNumber"){value(1234)}
            }
    }
}