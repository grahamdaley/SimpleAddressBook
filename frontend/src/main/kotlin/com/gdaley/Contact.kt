package com.gdaley

import kotlinx.serialization.Serializable

@Serializable
actual data class Contact(
    val id: String? = null,
    val name: String? = null,
    val email: String? = null
)
