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

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        val backButton = findViewById<Button>(R.id.button10)

        val defaultButton = findViewById<Button>(R.id.button12)

        val inputText = findViewById<TextInputEditText>(R.id.textInputEditText)

        val sharedPreferences = getSharedPreferences("MiPreferencia", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        defaultButton.setOnClickListener {inputText.setText("192.168.0.118")}


// Recuperar el String
        val valorGuardado = sharedPreferences.getString("miClave", "192.168.0.118")


        inputText.setText(valorGuardado)


        inputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Aquí puedes manejar acciones antes de que el texto cambie
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                // Haz algo con el texto
                Log.d("MainActivity", "Texto actual: ${s.toString()}")
                // Guardar un String
                editor.putString("miClave", s.toString())
                editor.apply() // o puedes usar editor.commit()


            }

            override fun afterTextChanged(s: Editable?) {
                // Aquí puedes manejar acciones después de que el texto ha cambiado
            }
        })


        backButton.setOnClickListener {
            onBackPressed()
        }



    }

    override fun onBackPressed() {
        // Aquí puedes agregar cualquier lógica adicional antes de cerrar la actividad
        super.onBackPressed()  // Asegúrate de llamar a super para que el comportamiento predeterminado aún se ejecute
    }

    fun miFuncion(texto: String) {

    }
}
