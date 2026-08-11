package org.discordfy.commands

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import org.discordfy.audio.MusicService


object JoinCommand {

    fun execute(event: SlashCommandInteractionEvent) {

        val member = event.member
        val voiceState = member?.voiceState

        if (voiceState == null || !voiceState.inAudioChannel()){

            event.reply("❌ Debes estar en un canal de voz.")
                .setEphemeral(true)
                .queue()

            return

        }

        val channel = voiceState.channel ?: return

        val audioManager = event.guild!!.audioManager

        val musicManager =
            MusicService.getGuildMusicManager(event.guild!!)

        event.reply("🎧 Me he unido al canal **${channel.name}**.")
            .queue()

        audioManager.sendingHandler = musicManager.sendHandler

        println("intentando conectar a canal ${channel.name}")

        audioManager.openAudioConnection(channel)

        Thread.sleep(2000)

        Thread {
            Thread.sleep(5000)

            println("Estado:")
            println(audioManager.connectionStatus)
        }.start()

        println("solicitud de conexion enviada")


    }
}