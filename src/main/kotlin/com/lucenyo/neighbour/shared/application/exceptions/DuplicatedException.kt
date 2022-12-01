package com.lucenyo.neighbour.shared.application.exceptions

import org.springframework.http.HttpStatus

class DuplicatedException(msg: String, code: Int = HttpStatus.BAD_REQUEST.value()): ApiException(msg, code)
