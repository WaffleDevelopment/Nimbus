package com.panav.nimbus.ticket

import net.dv8tion.jda.api.entities.Member
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class ModalListener : ListenerAdapter() {

    override fun onModalInteraction(event: ModalInteractionEvent) {
        if (event.modalId == "TicketPrompt"){
            val ign = event.getValue("ign")?.asString
            val issue = event.getValue("issue")?.asString
            val member : Member = event.member!!

            TicketManager.createTicket(member, ign!!, issue!!)

            event.reply("Thank you for your request").setEphemeral(true).submit(true)
        }
    }
}