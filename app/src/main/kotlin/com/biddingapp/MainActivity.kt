package com.biddingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import com.biddingapp.data.Bid
import com.biddingapp.ui.screens.BidsCardDetailsScreen
import com.biddingapp.ui.screens.BidsListScreen
import com.biddingapp.ui.theme.BackgroundLight
import com.biddingapp.ui.theme.BiddingAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BiddingAppTheme {
                var selectedBid by remember { mutableStateOf<Bid?>(null) }
                
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundLight
                ) {
                    if (selectedBid == null) {
                        BidsListScreen(
                            onBidClick = { selectedBid = it }
                        )
                    } else {
                        BidsCardDetailsScreen(
                            bid = selectedBid!!,
                            onNavigateBack = { selectedBid = null }
                        )
                    }
                }
            }
        }
    }
}
