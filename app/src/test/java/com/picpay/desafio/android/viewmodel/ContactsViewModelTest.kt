package com.picpay.desafio.android.viewmodel

import com.picpay.desafio.android.repository.FakeRepository
import com.picpay.desafio.android.utils.StateResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ContactsViewModelTest {

    private lateinit var contactsViewModel: ContactsViewModel
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setup() {
        fakeRepository = FakeRepository()
        contactsViewModel = ContactsViewModel(fakeRepository)
    }

    @Test
    fun `load users from viewModel, then check user state view`() = runTest {

        assertTrue(contactsViewModel.stateView.first() is StateResult.InProgress)
        contactsViewModel.loadUsers()

        assertTrue(contactsViewModel.user.first().isNotEmpty())
        assertTrue(contactsViewModel.stateView.first() is StateResult.Sucess)
    }
}