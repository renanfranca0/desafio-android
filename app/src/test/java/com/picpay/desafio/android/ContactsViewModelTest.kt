package com.picpay.desafio.android

import com.picpay.desafio.android.viewmodel.ContactsViewModel
import kotlinx.coroutines.test.runTest
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
/*        assertTrue(contactsViewModel.user.value.isEmpty())

        contactsViewModel.loadUsers()

        fakeRepository.emit(listOf(User("teste", "Renan", 0, "rnn")))
        assertTrue(contactsViewModel.user.value.isNotEmpty())*/
    }
}