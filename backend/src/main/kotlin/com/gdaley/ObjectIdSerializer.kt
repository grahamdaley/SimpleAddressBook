package com.gdaley

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.bson.types.ObjectId


class ObjectIdSerializer : JsonSerializer<ObjectId>() {

    override fun serialize(value: ObjectId, jgen: JsonGenerator, provider: SerializerProvider) {
        jgen.writeString(value.toString())
    }
}
