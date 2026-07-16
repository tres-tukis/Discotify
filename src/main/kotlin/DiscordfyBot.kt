package org.discordfy

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.JDA
import org.discordfy.listeners.CommandListener
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.discordfy.audio.AudioPlayerManager


class DiscordfyBot {

    fun start() {
        val config = ConfigManager()

        config.load()

        val token = config.getToken()

        val jda = JDABuilder.createDefault(token)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .addEventListeners(CommandListener())
            .build()

        jda.awaitReady()

        AudioPlayerManager.initialize()

        jda.updateCommands()
            .addCommands(
                Commands.slash(
                    "ping",
                    "Comprueba si Discotify está funcionando!"
                )
            )
            .queue{
                println("Comando /ping registrado correctamente!")
            }

    }
}