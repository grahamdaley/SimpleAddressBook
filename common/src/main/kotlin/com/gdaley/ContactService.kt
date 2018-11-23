package com.gdaley

import kotlinx.coroutines.Deferred
import pl.treksoft.kvision.remote.JoobyServiceManager
import pl.treksoft.kvision.remote.Request

interface IContactService {
    fun getContactList(req: Request? = null): Deferred<List<Contact>>
    fun saveContact(contact: Contact, req: Request? = null): Deferred<Contact>
    fun deleteContact(id: String, req: Request? = null): Deferred<Boolean>
}

expect class ContactService() : IContactService

object ContactServiceManager : JoobyServiceManager<ContactService>(ContactService()) {
    init {
        bind(ContactService::getContactList)
        bind(ContactService::saveContact)
        bind(ContactService::deleteContact)
    }
}
