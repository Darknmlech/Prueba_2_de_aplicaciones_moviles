package com.example.prueba2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()

        val etEmailReg = findViewById<EditText>(R.id.etEmailReg)
        val etPasswordReg = findViewById<EditText>(R.id.etPasswordReg)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            val email = etEmailReg.text.toString().trim()
            val password = etPasswordReg.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "¡Usuario registrado con éxito!", Toast.LENGTH_SHORT).show()
                        finish() // Regresa a la pantalla anterior
                    } else {
                        Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}