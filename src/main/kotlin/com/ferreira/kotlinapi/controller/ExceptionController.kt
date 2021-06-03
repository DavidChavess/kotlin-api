package com.ferreira.kotlinapi.controller

import com.ferreira.kotlinapi.exception.BusinessException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun bankNotFound( ex: BusinessException) = ResponseError(ex.message)

    inner class ResponseError(val message: String?)
}