package com.example.saysco.ui.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.saysco.databinding.FragmentProfileBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.login.LoginActivity


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        (this.requireActivity() as AppCompatActivity).supportActionBar?.show()
        (this.requireActivity() as AppCompatActivity).supportActionBar?.title = "Profile"

        viewModel.getUser().observe(this.requireActivity()) {
            with(binding) {
                textName.text = it.name
                textEmail.text = it.email
            }
        }

        binding.cvLogout.setOnClickListener {
            viewModel.logout()
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}