package com.example.casarealmandroid.realm




import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


open class DBRealmObject: RealmObject {
    @PrimaryKey
    var ID: String = ""
    var AllowDiscounts: Boolean? = false
    var AllowNonInteger: Boolean? = false
    @Ignore
    var Barcodes: RealmList<Barcode>? = realmListOf()
    var EnableSaleTimeRange: Boolean? = false
    var Code: String? = null
    var IsFolder: Boolean? = false
    var Marking: String? = null
    var Name: String? = null
    var ParentID: String? = null
    var Price: Double? = 0.0
    var PriceLineEndDate: String? = null
    var PriceLineId: String? = null
    var PriceLineStartDate: String? = null
    var SaleEndTime: String? = null
    var SaleStartTime: String? = null
    var ShortName: String? = null
    var StockBalance: Int? = 0
    var StockBalanceDate: Int? = 0
    var Unit: String? = null
    var VAT: Double? = 0.0
    var VATQuote: String? = null
    var WeightSale: Boolean? = false
    }

open class Barcode: RealmObject{
    @PrimaryKey
    var ID: String = ""
    var barcode: String? = null
}

open class Parent: RealmObject{
    @PrimaryKey
    var ID: String = ""
    var IsFolder: Boolean? = false
    var Name: String? = null
}


