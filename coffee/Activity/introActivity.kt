package com.example.coffee.Activity

import android.content.Intent
import android.os.Bundle
import com.example.coffee.databinding.ActivityIntroBinding

class introActivity : BaseActivity() {
    lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityIntroBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            startActivity(Intent(this@introActivity, MainActivity::class.java))
        }

    }
}