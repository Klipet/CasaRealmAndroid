package com.example.casarealmandroid.splash_activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.casarealmandroid.api.Constant
import com.example.casarealmandroid.api.RetrofitApi
import com.example.casarealmandroid.databinding.ActivityRegisterBinding
import com.example.casarealmandroid.realm.DBRealmObject
import com.example.casarealmandroid.view_model.AslInsert
import com.example.casarealmandroid.view_model.UpdateAsl
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class Register : AppCompatActivity() {
    private lateinit var bindind: ActivityRegisterBinding
    private lateinit var viewModelAsl: AslInsert
    private lateinit var viewModelDeleteAsl: UpdateAsl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityRegisterBinding.inflate(layoutInflater)
        val view = bindind.root
        setContentView(view)
        viewModelAsl = ViewModelProvider(this)[AslInsert::class.java]
        viewModelDeleteAsl = ViewModelProvider(this)[UpdateAsl::class.java]
        bindind.button.setOnClickListener {
            InsertToApp()
        }
    }

    fun InsertToApp () {
        try {
            val login = bindind.tvLogin.text.toString()
            val password = bindind.tvPassword.text.toString()
            val sharedPref = this.getSharedPreferences(Constant.shared, Context.MODE_PRIVATE)
            val spEdit = sharedPref.edit()
            spEdit.putString(Constant.LOGIN, login).putString(Constant.PASS, password)
            spEdit.apply()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val retrofitToken =
                        RetrofitApi.api.getTokenUser(login.toString(), password.toString())
                    if (retrofitToken.AuthentificateUserResult.ErrorCode == 0) {
                        val responseToken = retrofitToken?.AuthentificateUserResult?.Token
                        spEdit.putString(Constant.TOKEN, responseToken)
                        spEdit.apply()
                        withContext(Dispatchers.Main) {
                            getCasa(responseToken.toString())
                        }
                    }
                }catch (e: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@Register, e.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } catch (e: Exception){
            Log.d("ErrorToken", e.message.toString())
        } catch (e: IOException){
            Log.d("IOErrorToken", e.message.toString())
        }finally {

        }
    }
    fun getCasa(token: String){
        CoroutineScope(Dispatchers.IO).launch {
            val casaReqest = RetrofitApi.api.getPlace(token).execute()
            val casa = casaReqest.body()!!.GetWorkplacesResult
            val casaArr = casa.Workplaces.map { it.Name }.toTypedArray()
            val sharedPref = this@Register.getSharedPreferences(Constant.shared, Context.MODE_PRIVATE)
            val spEdit = sharedPref.edit()
            val casaNameInsert =  sharedPref.getString(Constant.CWSpase, "not")
            withContext(Dispatchers.Main){
                if (casaNameInsert == "not"){
                    val builder = AlertDialog.Builder(this@Register)
                    builder.setTitle("Выберите Кассу")
                    builder.setItems(casaArr) { dialog, switc ->
                        val casaID = casa.Workplaces.get(switc)?.ID.toString()
                        spEdit.putString(Constant.CWSpase, casaID)
                        spEdit.apply()
                        val casaName = casa.Workplaces.get(switc).Name.toString()
                        spEdit.putString(Constant.CASANAME, casaName)
                        spEdit.apply()
                        insertAsortimant(casaID, token)
                    }.show()
                }else{
                    insertAsortimant(casaNameInsert.toString(), token)
                }
            }
        }
    }
    private fun insertAsortimant(casa: String, token: String){
        val progressBar = bindind.progressBar
        try {
            CoroutineScope(Dispatchers.IO).launch {
                val aslReqest = RetrofitApi.api.getAslWP(token, casa)
                val asortiment = aslReqest.Assortments
                startActivity(viewModelAsl.aslInsert(asortiment, this@Register))
                withContext(Dispatchers.Main){

                }
           }
        }catch (e: Exception){
            Log.d("ErroeAsl", e.message.toString())
        }finally {
          //  val  intent = Intent(this, BonRegister::class.java)
          //  startActivity(intent)
          //  finish()
        }
    }
}