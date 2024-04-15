package com.marmary.saludarte


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener


class MainActivity : AppCompatActivity() {


    private lateinit var webSocket: WebSocket
    private val client by lazy { OkHttpClient() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeWebSocket()
        val buttonIds = listOf(
            R.id.button, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8
        )

        // Asignar el mismo manejador de clics a todos los botones
        buttonIds.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener { button ->
                sendMessage((button as Button).text.toString())
            }
        }

        val settingButton = findViewById<Button>(R.id.setting_button)

        settingButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        val connectButton = findViewById<Button>(R.id.connect_button)

        connectButton.setOnClickListener {
            reconnectWebSocket()
        }

    }


    private fun initializeWebSocket() {
        val request = Request.Builder().url("ws://192.168.0.118:81").build()
        val listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
                this@MainActivity.webSocket = webSocket
                runOnUiThread { Toast.makeText(this@MainActivity, "Conexión WebSocket abierta", Toast.LENGTH_SHORT).show() }
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                runOnUiThread { Toast.makeText(this@MainActivity, "Mensaje recibido: $text", Toast.LENGTH_SHORT).show() }
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                println("Cierre del Socket: $code / $reason")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
                t.printStackTrace()
            }
        }

        webSocket = client.newWebSocket(request, listener)
    }

    private fun sendMessage(message: String) {
        if (this::webSocket.isInitialized && webSocket.send(message)) {
            Toast.makeText(this, "Mensaje enviado: $message", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error al enviar mensaje", Toast.LENGTH_SHORT).show()
        }
    }


    private fun reconnectWebSocket() {
        // Cerrar el WebSocket actual si está abierto
        if (this::webSocket.isInitialized) {
            webSocket.close(1000, "Reconectando")
        }

        // Inicializar una nueva conexión WebSocket
        initializeWebSocket()
    }



    override fun onDestroy() {
        super.onDestroy()
        webSocket.close(1000, "Aplicación cerrada")
        client.dispatcher.executorService.shutdown()
    }



}