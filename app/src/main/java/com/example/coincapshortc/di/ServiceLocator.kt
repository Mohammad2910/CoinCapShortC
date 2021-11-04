package com.example.coincapshortc.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.get
import com.example.coincapshortc.model.AssetsRepository
import com.example.coincapshortc.model.api.CoinCapApi
import com.example.coincapshortc.ui.assets.AssetDetailsViewModel

//import com.example.coincapshortc.ui.rates.RatesViewModel

object ServiceLocator {

    private lateinit var applicationContext: Context

    fun init(applicationContext: Context) {
        ServiceLocator.applicationContext = applicationContext
    }

//    private val gameApi by lazy {
//        GameApi()
//    }

//    private val gameDB by lazy {
//        GameDatabase()
//    }

//    val database: AppDatabase by lazy {
//        AppDatabase.build(this.applicationContext)
//    }

    private val coinCapApi: CoinCapApi by lazy {
        CoinCapApi.build()
    }

//    private val gameRepository: GameRepository by lazy {
//        GameRepository(gameApi, gameDB)
//    }

    private val assetsRepository: AssetsRepository by lazy {
        AssetsRepository(coinCapApi)
    }

    private val viewModelFactory by lazy {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return when (modelClass) {
                    //MainViewModel::class.java -> MainViewModel(gameRepository)
                    AssetDetailsViewModel::class.java -> AssetDetailsViewModel(assetsRepository)
                    //RatesViewModel::class.java -> RatesViewModel(coinCapApi)
                    else -> throw IllegalArgumentException("Unsupported ViewModel $modelClass")
                } as T
            }
        }
    }

//    val ViewModelStoreOwner.mainViewModel: MainViewModel
//        get() = ViewModelProvider(this, viewModelFactory).get()

    val ViewModelStoreOwner.assetDetailsViewModel: AssetDetailsViewModel
        get() = ViewModelProvider(this, viewModelFactory).get()

//    val ViewModelStoreOwner.ratesViewModel: RatesViewModel
//        get() = ViewModelProvider(this, viewModelFactory).get()
}