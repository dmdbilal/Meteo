package com.dmdbilal.meteo.domain.util

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data) // Can return this if no error.
    class Error<T>(message: String, data: T?=null): Resource<T>(data, message) // Can return this if there is a error like no network.
}
