package com.ferreira.kotlinapi.exception

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class BusinessException(msg: String, status: HttpStatus) : ResponseStatusException(status, msg) {

}
