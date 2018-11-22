package com.gdaley

import org.bson.types.ObjectId
import org.mongodb.morphia.Datastore
import javax.inject.Inject

class AddressRepository @Inject constructor(private val datastore: Datastore) {

    fun findAddressesByName(name: String): List<Address> {
        return datastore.createQuery(Address::class.java)
            .field("name")
            .contains(name)
            .asList()
    }

    fun save(address: Address): Address {
        datastore.save(address)
        return address
    }

    fun delete(id: String) {
        val query = datastore.createQuery(Address::class.java)
            .field("id").equal(ObjectId(id))
        datastore.findAndDelete(query)
    }

}