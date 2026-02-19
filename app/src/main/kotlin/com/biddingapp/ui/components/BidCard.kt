package com.biddingapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biddingapp.data.Bid
import com.biddingapp.ui.theme.*
import java.time.format.DateTimeFormatter

@Composable
fun BidCard(
    bid: Bid,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .graphicsLayer(
                shadowElevation = 8f,
                shape = RoundedCornerShape(16.dp),
                clip = false,
                ambientShadowColor = Color.Black.copy(alpha = 0.15f),
                spotShadowColor = Color.Black.copy(alpha = 0.15f)
            )
    ) {
        // SVG Ticket Background
        Image(
            painter = painterResource(id = R.drawable.ic_bg_bid_card),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )

        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 18.dp)
        ) {
            // Top Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.al_nazar_logo),
                        contentDescription = "Al Nassar",
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_al_hilal),
                        contentDescription = "Al Hilal",
                        modifier = Modifier.size(30.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = bid.dateTime.format(
                            DateTimeFormatter.ofPattern("dd MMMM, hh:mm a")
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary,
                        maxLines = 1
                    )

                    Text(
                        text = bid.matchName,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.ExtraBold, // More emphasis
                        color = TextPrimary,
                        maxLines = 2
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // High Performance Cached Dashed Separator
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .drawWithCache {
                        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                        onDrawBehind {
                            drawLine(
                                color = DividerColor.copy(alpha = 0.3f),
                                start = Offset(0f, 0.5f),
                                end = Offset(size.width, 0.5f),
                                pathEffect = pathEffect,
                                strokeWidth = 1.dp.toPx()
                            )
                        }
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Middle Section
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Surface(
                    shape = RoundedCornerShape(5.dp),
                    color = CategoryOrange,
                ) {
                    Text(
                        text = "CAT${bid.category}",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Cat ${bid.category} - ${bid.sectionType}",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        maxLines = 1
                    )

                    Text(
                        text = "${bid.priceAmount} ${bid.priceCurrency}",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            // Bottom Section (Seating) - Transparent as per latest request
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, DividerColor.copy(alpha = 0.8f)),
                color = Color.Transparent
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SeatingColumn("Gate", bid.gate.toString())
                    VerticalDivider()
                    SeatingColumn("Block", bid.block)
                    VerticalDivider()
                    SeatingColumn("Stair", bid.stair.toString())
                    VerticalDivider()
                    SeatingColumn("Row", bid.row.toString())
                    VerticalDivider()
                    SeatingColumn("Seat", bid.seat.toString())
                }
            }
        }
    }
}

@Composable
private fun VerticalDivider() {
    Box(
        modifier = Modifier
            .height(30.dp)
            .width(1.dp)
            .background(DividerColor.copy(alpha = 0.5f))
    )
}

@Composable
private fun SeatingColumn(
    label: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy((-2).dp)
    ) {
        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            style = LocalTextStyle.current.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )

        Text(
            text = label,
            fontSize = 13.sp,
            color = TextSecondary,
            style = LocalTextStyle.current.copy(
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            )
        )
    }
}
