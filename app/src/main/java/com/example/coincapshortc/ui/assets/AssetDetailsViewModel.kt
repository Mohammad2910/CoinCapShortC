package com.example.coincapshortc.ui.assets

import androidx.lifecycle.*
import com.example.coincapshortc.model.AssetsRepository
import com.example.coincapshortc.model.model.Asset
import com.example.coincapshortc.utils.FLOAT_FORMATTER
import com.example.coincapshortc.utils.INTEGER_PRICE_FORMATTER
import com.example.coincapshortc.utils.PRICE_FORMATTER
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalTime

class AssetDetailsViewModel(private val repository: AssetsRepository) : ViewModel() {

    val id: String = "ethereum"
    val name: String = "Ethereum"

    // Option 1 of binding to UI: Collect the flow and throw into a MutableLiveData
    private val asset = MutableLiveData(Asset("", "", 0f, 0f, 0f))
    val price: LiveData<String> = asset.map { PRICE_FORMATTER.format(it.price) }
    val volume: LiveData<String> = asset.map { INTEGER_PRICE_FORMATTER.format(it.volume24Hr) }
    val change: LiveData<String> = asset.map { FLOAT_FORMATTER.format(it.change24Hr) + "%" }
    val updatedAt: LiveData<String> = asset.map { LocalTime.now().toString() }

    // Option 2 of binding to UI: Map the value of the Flow and then convert to StateFlow, that can be bound directly from UI
    val isInProgress: StateFlow<Boolean> = repository.states
        .map { s -> s.isInProgress }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)
    val error: StateFlow<String?> = repository.states
        .map { s -> s.error?.let { "Error: $it" } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    init {
        // Option 1 of binding to UI: Collect the flow and throw into a MutableLiveData
        viewModelScope.launch {
            repository.loadAsset(id)
                .collect { asset.value = it }

        }

        // Also trigger an asset refresh, as we start, so we have up-to-date info
        viewModelScope.launch {
            repository.refreshAsset(id)
        }

    }

    fun onRefreshButtonClicked() {
        viewModelScope.launch {
            repository.refreshAsset(id)

        }
        println("--------------------")
        println(price)
    }
}