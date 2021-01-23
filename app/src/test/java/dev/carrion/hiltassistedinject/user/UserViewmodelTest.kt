package dev.carrion.hiltassistedinject.user

import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

@ExperimentalCoroutinesApi
class UserViewmodelTest {

    private val repository: UserRepository = mockk()

    private val testDispatcher: CoroutineDispatcher = TestCoroutineDispatcher()

    private val name: String = "Test"

    @After
    fun tearDown() {
        confirmVerified(repository)
    }

    @Test
    fun `On UserViewModel init call repository and get message`() = runBlockingTest(testDispatcher) {
        val expected = "Hi $name"
        every { repository.getMessage(name) } returns expected
        pauseDispatcher()
        val userViewModel = UserViewModel(repository, testDispatcher, name)
        assertEquals("", userViewModel.message.value)
        resumeDispatcher()
        verify { repository.getMessage(name) }
        val actual = userViewModel.message.value
        assertEquals(expected, actual)
    }
}