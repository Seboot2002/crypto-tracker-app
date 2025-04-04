package com.sebascamayo.cryptotracker.core.data.networking

import com.sebascamayo.cryptotracker.core.domain.utils.NetworkError
import io.ktor.client.statement.HttpResponse
import java.nio.channels.UnresolvedAddressException
import com.sebascamayo.cryptotracker.core.domain.utils.Result
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, NetworkError> {

    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}