package org.discordfy.audio

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.entities.Guild

object TrackLoader {

    fun loadTrack(
        musicManager: GuildMusicManager,
        guild: Guild,
        query: String,
    ) {

        println("🔎 TrackLoader buscando: $query")

        guild.audioManager.sendingHandler =
            musicManager.sendHandler

        AudioPlayerManager
            .getPlayerManager()
            .loadItemOrdered(
                musicManager,
                query,
                object : AudioLoadResultHandler {

                    override fun trackLoaded(track: AudioTrack) {

                        println("🎵 TRACK CARGADO: ${track.info.title}")

                        musicManager.scheduler.queueTrack(track)

                    }

                    override fun playlistLoaded(playlist: AudioPlaylist) {

                        println("📀 PLAYLIST CARGADA: ${playlist.name}")

                        playlist.tracks.forEach {

                            println("🎵 Añadiendo: ${it.info.title}")

                            musicManager.scheduler.queueTrack(it)

                        }

                    }

                    override fun noMatches() {

                        println("❌ No se encontró ninguna canción")

                    }

                    override fun loadFailed(exception: FriendlyException) {

                        println("💀 Error cargando canción:")

                        exception.printStackTrace()

                    }

                }
            )

    }

}