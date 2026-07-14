package org.discordfy.listeners

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.discordfy.commands.PingCommand

class CommandListener : ListenerAdapter() {

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {

        when (event.name) {
            "ping" -> {

                PingCommand().execute(event)

            }
        }

    }
}