package com.example.recuclerview

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recuclerview.databinding.ActivityMailDetailBinding
import com.example.recuclerview.databinding.ActivityMainBinding
import kotlin.concurrent.timer

class MailDetailActivity:AppCompatActivity() {
    private lateinit var binding: ActivityMailDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMailDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }
    private fun initViews(){
        val email = intent.extras?.get("Data") as MailDetails
        with(binding){
            fromTV.text = email.sender
            subjectTV.text = email.title
            bodyTV.text = email.body

        }
    }
}