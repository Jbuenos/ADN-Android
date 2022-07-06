package com.jomibusa.adn_android.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jomibusa.adn_android.R
import com.jomibusa.adn_android.databinding.FragmentRegisterBinding
import com.jomibusa.adn_android.register.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        setListenerRadioButton()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setListenerRadioButton() {
        binding.radioGroupVehicles.setOnCheckedChangeListener { _, optionId ->
            kotlin.run {
                when (optionId) {
                    R.id.radio_button_car -> {
                        binding.textInputLayoutCylinderCapacity.visibility = View.GONE
                    }
                    R.id.radio_button_motorcycle -> {
                        binding.textInputLayoutCylinderCapacity.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

}