package com.example.prueba2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class DashboardActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        db = FirebaseFirestore.getInstance()

        val btnAlimentar = findViewById<Button>(R.id.btnAlimentar)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        btnAlimentar.setOnClickListener {
            val timestamp = Date()

            val registroComida = hashMapOf(
                "dispositivo" to "Smart Pet Feeder 01",
                "accion" to "Alimentación manual",
                "fecha" to timestamp.toString()
            )

            db.collection("historial_alimentacion")
                .add(registroComida)
                .addOnSuccessListener {
                    Toast.makeText(this, "¡Comida dispensada y guardada en la nube! 🐾", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}