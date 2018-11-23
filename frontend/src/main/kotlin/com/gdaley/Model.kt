package com.gdaley

import com.lightningkite.kotlin.observable.list.ObservableList
import com.lightningkite.kotlin.observable.list.observableListOf
import pl.treksoft.kvision.utils.syncWithList

object Model {

    private val contactService = ContactService()
    val contacts: ObservableList<Contact> = observableListOf()

    suspend fun saveContact(contact: Contact) {
        contactService.saveContact(contact).await()
        getContactList()
    }

    suspend fun deleteContact(id: String) {
        contactService.deleteContact(id).await()
        getContactList()
    }

    suspend fun getContactList() {
        val newContacts = contactService.getContactList().await()
        contacts.syncWithList(newContacts)
    }

}