package com.picpay.desafio.android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.repository.UsersRepository
import com.picpay.desafio.android.utils.StateResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(private val userRepository: UsersRepository) : ViewModel() {
    private val tag = ContactsViewModel::class.java.name

    private val _stateView: MutableStateFlow<StateResult<Any>> = MutableStateFlow(StateResult.InProgress)
    val stateView = _stateView.asStateFlow()

    val user = userRepository.users

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _stateView.value = StateResult.Error(throwable.message.toString())
        Log.e(tag, throwable.message.toString())
    }
    private val coroutineContextIO = Dispatchers.IO + exceptionHandler

    fun loadUsers() {
        _stateView.value = StateResult.InProgress
        viewModelScope.launch(coroutineContextIO) {
            userRepository.refreshUsers()
            _stateView.value = StateResult.Sucess()
        }
    }

}