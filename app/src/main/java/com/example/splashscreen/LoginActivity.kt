package com.example.splashscreen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.splashscreen.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        sharedPreferences = getSharedPreferences("login_info", Context.MODE_PRIVATE)


        // Check if the last login was within the desired time frame
        val lastLoginTimestamp = sharedPreferences.getLong("last_login_timestamp", 0)
        val currentTimeMillis = System.currentTimeMillis()
        val timeDifference = currentTimeMillis - lastLoginTimestamp
        val loginThreshold = 24 * 60 * 60 * 1000 // 24 hours in milliseconds

        if (timeDifference < loginThreshold) {
            // Last login was within the desired time frame, proceed to main activity
            startActivity(Intent(this, SubjectActivity::class.java))
            finish()
        }


        binding.Loginbutton.setOnClickListener {
            val email = binding.LoginUsername.text.toString()
            val password = binding.LoginPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Login successful",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@LoginActivity, SubjectActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Login failed: ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    this@LoginActivity,
                    "All fields are mandatory",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.SignupRedirect.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            finish()
        }
    }
    // Function to update last login timestamp in SharedPreferences
    private fun updateLastLoginTimestamp() {
        val editor = sharedPreferences.edit()
        editor.putLong("last_login_timestamp", System.currentTimeMillis())
        editor.apply()
    }
}
