package com.jomibusa.adn_android.register.view

import androidx.navigation.NavController
import androidx.test.espresso.matcher.ViewMatchers
import com.jomibusa.adn_android.shared.Page
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
        Page.on<RegisterPage>()
            .onHiltFragment(mockNavController)
    }

    @Test
    fun registerCar_writeAPlateAndGoBack_success() {
        val plate = "HMT" + (100..999).random()
        Page.on<RegisterPage>()
            .onClickButtonCar()
            .on<RegisterPage>()
            .checkVisibilityCylinderCapacity(ViewMatchers.Visibility.GONE)
            .on<RegisterPage>()
            .replaceTextPlate(plate)
            .on<RegisterPage>()
            .onClickButtonRegister()
    }

    @Test
    fun registerMotorcycle_writeAPlateAndGoBack_success() {
        val plate = "upa" + (10..99).random() + "C"
        Page.on<RegisterPage>()
            .onClickButtonMotorcycle()
            .on<RegisterPage>()
            .checkVisibilityCylinderCapacity(ViewMatchers.Visibility.VISIBLE)
            .on<RegisterPage>()
            .replaceTextPlate(plate)
            .on<RegisterPage>()
            .replaceTextCylinderCapacity()
            .on<RegisterPage>()
            .onClickButtonRegister()
    }

}
