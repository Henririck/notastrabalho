package com.example.notas_trabalho

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notas_trabalho.databinding.FragmentLoginBinding
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.Firebase

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root

    }

    private lateinit var auth: FirebaseAuth

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("Erro", "signInWithEmail:success")
                val user = auth.currentUser

                user?.getIdToken(true)?.addOnSuccessListener { result ->
                    val token = result.token
                    val sharedPref = requireActivity().getSharedPreferences("user_prefs", 0)
                    with(sharedPref.edit()) {
                        putString("user_token", token)
                        apply()
                    }
                }
            } else {
                // If sign in fails, display a message to the user.
                Log.w("Erro", "signInWithEmail:failure", task.exception)
                Toast.makeText(
                    requireContext(),
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        binding.buttonProximo.setOnClickListener() {
            val email = binding.inputNome.text.toString()
            val password = binding.inputSobrenome.text.toString()

            if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Preencha os campos de email e senha.",
                    Toast.LENGTH_SHORT,
                ).show()
            } else {
                signIn(email, password)

                navController.navigate(R.id.action_LoginFragment_to_FirstFragment)
            }
        }

        binding.btnRegistrar.setOnClickListener() {
            navController.navigate(R.id.action_LoginFragment_to_RegisterFragment)
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