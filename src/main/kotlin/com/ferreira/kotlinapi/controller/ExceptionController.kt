package com.ferreira.kotlinapi.controller

import com.ferreira.kotlinapi.exception.BusinessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {

    @ExceptionHandler(BusinessException::class)
    fun BusinessError( ex: BusinessException) = ResponseEntity(ResponseError(ex.reason), ex.status)

    inner class ResponseError(val message: String?)
}