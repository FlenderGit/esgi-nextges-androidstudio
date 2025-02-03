package fr.flender.nextges

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity: AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText: EditText = findViewById(R.id.ipt_mail)
        val passwordEditText: EditText = findViewById(R.id.ipt_password)
        val loginButton: Button = findViewById(R.id.btn_login)

        val uiElements = listOf(emailEditText, passwordEditText, loginButton)

        fun setUiEnabled(enabled: Boolean) {
            uiElements.forEach { it.isEnabled = enabled }
        }

//        val test = getSharedPreferences("test", MODE_APPEND)
//        with(test.edit()) {
//            putBoolean("isloggin", true)
//        }



        if (!::auth.isInitialized) auth = FirebaseAuth.getInstance()

        FirebaseFirestore.setLoggingEnabled(true)
        FirebaseAuth.getInstance().firebaseAuthSettings.setAppVerificationDisabledForTesting(false)

        if (auth.currentUser != null) {
            Log.d("Fast Login", "Alreadu connected")
            navigateToMainActivity()
        }


        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            when {
                !isValidEmail(email) -> {
                    showToast("Email non valide")
                }
                password.isEmpty() -> {
                    passwordEditText.error = "Mot de passe requis"
                }
                else -> {
                    setUiEnabled(false)
                    Log.d("Before login", "Jean")

                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            setUiEnabled(true)
                            Log.d("Login résult", task.isSuccessful.toString())
                            if (task.isSuccessful) {
                                navigateToMainActivity()
                            } else {
                                showToast("Échec de la connexion : ${task.exception?.message}")
                            }
                        }
                }
            }
        }
    }

    // Helper function to display a toast
    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    // Navigate to the main activity
    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}