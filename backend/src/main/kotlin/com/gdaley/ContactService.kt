package com.gdaley

import kotlinx.coroutines.Deferred
import pl.treksoft.kvision.remote.Request
import pl.treksoft.kvision.remote.async

actual class ContactService : IContactService {
    override fun getContactList(req: Request?): Deferred<List<Contact>> = req.async {
        val addressRepository = req!!.require(ContactRepository::class.java)
        addressRepository.getContacts()
    }

    override fun saveContact(contact: Contact, req: Request?): Deferred<Contact> = req.async {
        val addressRepository = req!!.require(ContactRepository::class.java)
        addressRepository.save(contact)
    }

    override fun deleteContact(id: String, req: Request?): Deferred<Boolean> = req.async {
        val addressRepository = req!!.require(ContactRepository::class.java)
        addressRepository.delete(id)
        true
    }
}