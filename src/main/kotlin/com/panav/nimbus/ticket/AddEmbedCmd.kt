package com.panav.nimbus.ticket

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.entities.emoji.Emoji
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.components.buttons.Button
import java.awt.Color


class AddEmbedCmd : ListenerAdapter() {

    /*
        This command is used for sending the create ticket embed in a channel
        for members to click on a button and create the ticket
     */

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name == "addticket"){

                val embed : EmbedBuilder = EmbedBuilder().apply {
                    setTitle("Support")
                    appendDescription("To create a ticket react with :envelope_with_arrow:")
                    setFooter(
                        "Nimbus works",
                        "https://cdn.discordapp.com/attachments/1057062515781144588/1085254847852462140/Nimbus.png"
                    )
                    setColor(Color.CYAN)
                    setThumbnail("https://cdn.discordapp.com/attachments/1085262220428071003/1085265928989712504/ticket-icon.png")
                }

                event.replyEmbeds(embed.build())
                    .addActionRow(
                        Button.primary("CreateTicket", Emoji.fromFormatted("<:envelopewitharrow:1085272691352801392>"))
                            .withLabel("Create a Ticket")
                    ).submit()
            }

        }
    }

