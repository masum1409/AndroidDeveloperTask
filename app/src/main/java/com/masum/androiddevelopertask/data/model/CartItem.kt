package com.masum.androiddevelopertask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cart")
data class CartItem(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    val title: String,
    val quantity: Int,
    val pricePerItem: Double,
)