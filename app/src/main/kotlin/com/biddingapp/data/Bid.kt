package com.biddingapp.data

import java.time.LocalDateTime

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
    val seat: Int
)

/**
 * Repository providing sample bid data
 */
object BidRepository {
    
    fun getSampleBids(): List<Bid> = listOf(
        Bid(
            id = "1",
            dateTime = LocalDateTime.of(2024, 12, 24, 12, 0),
            matchName = "SPL 24/25 - Al Nasar vs Al Hilal",
            category = 2,
            sectionType = "Center edges",
            priceAmount = 500,
            priceCurrency = "SAR",
            gate = 37,
            block = "A4",
            stair = 52,
            row = 14,
            seat = 23
        ),
        Bid(
            id = "2",
            dateTime = LocalDateTime.of(2024, 12, 24, 12, 0),
            matchName = "SPL 24/25 - Al Nasar vs Al Hilal",
            category = 2,
            sectionType = "Center edges",
            priceAmount = 500,
            priceCurrency = "SAR",
            gate = 37,
            block = "A4",
            stair = 52,
            row = 14,
            seat = 23
        ),
        Bid(
            id = "3",
            dateTime = LocalDateTime.of(2024, 12, 24, 12, 0),
            matchName = "SPL 24/25 - Al Nasar vs Al Hilal",
            category = 2,
            sectionType = "Center edges",
            priceAmount = 500,
            priceCurrency = "SAR",
            gate = 37,
            block = "A4",
            stair = 52,
            row = 14,
            seat = 23
        ),
        Bid(
            id = "4",
            dateTime = LocalDateTime.of(2024, 12, 24, 12, 0),
            matchName = "SPL 24/25 - Al Nasar vs Al Hilal",
            category = 3,
            sectionType = "Corner section",
            priceAmount = 350,
            priceCurrency = "SAR",
            gate = 15,
            block = "B2",
            stair = 28,
            row = 8,
            seat = 15
        )
    )
}
