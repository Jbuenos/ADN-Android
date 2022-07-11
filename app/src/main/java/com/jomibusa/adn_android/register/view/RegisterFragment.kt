package com.jomibusa.adn_android.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jomibusa.adn_android.R
import com.jomibusa.adn_android.databinding.FragmentRegisterBinding
import com.jomibusa.adn_android.register.viewmodel.RegisterViewModel
import com.jomibusa.domain.register.exception.CapacityParkingExceededException
import com.jomibusa.domain.register.exception.ExistSameVehicleException
import com.jomibusa.domain.vehicle.exception.InvalidPatternPlateException
import com.jomibusa.domain.vehicle.model.Car
import com.jomibusa.domain.vehicle.model.Motorcycle
import com.jomibusa.domain.vehicle.model.Plate
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    private var isCar = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        setListenerRadioButton()

        setListenerRegister()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getError.observe(viewLifecycleOwner) { exception ->
            parseException(exception)
        }

        viewModel.getResultNewRegister.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.text_message_register_success),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().popBackStack()
        }

    }

    private fun setListenerRadioButton() {
        binding.radioGroupVehicles.setOnCheckedChangeListener { _, optionId ->
            kotlin.run {
                when (optionId) {
                    R.id.radio_button_car -> {
                        isCar = true
                        binding.textInputLayoutCylinderCapacity.visibility = View.GONE
                    }
                    R.id.radio_button_motorcycle -> {
                        isCar = false
                        binding.textInputLayoutCylinderCapacity.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setListenerRegister() {
        binding.materialButtonRegister.setOnClickListener {
            try {
                val vehicle = if (isCar) {
                    Car(Plate(binding.textInputEditTextPlate.text.toString().uppercase()))
                } else {
                    Motorcycle(
                        binding.textInputEditTextCylinderCapacity.text.toString().toInt(),
                        Plate(binding.textInputEditTextPlate.text.toString().uppercase())
                    )
                }
                viewModel.insertNewRegister(vehicle)
            } catch (e: Exception) {
                parseException(e)
            }
        }
    }

    private fun parseException(exception: Exception) {
        val message = when (exception) {
            is CapacityParkingExceededException -> exception.message
            is ExistSameVehicleException -> exception.message
            is InvalidPatternPlateException -> exception.message
            else -> getString(R.string.text_message_general_exception)
        }
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}