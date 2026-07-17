package org.discordfy

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.JDA
import org.discordfy.listeners.CommandListener
import net.dv8tion.jda.api.interactions.commands.build.Commands
import org.discordfy.audio.AudioPlayerManager
import org.discordfy.listeners.VoiceListener
import net.dv8tion.jda.api.utils.cache.CacheFlag
import moe.kyokobot.libdave.NativeDaveFactory
import moe.kyokobot.libdave.jda.LDJDADaveSessionFactory
import net.dv8tion.jda.api.audio.AudioModuleConfig


class DiscordfyBot {

    fun start() {
        val config = ConfigManager()

        config.load()

        val token = config.getToken()


        val builder = JDABuilder.createDefault(token)
            .enableIntents(
                GatewayIntent.MESSAGE_CONTENT,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES
            )
            .enableCache(CacheFlag.VOICE_STATE)
            .addEventListeners(
                CommandListener(),
                VoiceListener()
            )
        val daveFactory = NativeDaveFactory()

        val daveSessionFactory =
            LDJDADaveSessionFactory(daveFactory)

        builder.setAudioModuleConfig(
            AudioModuleConfig()
                .withDaveSessionFactory(daveSessionFactory)
        )

        val jda = builder.build()

        jda.awaitReady()

        AudioPlayerManager.initialize()

        jda.updateCommands()
            .addCommands(
                Commands.slash(
                    "ping",
                    "Comprueba si Discotify está funcionando!"
                ),

                Commands.slash(
                    "join",
                    "Hace que Discotify entre al canal de voz."
                )

            )
            .queue{

                println("Discotify está conectado a Discord!")
            }

    }
}