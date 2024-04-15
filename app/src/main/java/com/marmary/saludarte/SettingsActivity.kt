package com.marmary.saludarte

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


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
