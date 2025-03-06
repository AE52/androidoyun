package com.example.fotogaleri

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var selectedImageView: ImageView
    private lateinit var noPhotoText: TextView
    private lateinit var selectButton: Button
    private lateinit var sendButton: Button
    
    private var selectedImageUri: Uri? = null
    private var tempImagePath: String? = null
    
    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
        private const val GALLERY_REQUEST_CODE = 101
        const val IMAGE_URI_KEY = "image_uri"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // UI bileşenlerini başlat
        selectedImageView = findViewById(R.id.selectedImageView)
        noPhotoText = findViewById(R.id.noPhotoText)
        selectButton = findViewById(R.id.selectButton)
        sendButton = findViewById(R.id.sendButton)
        
        // Buton tıklama olaylarını ayarla
        selectButton.setOnClickListener {
            checkPermissionAndOpenGallery()
        }
        
        sendButton.setOnClickListener {
            sendImageToDetailActivity()
        }
    }
    
    private fun checkPermissionAndOpenGallery() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        } else {
            openGallery()
        }
    }
    
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.isNotEmpty()) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                Toast.makeText(
                    this,
                    "Fotoğrafları görmek için izin gerekiyor",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            // Seçilen fotoğrafın URI'sini al
            selectedImageUri = data.data
            
            if (selectedImageUri != null) {
                // ImageView'da göster
                selectedImageView.setImageURI(selectedImageUri)
                noPhotoText.visibility = View.GONE
                sendButton.isEnabled = true
            }
        }
    }
    
    private fun saveBitmapToFile(bitmap: Bitmap): String {
        val file = File(cacheDir, "temp_image_${System.currentTimeMillis()}.jpg")
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
        outputStream.flush()
        outputStream.close()
        return file.absolutePath
    }
    
    private fun sendImageToDetailActivity() {
        selectedImageUri?.let { uri ->
            val intent = Intent(this, DetailActivity::class.java)
            
            // URI'yi intent ile gönder
            intent.putExtra(IMAGE_URI_KEY, uri.toString())
            
            startActivity(intent)
        }
    }
} 