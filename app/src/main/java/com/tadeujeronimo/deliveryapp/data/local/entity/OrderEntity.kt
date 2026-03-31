package com.tadeujeronimo.deliveryapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val totalValueOrder: String,
    val date: LocalDateTime = LocalDateTime.now(),
)
