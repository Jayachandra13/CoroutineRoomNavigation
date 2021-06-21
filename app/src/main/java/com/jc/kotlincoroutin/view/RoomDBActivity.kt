package com.jc.kotlincoroutin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jc.kotlincoroutin.R
import com.jc.kotlincoroutin.room.Word
import com.jc.kotlincoroutin.util.WordsApplication
import com.jc.kotlincoroutin.viewmodel.WordViewModel
import com.jc.kotlincoroutin.viewmodel.WordViewModelFactory

class RoomDBActivity : AppCompatActivity() {

    private lateinit var editWordView: EditText

    private val wordViewModel:WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_d_b)

        editWordView = findViewById(R.id.edit_word)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            if (!TextUtils.isEmpty(editWordView.text)){
                val word = Word(editWordView.text.toString())
                wordViewModel.insert(word)
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerviewRoom)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let { adapter.submitList(it) }
        })
    }
}