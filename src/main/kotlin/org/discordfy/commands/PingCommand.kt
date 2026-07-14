package org.discordfy.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent

class PingCommand {

    fun execute(event: SlashCommandInteractionEvent) {

        event.reply("🏓 Pong!").queue()

    }

}