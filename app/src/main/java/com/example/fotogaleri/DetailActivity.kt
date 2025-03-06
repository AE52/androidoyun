package com.example.fotogaleri

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var detailImageView: ImageView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // UI bileşenlerini başlat
        detailImageView = findViewById(R.id.detailImageView)
        backButton = findViewById(R.id.backButton)

        // Intent ile gelen fotoğrafı al ve göster
        val imageUriString = intent.getStringExtra(MainActivity.IMAGE_URI_KEY)
        
        if (imageUriString != null) {
            try {
                val uri = Uri.parse(imageUriString)
                detailImageView.setImageURI(uri)
            } catch (e: Exception) {
                Toast.makeText(this, "Fotoğraf gösterilemiyor: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Fotoğraf bulunamadı", Toast.LENGTH_SHORT).show()
        }

        // Geri butonuna basıldığında aktiviteyi kapat
        backButton.setOnClickListener {
            finish()
        }
    }
} 