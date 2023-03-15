package com.panav.nimbus.ticket

import com.panav.nimbus.config
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.entities.Member
import java.awt.Color

object TicketManager {

     fun createTicket(member: Member, name : String, issue : String) {
        val guild = member.guild
        val category = guild.getCategoryById(config.get("TICKET-CATEGORY"))!!
        val role = guild.getRoleById(config.get("Support-Role"))

        val action = guild.createTextChannel("ticket-${member.effectiveName}", category).apply {
            setTopic("Ticket-${member.effectiveName}")

            addPermissionOverride(
                member,
                listOf(
                    Permission.MESSAGE_HISTORY,
                    Permission.MESSAGE_SEND,
                    Permission.MESSAGE_ATTACH_FILES,
                    Permission.MESSAGE_EMBED_LINKS,
                    Permission.MESSAGE_ADD_REACTION,
                    Permission.MESSAGE_EXT_STICKER,
                    Permission.MESSAGE_EXT_EMOJI
                ),
                emptyList()
            )

            addPermissionOverride(
                guild.publicRole, emptyList(),
                listOf(Permission.MESSAGE_HISTORY, Permission.MESSAGE_SEND)
            )
            addPermissionOverride(
                guild.getMember(guild.jda.selfUser)!!,
                listOf(Permission.MESSAGE_SEND, Permission.MESSAGE_HISTORY),
                emptyList()
            )

            if (role != null) {
                addPermissionOverride(
                    role,
                    listOf(
                        Permission.MESSAGE_SEND,
                        Permission.MESSAGE_HISTORY,
                        Permission.MESSAGE_ATTACH_FILES,
                        Permission.MESSAGE_EMBED_LINKS
                    ),
                    emptyList()
                )
            }
        }.submit()

        val embed = EmbedBuilder().apply {
            setFooter("Please wait Patiently for our Staff Members to respond", "https://cdn.discordapp.com/attachments/1057062515781144588/1085254847852462140/Nimbus.png")
            addField("IGN:", name, true)
            addField("Issue: ", issue, true)
            setAuthor(member.effectiveName, "https://youtu.be/dQw4w9WgXcQ", member.effectiveAvatarUrl)
            setColor(Color.WHITE)
            setThumbnail("https://cdn.discordapp.com/attachments/1057062515781144588/1085254847852462140/Nimbus.png")
        }

         action.get().sendMessageEmbeds(embed.build()).queue()

    }
}