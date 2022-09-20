package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.network.PicPayService
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ExampleServiceTest {

    private val api = mock<PicPayService>()
    private val service = FakeService(api)

    @Test
    fun `call to api, then check results`() = runTest {
        val fakeResult = mock<List<User>>()
        val expectedUsers = emptyList<User>()

        whenever(service.getUsers()).thenReturn(fakeResult)
        whenever(api.usersEndpoint.getUsers()).thenReturn(expectedUsers)

        val result = service.getUsers()

        assert(result.isEmpty())
    }

    /*private val api = mock<PicPayService>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() {
        // given
        val call = mock<Call<List<User>>>()
        val expectedUsers = emptyList<User>()

        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
        whenever(api.getUsers()).thenReturn(call)

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }*/
}