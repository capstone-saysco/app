package com.example.saysco.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.saysco.data.remote.retrofit.ApiConfig
import com.example.saysco.data.repository.AuthRepository
import com.example.saysco.databinding.FragmentHomeBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.addNewEssay.AddEssayActivity
import com.example.saysco.ui.login.LoginActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (this.requireActivity() as AppCompatActivity).supportActionBar?.hide()

        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (user.token != null) {
                binding.username.text = user.name
                binding.btnEssayScoring.setOnClickListener {
                    val intent = Intent(requireContext(), AddEssayActivity::class.java)
                    intent.putExtra("idUser", user.id.toString())
                    intent.putExtra("fromPage", "Home")
                    startActivity(intent)
                }
            } else {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}