package com.lucenyo.neighbour.shared.application.exceptions

sealed class ApiException(msg: String, val code: Int) : Exception(msg)
