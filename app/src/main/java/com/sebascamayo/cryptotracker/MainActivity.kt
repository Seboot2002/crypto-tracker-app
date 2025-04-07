package com.sebascamayo.cryptotracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sebascamayo.cryptotracker.core.domain.navigation.AdaptiveCoinListDetailPlane
import com.sebascamayo.cryptotracker.core.presentation.utils.ObserveAsEvents
import com.sebascamayo.cryptotracker.ui.theme.CryptoTrackerTheme
import com.sebascamayo.cryptotracker.core.presentation.utils.toString
import com.sebascamayo.cryptotracker.crypto.presentation.coin_detail.CoinDetailScreen
import com.sebascamayo.cryptotracker.crypto.presentation.coin_list.CoinListEvent
import com.sebascamayo.cryptotracker.crypto.presentation.coin_list.CoinListScreen
import com.sebascamayo.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    AdaptiveCoinListDetailPlane(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}