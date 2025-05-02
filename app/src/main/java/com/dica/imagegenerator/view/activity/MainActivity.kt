package com.dica.imagegenerator.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dica.imagegenerator.databinding.ActivityMainBinding
import com.dica.imagegenerator.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        binding.btnGenerate.setOnClickListener {
            val prompt = binding.etPrompt.text.toString()
            viewModel.generateImage(prompt)
        }
    }

    private fun observeViewModel() {
        viewModel.image.observe(this) {
            binding.imageView.setImageBitmap(it)
        }

        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}