package com.example.splashscreen


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.splashscreen.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.Signupbutton.setOnClickListener {
            val email = binding.SignupUsername.text.toString()
            val password = binding.SignupPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@SignupActivity,
                                "Signup successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this@SignupActivity,
                                "Signup failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    this@SignupActivity,
                    "All fields are mandatory",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.LoginRedirect.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            finish()
        }
    }
}
