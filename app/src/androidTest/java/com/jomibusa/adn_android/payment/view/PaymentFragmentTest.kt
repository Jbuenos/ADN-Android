package com.jomibusa.adn_android.payment.view

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
class PaymentFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var mockNavController: NavController

    @Before
    fun setUp() {
        hiltRule.inject()
        mockNavController = Mockito.mock(NavController::class.java)
        Page.on<PaymentPage>()
            .onHiltFragment(mockNavController)
    }

    @Test
    fun calculatePayment_writePlateAndFindRegister_success() {
        val plate = "HMT" + (100..999).random()
        Page.on<PaymentPage>()
            .checkVisibilityTotalService(ViewMatchers.Visibility.GONE)
            .on<PaymentPage>()
            .checkVisibilityButtonPayment(ViewMatchers.Visibility.GONE)
            .on<PaymentPage>()
            .replaceTextPlate(plate)
            .on<PaymentPage>()
            .onClickButtonCalculatePayment()
    }

}
