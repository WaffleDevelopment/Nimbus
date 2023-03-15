package com.panav.nimbus.ticket

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.components.ActionRow
import net.dv8tion.jda.api.interactions.components.text.TextInput
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle
import net.dv8tion.jda.api.interactions.modals.Modal

class ButtonListener : ListenerAdapter() {

    override fun onButtonInteraction(event: ButtonInteractionEvent) {

        if (event.button.id.equals("CreateTicket")){
            val ign = TextInput.create("ign", "In-game name", TextInputStyle.SHORT)
                .setPlaceholder("Your in-game name")
                .setRequiredRange(3, 16)

            val concern = TextInput.create("issue", "Concern/Issue", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Concern/Issue/Questions")

            val modal = Modal.create("TicketPrompt", "Nimbus-Tickets")
                .addComponents(ActionRow.of(ign.build()), ActionRow.of(concern.build()))

            event.replyModal(modal.build()).submit(true)
        }
    }

}