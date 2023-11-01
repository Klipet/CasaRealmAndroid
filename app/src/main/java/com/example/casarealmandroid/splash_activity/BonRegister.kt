package com.example.casarealmandroid.splash_activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.casarealmandroid.R
import com.example.casarealmandroid.api.Constant
import com.example.casarealmandroid.databinding.ActivityBonRegisterBinding

class BonRegister : AppCompatActivity() {
    private lateinit var binding: ActivityBonRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBonRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_bon_register)
        setCasaName()
    }
    fun setCasaName() = with (binding){
        val sharedPref = getSharedPreferences(Constant.shared, Context.MODE_PRIVATE)
        val casaName = sharedPref.getString(Constant.CASANAME, "Non")
        tvNameCasa.text = casaName

    }
}