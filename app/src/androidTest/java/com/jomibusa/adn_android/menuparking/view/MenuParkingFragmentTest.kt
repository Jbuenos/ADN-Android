package com.jomibusa.adn_android.menuparking.view

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jomibusa.adn_android.R
import com.jomibusa.adn_android.shared.Page
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

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
        Page.on<MenuParkingPage>()
            .onFragment(menuParking, mockNavController)
            .on<MenuParkingPage>()
            .isVisible()
            .on<MenuParkingPage>()
            .onClickRegister()
            .on<MenuParkingPage>()
            .goToRegisterFragment(mockNavController)
    }

    @Test
    fun goToPaymentFragment_success() {
        Page.on<MenuParkingPage>()
            .onFragment(menuParking, mockNavController)
            .on<MenuParkingPage>()
            .isVisible()
            .on<MenuParkingPage>()
            .onClickPayment()
            .on<MenuParkingPage>()
            .goToPaymentFragment(mockNavController)
    }

}
