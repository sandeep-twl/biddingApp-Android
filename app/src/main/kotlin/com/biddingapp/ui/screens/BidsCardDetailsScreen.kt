package com.biddingapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biddingapp.R
import com.biddingapp.data.Bid
import com.biddingapp.data.BidRepository
import com.biddingapp.ui.components.BidCard
import com.biddingapp.ui.components.BidderItem
import com.biddingapp.ui.components.BiddingTopBar
import com.biddingapp.ui.theme.*

@Composable
fun BidsCardDetailsScreen(
    modifier: Modifier = Modifier,
    bid: Bid,
    onNavigateBack: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            BiddingTopBar(
                title = "Bidding Details",
                showBackButton = true,
                onBackClick = onNavigateBack,
                rightAction = "download" // Changed from share to download
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent) // Ensure transparent background
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 24.dp) // Increased bottom padding from 16 to 24
            ) {
                Button(
                    onClick = { /* TODO: Place bid */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFD100), // Match yellow from screenshot
                        contentColor = TextPrimary
                    )
                ) {
                    Text(
                        text = "Place Your Bid",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        containerColor = BackgroundLight
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Main Ticket Card
            item {
                BidCard(
                    bid = bid,
                    isDetailScreen = true, // Added isDetailScreen to show Ongoing tag
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // "Posted by" section
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape) // Changed from RoundedCornerShape(12.dp) to CircleShape
                            .background(Color.LightGray.copy(alpha = 0.3f))
                    ) {
                        Image(
                            painter = painterResource(id = bid.posterAvatarResId ?: R.drawable.ic_launcher_background),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = "Posted by",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                        Text(
                            text = bid.posterName,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }
                }
            }

            // Auction Summary Section
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                ) {
                    // Main summary container
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        shape = RoundedCornerShape(12.dp),
//                        color = Color(0xFFF1F5F9), // Very light gray-blue
                        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SummaryColumn("Starting Bid", "SAR ${bid.initialBid}")
                            VerticalDivider()
                            SummaryColumn("Latest Bid", "SAR ${bid.latestBid}")
                            VerticalDivider()
                            SummaryColumn("Bidders", bid.biddersCount.toString())
                        }
                    }

                    // "Time Left" pill
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopCenter),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(1.dp, Color(0xFFE2E8F0))
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Time Left ",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextSecondary,
                                fontSize = 11.sp
                            )
                            Text(
                                text = bid.timeLeft,
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Black, // Changed from Red to Black
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }

            // Bidders List Section
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    color = LightBlueBackground // Added = background for the bidders section
                ) {
                    Column {
                        Text(
                            text = "Bidders",
                            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        
                        bid.bidders.forEach { bidder ->
                            BidderItem(
                                bidder = bidder,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun SummaryColumn(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            fontSize = 12.sp
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            fontSize = 15.sp
        )
    }
}

@Composable
private fun VerticalDivider() {
    Box(
        modifier = Modifier
            .height(30.dp)
            .width(1.dp)
            .background(Color(0xFFCBD5E1))
    )
}

@Preview(showBackground = true)
@Composable
fun BidsCardDetailsPreview() {
    BiddingAppTheme {
        BidsCardDetailsScreen(
            bid = BidRepository.getSampleBids().first()
        )
    }
}
