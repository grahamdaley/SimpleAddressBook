package com.gdaley

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import kotlinx.serialization.Serializable
import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id

@Entity
@Serializable
actual data class Contact(
    @Id
    @JsonSerialize(using = ObjectIdSerializer::class)
    val id: ObjectId? = null,

    val name: String? = null,
    val email: String? = null
)
