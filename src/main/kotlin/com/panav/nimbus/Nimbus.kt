package com.panav.nimbus

import com.panav.nimbus.commands.TestCommand
import com.panav.nimbus.ticket.AddEmbedCmd
import com.panav.nimbus.ticket.ButtonListener
import com.panav.nimbus.ticket.ModalListener
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.coroutines.*
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.Commands
import net.dv8tion.jda.api.requests.GatewayIntent
import kotlin.time.Duration.Companion.seconds

val config : Dotenv = Dotenv.configure().ignoreIfMissing().load()

private val token = config.get("TOKEN")

lateinit var activityJob: Job
lateinit var jda: JDA

class Nimbus {


    private val activities = arrayOf(
        Activity.listening("to Spotify"),
        Activity.competing("in valorant"),
        Activity.playing("with noobs")
    )

    private val builder = JDABuilder
        .createDefault(token)
        .setActivity(activities[0])
        .addEventListeners(TestCommand(), AddEmbedCmd(), ButtonListener(), ModalListener())

    init{
        jda = builder.build()
        jda.awaitReady()
        
    }



    @OptIn(DelicateCoroutinesApi::class)
    fun activityShuffle(){
        GlobalScope.launch {
            activityJob = async{
                while (true) {
                    for (activity in activities) {
                        jda.presence.activity = activity
                        delay(60.seconds.inWholeMilliseconds)
                    }
                }
            }
        }
    }


}

fun main(args: Array<String>) {
    Nimbus()
    Nimbus().activityShuffle()
    println("Main class initialised")
    registerCmds(jda)
}

fun registerCmds(jda : JDA){
    jda.getGuildById(config.get("GUILD-ID"))?.updateCommands()?.addCommands(
        Commands.slash("testcmd","This is for testing purposes")
            .addOption(OptionType.STRING, "testcmd","write your msg",false).setGuildOnly(true),

        Commands.slash("addticket", "Sends a Ticket Embed in the Channel")
            .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
            .setGuildOnly(true)

    )?.queue()

}




