package com.example.saysco.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.saysco.data.model.User
import com.example.saysco.databinding.FragmentHomeBinding
import com.example.saysco.ui.newscoring.NewScoringActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Dengan data login, sementara belum implementasi login maka menggunakan objek user
        val user = User(idUser = "a123", username = "Fahns", "ahnafbawedan01@gmail", token = "123", isLogin = true)
        binding.btnEssayScoring.setOnClickListener{
            val intent = Intent(context, NewScoringActivity::class.java)
            intent.putExtra("idUser", user.idUser)
            intent.putExtra("fromPage", "Home")
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}