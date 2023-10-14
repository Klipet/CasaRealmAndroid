package com.example.casarealmandroid.view_model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.casarealmandroid.realm.Barcode
import com.example.casarealmandroid.realm.DBRealmObject
import com.example.casarealmandroid.realm.Parent
import com.example.casarealmandroid.response_data.asl.Assortment
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UpdateAsl: ViewModel() {
        val config = RealmConfiguration.create(setOf(DBRealmObject::class, Parent::class, Barcode::class))
        val realm = Realm.open(config)
    fun updateAsl(aslList: List<Assortment>, context: Context){
        CoroutineScope(Dispatchers.Default).launch {
            try {
             realm.write{
                 for(asl in aslList){
                     val existingAsl = realm.query<DBRealmObject>().first().find()
                     if (existingAsl != null){
                         updateobjectFromResponse(existingAsl, asl)
                     }else{
                         val newObject = createObjectFromResponse(asl)
                         copyToRealm(newObject)
                     }
                 }

             }
         }catch (e: Exception){
             Log.d("ExeptionAsl", e.message.toString())
         }finally {
             realm.close()
             withContext(Dispatchers.Main){
                 toastInsert(context)
             }
         }
       }
    }

    private fun updateobjectFromResponse(existingAsl: DBRealmObject, asl: Assortment) {
        existingAsl.ID = asl.ID
        existingAsl.AllowDiscounts = asl.AllowDiscounts
        existingAsl.AllowNonInteger = asl.AllowNonInteger
        existingAsl.EnableSaleTimeRange = asl.EnableSaleTimeRange
        existingAsl.Code = asl.ID
        existingAsl.IsFolder = asl.IsFolder
        existingAsl.Marking = asl.Marking
        existingAsl.Name = asl.Name
        existingAsl.ParentID = asl.ParentID
        existingAsl.Price = asl.Price
        existingAsl.PriceLineEndDate = asl.PriceLineEndDate.toString()
        existingAsl.PriceLineId = asl.PriceLineId
        existingAsl.PriceLineStartDate = asl.PriceLineStartDate
        existingAsl.SaleEndTime = asl.SaleEndTime.toString()
        existingAsl.SaleStartTime = asl.SaleStartTime.toString()
        existingAsl.ShortName = asl.ShortName
        existingAsl.StockBalance = asl.StockBalance
        existingAsl.StockBalanceDate = asl.StockBalanceDate
        existingAsl.Unit = asl.Unit
        existingAsl.VAT = asl.VAT
        existingAsl.VATQuote = asl.VATQuote
        existingAsl.WeightSale = asl.WeightSale

    }
    private fun createObjectFromResponse(asl: Assortment): DBRealmObject {
        return DBRealmObject().apply {
            AllowDiscounts = asl.AllowDiscounts
            AllowNonInteger = asl.AllowNonInteger
            EnableSaleTimeRange = asl.EnableSaleTimeRange
            Code = asl.ID
            IsFolder = asl.IsFolder
            Marking = asl.Marking
            Name = asl.Name
            ParentID = asl.ParentID
            Price = asl.Price
            PriceLineEndDate = asl.PriceLineEndDate.toString()
            PriceLineId = asl.PriceLineId
            PriceLineStartDate = asl.PriceLineStartDate
            SaleEndTime = asl.SaleEndTime.toString()
            SaleStartTime = asl.SaleStartTime.toString()
            ShortName = asl.ShortName
            StockBalance = asl.StockBalance
            StockBalanceDate = asl.StockBalanceDate
            Unit = asl.Unit
            VAT = asl.VAT
            VATQuote = asl.VATQuote
            WeightSale = asl.WeightSale
        }

    }
    fun toastInsert(context: Context){
        Toast.makeText(context, "Complet", Toast.LENGTH_LONG).show()
    }

}