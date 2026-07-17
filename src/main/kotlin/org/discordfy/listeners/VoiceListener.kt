package org.discordfy.listeners

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class VoiceListener : ListenerAdapter() {

    override fun onGuildVoiceUpdate(event: GuildVoiceUpdateEvent) {

        println("===== EVENTO DE VOZ =====")
        println("Usuario: ${event.entity.effectiveName}")

        if (event.channelJoined != null) {
            println("ENTRÓ A: ${event.channelJoined?.name}")
        }

        if (event.channelLeft != null) {
            println("SALIO DE: ${event.channelLeft?.name}")
        }

        println("=========================")

    }

}