package com.biddingapp.data

import java.time.LocalDateTime

/**
 * Data class representing a bid for a sports event ticket
 */
data class Bidder(
    val id: String,
    val name: String,
    val avatarResId: Int? = null,
    val bidTime: String,
    val bidAmount: Int
)

/**
 * Data class representing a bid for a sports event ticket
 */
data class Bid(
    val id: String,
    val dateTime: LocalDateTime,
    val matchName: String,
    val category: Int,
    val sectionType: String,
    val priceAmount: Int,
    val priceCurrency: String,
    val gate: Int,
    val block: String,
    val stair: Int,
    val row: Int,
    val seat: Int,
    val posterName: String = "Jacob Russell",
    val posterAvatarResId: Int? = null,
    val timeLeft: String = "23:56:09",
    val latestBid: Int = 1500,
    val initialBid: Int = 1000,
    val biddersCount: Int = 10,
    val bidders: List<Bidder> = emptyList()
)

/**
 * Repository providing sample bid data
 */
object BidRepository {
    
    fun getSampleBidders(): List<Bidder> = listOf(
        Bidder("1", "Cody Fisher", com.biddingapp.R.drawable.user_image_1, "4 hrs ago", 1500),
        Bidder("2", "Jacob Jones", com.biddingapp.R.drawable.user_image_2, "5 hrs ago", 1300),
        Bidder("3", "Dianne Russell", com.biddingapp.R.drawable.user_image_3, "6 hrs ago", 1150),
        Bidder("4", "Ralph Edwards", com.biddingapp.R.drawable.user_image_4, "7 hrs ago", 1100),
        Bidder("5", "Kathryn Murphy", com.biddingapp.R.drawable.user_image_5, "8 hrs ago", 1000)
    )

    fun getSampleBids(): List<Bid> = listOf(
        Bid(
            id = "1",
            dateTime = LocalDateTime.of(2024, 12, 24, 12, 0),
            matchName = "SPL 24/25 - Al Nassr vs Al Hilal",
            category = 2,
            sectionType = "Center edges",
            priceAmount = 500,
            priceCurrency = "SAR",
            gate = 37,
            block = "A4",
            stair = 52,
            row = 14,
            seat = 23,
            posterAvatarResId = com.biddingapp.R.drawable.user_image_7,
            bidders = getSampleBidders()
        ),
        Bid(
            id = "2",
            dateTime = LocalDateTime.of(2024, 12, 24, 12, 0),
            matchName = "SPL 24/25 - Al Nassr vs Al Hilal",
            category = 2,
            sectionType = "Center edges",
            priceAmount = 500,
            priceCurrency = "SAR",
            gate = 37,
            block = "A4",
            stair = 52,
            row = 14,
            seat = 23,
            posterAvatarResId = com.biddingapp.R.drawable.user_image_6
        ),
        Bid(
            id = "3",
            dateTime = LocalDateTime.of(2024, 12, 24, 12, 0),
            matchName = "SPL 24/25 - Al Nassr vs Al Hilal",
            category = 2,
            sectionType = "Center edges",
            priceAmount = 500,
            priceCurrency = "SAR",
            gate = 37,
            block = "A4",
            stair = 52,
            row = 14,
            seat = 23,
            posterAvatarResId = com.biddingapp.R.drawable.user_image_1
        ),
        Bid(
            id = "4",
            dateTime = LocalDateTime.of(2024, 12, 24, 12, 0),
            matchName = "SPL 24/25 - Al Nassr vs Al Hilal",
            category = 3,
            sectionType = "Corner section",
            priceAmount = 350,
            priceCurrency = "SAR",
            gate = 15,
            block = "B2",
            stair = 28,
            row = 8,
            seat = 15,
            posterAvatarResId = com.biddingapp.R.drawable.user_image_2
        )
    )
}
