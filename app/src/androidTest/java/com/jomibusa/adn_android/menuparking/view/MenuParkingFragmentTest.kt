/*
package com.jomibusa.adn_android.menuparking.view

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jomibusa.adn_android.R
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class MenuParkingFragmentTest {

    private lateinit var menuParking: FragmentScenario<MenuParkingFragment>
    private lateinit var mockNavController: NavController

    @Before
    fun setUp() {
        menuParking = launchFragmentInContainer(themeResId = R.style.Theme_ADNAndroid)
        menuParking.moveToState(newState = Lifecycle.State.STARTED)
        mockNavController = mock(NavController::class.java)
    }

    @Test
    fun goToRegisterFragment_success() {
        menuParking.onFragment { fragment ->
            mockNavController.setGraph(R.navigation.core_nav_graph)
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
        onView(withId(R.id.material_button_register)).check(
            matches(
                withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
        onView(withId(R.id.material_button_register)).perform(click())
        verify(mockNavController).navigate(MenuParkingFragmentDirections.actionParkingFragmentToRegisterFragment())
    }

    @Test
    fun goToPaymentFragment_success() {
        menuParking.onFragment { fragment ->
            mockNavController.setGraph(R.navigation.core_nav_graph)
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
        onView(withId(R.id.material_button_payment)).check(
            matches(
                withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
        onView(withId(R.id.material_button_payment)).perform(click())
        verify(mockNavController).navigate(MenuParkingFragmentDirections.actionParkingFragmentToPaymentFragment())
    }

}*/
