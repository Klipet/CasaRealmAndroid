package com.example.casarealmandroid.realm

import android.app.Application
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RealmInit: Application() {
    private var config: RealmConfiguration? = null
    override fun onCreate() {
        super.onCreate()
    //    CoroutineScope(Dispatchers.IO).launch {
    //        Realm.init(this@RealmInit)
    //        config = RealmConfiguration.Builder()
    //            .name("casa")
    //            .deleteRealmIfMigrationNeeded()
    //            .schemaVersion(1)
    //            .allowWritesOnUiThread(true)
    //            .allowQueriesOnUiThread(true)
    //            .build()
    //        config.let { Realm.setDefaultConfiguration(it) }
    //    }
    }
}