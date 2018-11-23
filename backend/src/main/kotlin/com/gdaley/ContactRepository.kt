package com.gdaley

import org.bson.types.ObjectId
import org.mongodb.morphia.Datastore
import javax.inject.Inject

class ContactRepository @Inject constructor(private val datastore: Datastore) {

    fun getContacts(): List<Contact> {
        return datastore.createQuery(Contact::class.java).asList()
    }

    fun save(contact: Contact): Contact {
        datastore.save(contact)
        return contact
    }

    fun delete(id: String) {
        val query = datastore.createQuery(Contact::class.java)
            .field("id").equal(ObjectId(id))
        datastore.findAndDelete(query)
    }

}