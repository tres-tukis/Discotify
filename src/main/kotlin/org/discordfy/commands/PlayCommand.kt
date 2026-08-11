package org.discordfy.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import org.discordfy.audio.MusicService

object PlayCommand {

    fun execute(event: SlashCommandInteractionEvent){

        val query = event.getOption("query")?.asString

        if (query == null){
            event.reply("❌ Debes indicar una canción.")
                .setEphemeral(true)
                .queue()
            return
        }

        MusicService.loadAndPlay(
            event.guild!!,
            query
        )

        event.reply("🎵 Cargando: **$query**")
            .queue()

    }
}