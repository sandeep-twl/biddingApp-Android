package com.biddingapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.biddingapp.R
import com.biddingapp.data.BidRepository
import com.biddingapp.ui.components.BiddingTopBar
import com.biddingapp.ui.components.BidCard
import com.biddingapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun BidsListScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit = {},
) {
    val bids = remember { BidRepository.getSampleBids() }
    var isSearchActive by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    
    Scaffold(
        topBar = {
            BiddingTopBar(
                title = "All Biddings",
                showBackButton = true,
                onBackClick = onNavigateBack,
                rightAction = "search",
                isRightActionActive = isSearchActive,
                onRightActionClick = { isSearchActive = !isSearchActive }
            )
        },
        containerColor = BackgroundLight
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { focusManager.clearFocus() }
        ) {
            // Search Bar (Conditional)
            if (isSearchActive) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    placeholder = {
                        Text(
                            text = "Search ticket...",
                            color = TextSecondary.copy(alpha = 0.6f)
                        )
                    },
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color.LightGray.copy(alpha = 0.3f),
                        unfocusedBorderColor = Color.LightGray.copy(alpha = 0.3f),
                        cursorColor = PrimaryBlue
                    ),
                    singleLine = true
                )
            }

            // "Bids posted" header with filter icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Bids posted",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                
                IconButton(
                    onClick = { /* TODO: Filter */ },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        painter=painterResource(id = R.drawable.ic_filter),
                        contentDescription ="Filter",
                        tint = Color.Unspecified
                    )
                }
            }
            
            // Bids list
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(
                    items = bids,
                    key = { it.id }
                ) { bid ->
                    BidCard(bid = bid)
                }
            }
        }
    }
}
