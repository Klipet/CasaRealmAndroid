package com.example.casarealmandroid.view_model



import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.casarealmandroid.realm.Barcode
import com.example.casarealmandroid.realm.DBRealmObject
import com.example.casarealmandroid.realm.Parent
import com.example.casarealmandroid.response_data.asl.Assortment
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.exceptions.RealmException
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.withContext


class AslInsert: ViewModel() {
    val config = RealmConfiguration.create(setOf(DBRealmObject::class, Parent::class, Barcode::class))
    val realm = Realm.open(config)
    val realmOpt = Realm.compactRealm(config)

    suspend fun aslInsert(aslList: List<Assortment>){
        try {
            withContext(Dispatchers.IO){
                realm.write {
                    for (asl in aslList){
                        val aslinsertObject = DBRealmObject().apply {
                        ID = asl.ID
                        AllowDiscounts = asl.AllowDiscounts
                        AllowNonInteger = asl.AllowNonInteger
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
                        copyToRealm(aslinsertObject, updatePolicy = UpdatePolicy.ALL)
                    }

                }

                realm.writeBlocking {
                    for (folder in aslList) {
                         val folder = Parent().apply {
                            if (folder.IsFolder == true) {
                                ID = folder.ID
                                IsFolder = folder.IsFolder
                                Name = folder.Name
                            }
                        }
                        copyToRealm(folder, updatePolicy = UpdatePolicy.ALL)
                    }

                }
                realm.writeBlocking {
                    for (asl in aslList){
                        if (asl.Barcodes != null){
                            for (barcodeItem in asl.Barcodes){
                                val barcodes = Barcode().apply {
                                    ID = asl.ID
                                    barcode = barcodeItem
                                }
                                copyToRealm(barcodes, updatePolicy = UpdatePolicy.ALL)
                            }
                        }

                    }

                }
            }
        }catch (e:RealmException){
            Log.d("Insert Asl", e.message.toString())
        }finally {
            realm.close()

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

