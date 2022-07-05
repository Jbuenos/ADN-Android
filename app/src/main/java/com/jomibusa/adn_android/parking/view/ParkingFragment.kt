package com.jomibusa.adn_android.parking.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jomibusa.adn_android.databinding.FragmentParkingBinding

class ParkingFragment : Fragment() {

    private var _binding: FragmentParkingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentParkingBinding.inflate(inflater, container, false)

        binding.apply {

            materialButtonRegister.setOnClickListener {
                findNavController().navigate(ParkingFragmentDirections.actionParkingFragmentToRegisterFragment())
            }

            materialButtonPayment.setOnClickListener {
                findNavController().navigate(ParkingFragmentDirections.actionParkingFragmentToPaymentFragment())
            }

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}