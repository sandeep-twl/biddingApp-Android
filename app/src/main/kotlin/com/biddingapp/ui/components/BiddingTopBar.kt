package com.biddingapp.ui.components
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.*
import com.biddingapp.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.biddingapp.ui.theme.PrimaryBlue
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign

/**
 * A reusable TopBar for the Bidding App.
 *
 * @param title The text to display in the center of the bar.
 * @param showBackButton Whether to show the navigation back icon.
 * @param onBackClick Callback when the back icon is clicked.
 * @param rightAction The type of action icon to show on the right ("search", "download", or null).
 * @param isRightActionActive Whether the right action is currently active (toggles border alpha).
 * @param onRightActionClick Callback when the right action icon is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BiddingTopBar(
    title: String,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    rightAction: String? = null,
    isRightActionActive: Boolean = false,
    onRightActionClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            if (showBackButton) {
                Box(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.1f))
                        .border(1.dp, Color.White.copy(alpha = 0.5f), CircleShape)
                        .clickable { onBackClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        },
        actions = {
            if (rightAction != null) {
                val icon = when (rightAction.lowercase()) {
                    "search" -> painterResource(id = R.drawable.ic_search)
                    "download" -> painterResource(id = R.drawable.ic_download)
                    "share" -> painterResource(id = R.drawable.ic_share)
                    else -> null
                }
                
                if (icon != null) {
                    Box(
                        modifier = Modifier
                            .padding(end = 12.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.1f))
                            .border(
                                width = 1.dp,
                                color = Color.White.copy(alpha = if (isRightActionActive) 1f else 0.5f),
                                shape = CircleShape
                            )
                            .clickable { onRightActionClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = icon,
                            contentDescription = rightAction,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryBlue,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
    )
}
