package com.gdaley

import kotlinx.serialization.Serializable

@Serializable
actual data class Address(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null
)
