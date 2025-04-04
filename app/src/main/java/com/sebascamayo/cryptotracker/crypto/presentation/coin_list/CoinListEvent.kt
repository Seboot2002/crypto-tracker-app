package com.sebascamayo.cryptotracker.crypto.presentation.coin_list

import com.sebascamayo.cryptotracker.core.domain.utils.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}