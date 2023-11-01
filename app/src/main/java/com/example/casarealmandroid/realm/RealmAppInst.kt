package com.example.casarealmandroid.realm

import android.app.Application
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration


class RealmAppInst: Application() {
    override fun onCreate() {
        super.onCreate()
        val config = RealmConfiguration.Builder(setOf(
            DBRealmObject::class, Parent::class, Barcode::class))
            .name("casa")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.open(config)
    }
}


