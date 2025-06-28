package com.example.notas_trabalho

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notas_trabalho.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        val dados_nome = arguments?.getString("nome_completo") ?: "Valor padrão"

        val navController = findNavController()

        binding.singOut.setOnClickListener() {
            edit.clear().apply()

            navController.navigate(R.id.action_FirstFragment_to_LoginFragment)
        }

        val lembrete = sharedPref.getString("lembrete", "")
        binding.textInputEditText.setText(lembrete)

        binding.deleteButton.setOnClickListener {
            binding.textInputEditText.text?.clear()
            edit.remove("lembrete").apply()
        }

        binding.saveButton.setOnClickListener {
            val notas = binding.textInputEditText.text.toString()
            edit.putString("lembrete", notas).apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
