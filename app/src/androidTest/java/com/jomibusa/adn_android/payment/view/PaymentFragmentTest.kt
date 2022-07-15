package com.jomibusa.adn_android.payment.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.jomibusa.adn_android.R
import com.jomibusa.adn_android.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@HiltAndroidTest
class PaymentFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var mockNavController: NavController

    /*@Before
    fun setUp() {
        hiltRule.inject()
        mockNavController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<PaymentFragment> {
            mockNavController.setGraph(R.navigation.core_nav_graph)
            Navigation.setViewNavController(this.requireView(), mockNavController)
        }
    }

    @Test
    fun calculatePayment_writePlateAndFindRegister_success() {
        val plate = "HMT" + (100..999).random()
        onView(withId(R.id.text_view_payment)).check(
            matches(
                withEffectiveVisibility(
                    ViewMatchers.Visibility.GONE
                )
            )
        )
        onView(withId(R.id.material_button_payment)).check(
            matches(
                withEffectiveVisibility(
                    ViewMatchers.Visibility.GONE
                )
            )
        )
        onView(withId(R.id.text_input_edit_text_search)).perform(
            replaceText(plate), closeSoftKeyboard()
        )
        onView(withId(R.id.material_button_calculate_payment)).perform(click())
    }*/

}