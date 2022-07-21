package com.jomibusa.adn_android.payment.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.jomibusa.adn_android.R
import com.jomibusa.adn_android.launchFragmentInHiltContainer
import com.jomibusa.adn_android.shared.Page
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class PaymentPage : Page() {

    override fun verify(): Page {
        return this
    }

    fun onHiltFragment(
        navController: NavController
    ): Page {
        launchFragmentInHiltContainer<PaymentFragment> {
            navController.setGraph(R.navigation.core_nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
        }
        return this
    }

    fun checkVisibilityTotalService(visibility: ViewMatchers.Visibility): Page {
        Espresso.onView(ViewMatchers.withId(R.id.text_view_payment)).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(
                    visibility
                )
            )
        )
        return this
    }

    fun checkVisibilityButtonPayment(visibility: ViewMatchers.Visibility): Page {
        Espresso.onView(ViewMatchers.withId(R.id.material_button_payment)).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(
                    visibility
                )
            )
        )
        return this
    }

    fun replaceTextPlate(plate: String): Page {
        Espresso.onView(ViewMatchers.withId(R.id.text_input_edit_text_search)).perform(
            ViewActions.replaceText(plate), ViewActions.closeSoftKeyboard()
        )
        return this
    }

    fun onClickButtonCalculatePayment(): Page {
        Espresso.onView(ViewMatchers.withId(R.id.material_button_calculate_payment))
            .perform(ViewActions.click())
        return this
    }

}