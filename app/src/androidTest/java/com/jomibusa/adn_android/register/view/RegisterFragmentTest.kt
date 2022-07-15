package com.jomibusa.adn_android.register.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
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
class RegisterFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var mockNavController: NavController

    @Before
    fun setUp() {
        hiltRule.inject()
        mockNavController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<RegisterFragment> {
            mockNavController.setGraph(R.navigation.core_nav_graph)
            Navigation.setViewNavController(this.requireView(), mockNavController)
        }
    }

    @Test
    fun registerCar_writeAPlateAndGoBack_success() {
        val plate = "HMT" + (100..999).random()

        onView(withId(R.id.radio_button_car)).perform(ViewActions.click())
        onView(withId(R.id.text_input_layout_cylinder_capacity)).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.GONE
                )
            )
        )
        onView(withId(R.id.text_input_edit_text_plate)).perform(
            replaceText(plate), closeSoftKeyboard()
        )
        onView(withId(R.id.material_button_register)).perform(ViewActions.click())
    }

    @Test
    fun registerMotorcycle_writeAPlateAndGoBack_success() {

        val plate = "upa" + (10..99).random() + "C"

        onView(withId(R.id.radio_button_motorcycle)).perform(ViewActions.click())
        onView(withId(R.id.text_input_layout_cylinder_capacity)).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
        onView(withId(R.id.text_input_edit_text_plate)).perform(
            replaceText(plate), closeSoftKeyboard()
        )
        onView(withId(R.id.text_input_edit_text_cylinder_capacity)).perform(
            replaceText("250"), closeSoftKeyboard()
        )
        onView(withId(R.id.material_button_register)).perform(ViewActions.click())
    }


}