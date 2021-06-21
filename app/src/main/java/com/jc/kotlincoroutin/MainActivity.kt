package com.jc.kotlincoroutin

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.jc.kotlincoroutin.view.NavigationActivity
import com.jc.kotlincoroutin.view.RetrofitActivity
import com.jc.kotlincoroutin.view.RoomDBActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val IMAGE_URL =
        "https://raw.githubusercontent.com/DevTides/JetpackDogsApp/master/app/src/main/res/drawable/dog.png"

    val coroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineScope.launch {
            val differed = coroutineScope.async(Dispatchers.IO) { getOriginalBitmap() }
            val imageBitmap = differed.await()
            val filterDifferedResults = coroutineScope.async(Dispatchers.Default) { Filter.apply(imageBitmap) }
            val filteredBitmap = filterDifferedResults.await()
            loadImage(filteredBitmap)
        }
        imageView.setOnClickListener { startActivity(Intent(this, RetrofitActivity::class.java)) }
        btnRoomDB.setOnClickListener { startActivity(Intent(this, RoomDBActivity::class.java)) }
        btnNavigation.setOnClickListener { startActivity(Intent(this, NavigationActivity::class.java)) }
    }

    private fun getOriginalBitmap() =
        URL(IMAGE_URL).openStream().use {
            BitmapFactory.decodeStream(it)
        }

    private fun loadImage(bitmap: Bitmap){
        progressBar.visibility = View.GONE
        imageView.setImageBitmap(bitmap)
        imageView.visibility = View.VISIBLE
    }
}