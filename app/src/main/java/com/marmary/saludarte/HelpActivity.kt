package com.marmary.saludarte

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class HelpActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)


        val backButton = findViewById<Button>(R.id.button10)

        backButton.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onBackPressed() {
        // Aquí puedes agregar cualquier lógica adicional antes de cerrar la actividad
        super.onBackPressed()  // Asegúrate de llamar a super para que el comportamiento predeterminado aún se ejecute
    }
}