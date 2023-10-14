package com.example.casarealmandroid.view_model



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.casarealmandroid.realm.Barcode
import com.example.casarealmandroid.realm.DBRealmObject
import com.example.casarealmandroid.realm.Parent
import com.example.casarealmandroid.response_data.asl.Assortment
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.withContext


class AslInsert: ViewModel() {
    val config = RealmConfiguration.create(setOf(DBRealmObject::class, Parent::class, Barcode::class))
    val realm = Realm.open(config)
    val allAsl by lazy { MutableLiveData<List<DBRealmObject>>() }

    suspend fun aslInsert(aslList: List<Assortment>){
        withContext(Dispatchers.IO){
            realm.writeBlocking {
                val aslinsertObject = DBRealmObject().apply {
                    for (asl in aslList){
                        ID = asl.ID
                        AllowDiscounts = asl.AllowDiscounts
                        AllowNonInteger = asl.AllowNonInteger
                        realm.writeBlocking {
                            copyToRealm(Barcode().apply {
                                for (barcode in asl.Barcodes)
                                    this.ID = asl.ID
                                    this.barcode = barcode
                            })
                        }
                        EnableSaleTimeRange = asl.EnableSaleTimeRange
                        Code = asl.Code
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
                copyToRealm(aslinsertObject)
            }
        }


    }
   suspend fun getAllAsl() {
        return withContext(Dispatchers.IO) {
            val resault = realm.query<DBRealmObject>("ID == %0")
                .find()
                .asFlow()
            resault.collect{

            }
        }
    }
    

}

