package com.gdaley

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import pl.treksoft.kvision.data.DataContainer.Companion.dataContainer
import pl.treksoft.kvision.form.FormPanel
import pl.treksoft.kvision.form.FormPanel.Companion.formPanel
import pl.treksoft.kvision.form.text.Text
import pl.treksoft.kvision.form.text.TextInputType
import pl.treksoft.kvision.html.Button
import pl.treksoft.kvision.html.Button.Companion.button
import pl.treksoft.kvision.html.Icon.Companion.icon
import pl.treksoft.kvision.modal.Confirm
import pl.treksoft.kvision.modal.Modal
import pl.treksoft.kvision.panel.VPanel
import pl.treksoft.kvision.table.Cell.Companion.cell
import pl.treksoft.kvision.table.HeaderCell
import pl.treksoft.kvision.table.Row
import pl.treksoft.kvision.table.Table
import pl.treksoft.kvision.table.TableType
import pl.treksoft.kvision.utils.px

object MainPanel : VPanel() {

    private lateinit var addForm: FormPanel<Contact>
    private val modal: Modal
    private var editingIndex = -1

    init {
        padding = 10.px

        val table = Table(types = setOf(TableType.STRIPED, TableType.HOVER)) {
            addHeaderCell(HeaderCell("Name"))
            addHeaderCell(HeaderCell("Email"))
            addHeaderCell(HeaderCell(""))
        }

        dataContainer(
            Model.contacts, { index, address ->
                Row {
                    cell(address.name)
                    cell(address.email)
                    cell {
                        icon("fa-times") {
                            title = "Delete"
                            setEventListener {
                                click = { e ->
                                    e.stopPropagation()
                                    Confirm.show("Are you sure?", "Do you want to delete this address?") {
                                        delete(index)
                                    }
                                }
                            }
                        }
                    }
                    setEventListener {
                        click = {
                            edit(index)
                        }
                    }
                }
            }, container = table
        )

        modal = Modal("Add/Edit Contact")
        modal.add(VPanel {
            addForm = formPanel {
                add(Contact::name, Text(label = "Name:").apply { maxlength = 255 })
                add(Contact::email,
                    Text(TextInputType.EMAIL, label = "Email:").apply { maxlength = 255 }) {
                    it.getValue()
                        ?.matches("[\\w-]+@([\\w-]+\\.)+[\\w-]+")
                }
            }

        })
        modal.addButton(Button("Cancel").onClick {
            modal.hide()
        })
        modal.addButton(Button("OK").onClick {
            save()
        })

        button(text = "Add Contact").onClick {
            newAddress()
        }

    }

    private fun newAddress() {
        addForm.setData(Contact())
        editingIndex = -1
        modal.show()
    }

    private fun save() {
        if (addForm.validate()) {
            val id = if (editingIndex >= 0) Model.contacts[editingIndex].id else null
            val address = addForm.getData().copy(id = id)
            GlobalScope.launch {
                Model.saveContact(address)
            }
            modal.hide()
        }
    }

    private fun edit(index: Int) {
        val address = Model.contacts[index]
        addForm.setData(address)
        editingIndex = index
        modal.show()
    }

    private fun delete(index: Int) {
        GlobalScope.launch {
            Model.contacts[index].id?.let {
                Model.deleteContact(it)
            }
        }
    }

}
