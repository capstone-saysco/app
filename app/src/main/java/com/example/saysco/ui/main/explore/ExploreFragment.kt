package com.example.saysco.ui.main.explore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saysco.databinding.FragmentExploreBinding
import com.example.saysco.ui.ViewModelFactory
import com.example.saysco.ui.adapter.EssayAdapter
import com.example.saysco.ui.main.home.HomeViewModel
import com.example.saysco.data.Result
import com.example.saysco.data.model.StudentAnswer
import com.example.saysco.data.remote.response.EssayItem
import com.example.saysco.ui.adapter.StudentAnswerEditAdapter
import com.example.saysco.ui.addNewEssay.AddEssayActivity
import com.example.saysco.ui.addNewEssay.studentAnswer.editStudentAnswer.EditStudentAnswerActivity
import com.example.saysco.ui.listAnwers.ListAnswerActivity
import com.example.saysco.ui.login.LoginActivity
import com.google.android.material.snackbar.Snackbar


class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ExploreViewModel> {
        ViewModelFactory.getInstance(requireActivity())
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rcEssay.layoutManager = LinearLayoutManager(requireContext())
        binding.rcEssay.setHasFixedSize(true)
        val adapter = EssayAdapter()


        viewModel.getSession().observe(viewLifecycleOwner) { user ->
            if (user.token != null) {
                viewModel.essays.observe(viewLifecycleOwner) { result ->
                    if (result != null) {
                        when (result) {
                            is Result.Loading -> {
                                showLoading(true)
                            }

                            is Result.Success -> {
                                showLoading(false)
                                val essays = result.data.data
                                adapter.submitList(essays)
                                binding.rcEssay.adapter = adapter
                                adapter.setOnItemClickCallback(object : EssayAdapter.OnItemClickCallback {
                                    override fun onItemClicked(essay: EssayItem) {
                                        val intent = Intent(
                                            requireActivity(),
                                            ListAnswerActivity::class.java
                                        )
                                        intent.putExtra("userEssay", essay)
                                        startActivity(intent)
                                    }
                                })
                            }

                            is Result.Error -> {
                                showLoading(false)
                            }
                        }
                    }
                }

                viewModel.loadEssays(user.token)
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility =
            View.VISIBLE else binding.progressBar.visibility = View.GONE
    }

    private fun showAlert(string: String) {
        Snackbar.make(binding.root, string, Snackbar.LENGTH_LONG).show()
    }
}