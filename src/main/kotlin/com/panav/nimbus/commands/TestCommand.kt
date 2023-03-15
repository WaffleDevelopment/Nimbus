package com.panav.nimbus.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class TestCommand : ListenerAdapter() {

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {

        if (event.name == "testcmd") {
            event.reply("I will send you to jesus").queue()
        }

    }
}