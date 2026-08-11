package org.discordfy.listeners

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.discordfy.commands.PingCommand
import org.discordfy.commands.JoinCommand
import org.discordfy.commands.PlayCommand

class CommandListener : ListenerAdapter() {

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {

        when (event.name) {
            "ping" -> PingCommand().execute(event)
            "join" -> JoinCommand.execute(event)
            "play" -> PlayCommand.execute(event)


        }

    }
}