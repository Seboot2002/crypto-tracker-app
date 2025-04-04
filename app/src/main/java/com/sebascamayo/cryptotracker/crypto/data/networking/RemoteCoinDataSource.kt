package com.sebascamayo.cryptotracker.crypto.data.networking

import com.sebascamayo.cryptotracker.BuildConfig
import com.sebascamayo.cryptotracker.core.data.networking.constructUrl
import com.sebascamayo.cryptotracker.core.data.networking.safeCall
import com.sebascamayo.cryptotracker.core.domain.utils.NetworkError
import com.sebascamayo.cryptotracker.core.domain.utils.Result
import com.sebascamayo.cryptotracker.core.domain.utils.map
import com.sebascamayo.cryptotracker.crypto.data.mappers.toCoin
import com.sebascamayo.cryptotracker.crypto.data.mappers.toCoinPrice
import com.sebascamayo.cryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.sebascamayo.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.sebascamayo.cryptotracker.crypto.domain.Coin
import com.sebascamayo.cryptotracker.crypto.domain.CoinDataSource
import com.sebascamayo.cryptotracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {

    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {

        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets?apiKey=")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {

        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryDto> {

            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history?apiKey=")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map {
                it.toCoinPrice()
            }
        }
    }

}