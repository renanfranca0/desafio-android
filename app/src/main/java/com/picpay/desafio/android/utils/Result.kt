package com.picpay.desafio.android.utils

sealed class StateResult<out T: Any> {
    object InProgress : StateResult<Nothing>()
    data class Sucess<out T: Any>(val result: T? = null): StateResult<T>()
    data class Error(var message: String) : StateResult<Nothing>()
}
