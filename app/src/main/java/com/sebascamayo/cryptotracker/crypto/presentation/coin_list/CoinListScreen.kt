package com.sebascamayo.cryptotracker.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.sebascamayo.cryptotracker.crypto.presentation.coin_list.componentes.CoinListItem
import com.sebascamayo.cryptotracker.crypto.presentation.coin_list.componentes.previewCoin
import com.sebascamayo.cryptotracker.ui.theme.CryptoTrackerTheme

@Composable
fun CoinListScreen(
    state: CoinListState,
    onAction: (CoinListAction) -> Unit,
    modifier: Modifier = Modifier
){
    println("State coins: ${state.coins}")
    if (state.isLoading){
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins){ coinUi ->
                CoinListItem(
                    coinUi = coinUi,
                    onClick = {
                        onAction(CoinListAction.OnCoinClick(coinUi))
                    },
                    modifier = modifier.fillMaxWidth()
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun CoinListScreenPreview(){
    CryptoTrackerTheme {
        CoinListScreen(
            state = CoinListState(
                coins = (1..100).map{
                    previewCoin.copy(id = it.toString())
                },
                isLoading = false
            ),
            onAction = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}
