package com.jomibusa.adn_android.menuparking.view

import androidx.fragment.app.testing.FragmentScenario
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.jomibusa.adn_android.R
import com.jomibusa.adn_android.shared.Page
import org.mockito.Mockito

class MenuParkingPage : Page() {

    fun onFragment(
        menuParking: FragmentScenario<MenuParkingFragment>,
        navController: NavController
    ): Page {
        menuParking.onFragment { fragment ->
            navController.setGraph(R.navigation.core_nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        return this
    }

    override fun verify(): Page {
        return this
    }

    fun isVisible(): Page {
        Espresso.onView(ViewMatchers.withId(R.id.material_button_register)).check(
            ViewAssertions.matches(
                ViewMatchers.withEffectiveVisibility(
                    ViewMatchers.Visibility.VISIBLE
                )
            )
        )
        return this
    }

    fun onClickRegister(): Page {
        Espresso.onView(ViewMatchers.withId(R.id.material_button_register))
            .perform(ViewActions.click())
        return this
    }

    fun onClickPayment(): Page {
        Espresso.onView(ViewMatchers.withId(R.id.material_button_payment))
            .perform(ViewActions.click())
        return this
    }

    fun goToRegisterFragment(navController: NavController): Page {
        Mockito.verify(navController)
            .navigate(MenuParkingFragmentDirections.actionParkingFragmentToRegisterFragment())
        return this
    }

    fun goToPaymentFragment(navController: NavController): Page {
        Mockito.verify(navController)
            .navigate(MenuParkingFragmentDirections.actionParkingFragmentToPaymentFragment())
        return this
    }
}