package com.jomibusa.adn_android.payment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jomibusa.adn_android.R
import com.jomibusa.adn_android.databinding.FragmentPaymentBinding
import com.jomibusa.adn_android.payment.model.VehicleType
import com.jomibusa.adn_android.payment.util.Util.parseCurrency
import com.jomibusa.adn_android.payment.viewmodel.PaymentViewModel
import com.jomibusa.domain.register.model.Register
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PaymentViewModel by viewModels()

    private lateinit var register: Register

    private var vehicleType = VehicleType.CAR

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(inflater, container, false)

        binding.apply {

            materialButtonCalculatePayment.setOnClickListener {
                val plate = textInputEditTextSearch.text.toString().uppercase()
                viewModel.calculateService(vehicleType, plate)
            }

            materialButtonPayment.setOnClickListener {
                viewModel.doPayment(vehicleType, register)
            }

            setListenerRadioButton()

        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getValueService.observe(viewLifecycleOwner) { payment ->
            validatePayment(payment)
        }

        viewModel.doPayment.observe(viewLifecycleOwner) { result ->
            if (result != -1) {
                showMessage(getString(R.string.text_message_payment_success))
                findNavController().popBackStack()
            } else {
                showMessage(getString(R.string.text_message_general_exception))
            }
        }

        viewModel.getError.observe(viewLifecycleOwner) { isError ->
            if (isError) {
                showMessage(getString(R.string.text_message_general_exception))
                findNavController().popBackStack()
            }
        }

    }

    private fun setListenerRadioButton() {
        binding.radioGroupVehicles.setOnCheckedChangeListener { _, optionId ->
            kotlin.run {
                when (optionId) {
                    R.id.radio_button_car -> {
                        vehicleType = VehicleType.CAR
                    }
                    R.id.radio_button_motorcycle -> {
                        vehicleType = VehicleType.MOTORCYCLE
                    }
                }
            }
        }
    }

    private fun validatePayment(payment: Pair<Register?, Double>) {
        if (payment.first != null) {
            register = payment.first!!
            binding.materialButtonCalculatePayment.visibility = View.GONE
            binding.radioGroupVehicles.visibility = View.GONE
            binding.textViewPayment.visibility = View.VISIBLE
            binding.textViewPayment.text =
                getString(R.string.text_message_total_service, payment.second.parseCurrency())
            binding.materialButtonPayment.visibility = View.VISIBLE
        } else {
            val message = when (vehicleType) {
                VehicleType.CAR -> getString(R.string.text_message_car_not_found)
                VehicleType.MOTORCYCLE -> getString(R.string.text_message_motorcycle_not_found)
            }
            showMessage(message)
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(
            requireContext(),
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}