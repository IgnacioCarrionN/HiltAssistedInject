package dev.carrion.hiltassistedinject.user

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dev.carrion.hiltassistedinject.MainModule
import dev.carrion.hiltassistedinject.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class, manifest = Config.NONE, sdk = [28])
@RunWith(RobolectricTestRunner::class)
class UserFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testUserFragment() {
        val fragmentArgs = bundleOf("name" to "Test")

        launchFragmentInHiltContainer<UserFragment>(fragmentArgs)

        onView(withId(R.id.name)).check(matches(withText("Hi Test")))
    }


}