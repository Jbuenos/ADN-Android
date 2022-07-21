package com.jomibusa.adn_android.register.view

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
class RegisterPage : Page() {

    override fun verify(): Page {
        return this
    }

    fun onHiltFragment(
        navController: NavController
    ): Page {
        launchFragmentInHiltContainer<RegisterFragment> {
            navController.setGraph(R.navigation.core_nav_graph)
            Navigation.setViewNavController(this.requireView(), navController)
        }
        return this
    }

    fun onClickButtonCar(): Page {
        Espresso.onView(ViewMatchers.withId(R.id.radio_button_car)).perform(ViewActions.click())
        return this
    }

    fun onClickButtonMotorcycle(): Page {
        Espresso.onView(ViewMatchers.withId(R.id.radio_button_motorcycle))
            .perform(ViewActions.click())
        return this
    }

    fun onClickButtonRegister(): Page {
        Espresso.onView(ViewMatchers.withId(R.id.material_button_register))
            .perform(ViewActions.click())
        return this
    }

    fun checkVisibilityCylinderCapacity(visibility: ViewMatchers.Visibility): Page {
        Espresso.onView(ViewMatchers.withId(R.id.text_input_layout_cylinder_capacity)).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(
                    visibility
                )
            )
        )
        return this
    }

    fun replaceTextPlate(plate: String): Page {
        Espresso.onView(ViewMatchers.withId(R.id.text_input_edit_text_plate)).perform(
            ViewActions.replaceText(plate), ViewActions.closeSoftKeyboard()
        )
        return this
    }

    fun replaceTextCylinderCapacity(): Page {
        Espresso.onView(ViewMatchers.withId(R.id.text_input_edit_text_cylinder_capacity)).perform(
            ViewActions.replaceText("250"), ViewActions.closeSoftKeyboard()
        )
        return this
    }

}