package com.example.notas_trabalho

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notas_trabalho.databinding.FragmentRegisterBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root

    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Erro", "createUserWithEmail:success")
                    val navController = findNavController()

                    navController.navigate(R.id.action_RegisterFragment_to_LoginFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Erro", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        requireContext(),
                        "Registration failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.prxBtn.setOnClickListener() {
            val email = binding.textInputEmail.text.toString()
            val senha = binding.inputSenha.text.toString()

            if (email.isNullOrEmpty() || senha.isNullOrEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Preencha os campos de email e senha.",
                    Toast.LENGTH_SHORT,
                ).show()
            } else {
                createAccount(email, senha)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

private fun updateUI(user: FirebaseUser?) {
}

private fun reload() {
}