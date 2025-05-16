package com.example.notas_trabalho

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        val sharedPref = requireContext().getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        val dados_nome = arguments?.getString("nome_completo") ?: "Valor padrão"

        binding.nomeView.setText("Olá, " + dados_nome)

        val lembrete = sharedPref.getString("lembrete", "Sem notas")
        binding.textInputEditText.setText(lembrete)

        binding.deleteButton.setOnClickListener {
            binding.textInputEditText.text?.clear()
            edit.clear().apply()
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
