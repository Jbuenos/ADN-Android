package com.jomibusa.adn_android.payment.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.jomibusa.adn_android.R
import com.jomibusa.adn_android.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito

@ExperimentalCoroutinesApi
@HiltAndroidTest
class PaymentFragmentTest : AutoCloseKoinTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var mockNavController: NavController

    @Before
    fun setUp() {
        hiltRule.inject()
        mockNavController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<PaymentFragment> {
            mockNavController.setGraph(R.navigation.core_nav_graph)
            Navigation.setViewNavController(this.requireView(), mockNavController)
        }
    }

    @Test
    fun calculatePayment_writePlateAndGetTheTotalService_success() {

    }


}