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
import com.jomibusa.adn_android.payment.model.VehicleType
import com.jomibusa.adn_android.register.model.CarVehicleRegister
import com.jomibusa.adn_android.register.model.MotorcycleVehicleRegister
import com.jomibusa.adn_android.register.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    private var vehicleType = VehicleType.CAR

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
            showMessage(exception)
        }

        viewModel.getResultNewRegister.observe(viewLifecycleOwner) {
            showMessage(getString(R.string.text_message_register_success))
            findNavController().popBackStack()
        }

    }

    private fun setListenerRadioButton() {
        binding.radioGroupVehicles.setOnCheckedChangeListener { _, optionId ->
            kotlin.run {
                when (optionId) {
                    R.id.radio_button_car -> {
                        vehicleType = VehicleType.CAR
                        binding.textInputLayoutCylinderCapacity.visibility = View.GONE
                    }
                    R.id.radio_button_motorcycle -> {
                        vehicleType = VehicleType.MOTORCYCLE
                        binding.textInputLayoutCylinderCapacity.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setListenerRegister() {
        binding.materialButtonRegister.setOnClickListener {
            when (vehicleType) {
                VehicleType.CAR -> viewModel.insertNewRegister(
                    CarVehicleRegister,
                    binding.textInputEditTextPlate.text.toString().uppercase()
                )
                VehicleType.MOTORCYCLE -> if (!isEmptyCylinderCapacity()) {
                    viewModel.insertNewRegister(
                        MotorcycleVehicleRegister,
                        binding.textInputEditTextPlate.text.toString().uppercase(),
                        binding.textInputEditTextCylinderCapacity.text.toString().toInt()
                    )
                } else {
                    binding.textInputEditTextCylinderCapacity.error =
                        getString(R.string.text_message_empty_cylinder_capacity)
                }
            }
        }
    }

    private fun isEmptyCylinderCapacity() =
        binding.textInputEditTextCylinderCapacity.text.toString().isEmpty()

    private fun showMessage(message: String?) {
        Toast.makeText(
            requireContext(),
            message ?: getString(R.string.text_message_general_exception),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}