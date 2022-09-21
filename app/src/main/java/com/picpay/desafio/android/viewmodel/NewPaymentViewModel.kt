package com.picpay.desafio.android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.model.User
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
class NewPaymentViewModel @Inject constructor(private val usersRepository: UsersRepository) : ViewModel() {
    private val tag = NewPaymentViewModel::class.java.name

    private val _stateView: MutableStateFlow<StateResult<Any>> = MutableStateFlow(StateResult.InProgress)
    val stateView = _stateView.asStateFlow()

    private val _user: MutableStateFlow<User> = MutableStateFlow(User())
    val user = _user.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _stateView.value = StateResult.Error(throwable.message.toString())
        Log.e(tag, throwable.message.toString())
    }
    private val coroutineContextIO = Dispatchers.IO + exceptionHandler

    fun loadUser(userId: Int) {
        viewModelScope.launch(coroutineContextIO) {
            usersRepository.getUser(userId).collect { user ->
                _user.emit(user)
                _stateView.emit(StateResult.Sucess())
            }
        }
    }

}