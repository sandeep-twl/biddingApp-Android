package com.biddingapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biddingapp.R
import com.biddingapp.data.Bid
import com.biddingapp.ui.theme.*
import java.time.format.DateTimeFormatter

@Composable
fun BidCard(
    bid: Bid,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = SurfaceWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Date/Time header
            Text(
                text = bid.dateTime.format(DateTimeFormatter.ofPattern("dd MMMM, hh:mm a")),
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Match info row
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Team logo placeholder
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(PrimaryBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "N",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Text(
                    text = bid.matchName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = PrimaryBlue
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Category and section info
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Category badge
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = CategoryOrange
                ) {
                    Text(
                        text = "CAT${bid.category}",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(modifier = Modifier.width(12.dp))
                
                Column {
                    Text(
                        text = "Cat ${bid.category} - ${bid.sectionType}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary
                    )
                    Text(
                        text = "${bid.priceAmount} ${bid.priceCurrency}",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Seating details table
            SeatingDetailsRow(bid)
        }
    }
}

@Composable
private fun SeatingDetailsRow(bid: Bid) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SeatingColumn(label = "Gate", value = bid.gate.toString())
        SeatingColumn(label = "Block", value = bid.block)
        SeatingColumn(label = "Stair", value = bid.stair.toString())
        SeatingColumn(label = "Row", value = bid.row.toString())
        SeatingColumn(label = "Seat", value = bid.seat.toString())
    }
}

@Composable
private fun SeatingColumn(
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary
        )
    }
}
