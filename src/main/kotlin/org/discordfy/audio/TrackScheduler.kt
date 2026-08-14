package org.discordfy.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason
import java.util.concurrent.LinkedBlockingQueue
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException

class TrackScheduler(
    private val player: com.sedmelluq.discord.lavaplayer.player.AudioPlayer
) : AudioEventAdapter() {

    private val queue = LinkedBlockingQueue<AudioTrack>()

    private val fallbackQueue = LinkedBlockingQueue<AudioTrack>()

    private var handlingFallback = false


    fun queueTrack(track: AudioTrack) {

        println("🎵 Track recibido: ${track.info.title}")

        val started = player.startTrack(track,true)


        println("▶️ startTrack devolvió: $started")
        println(
            "🎧 Track actual: ${player.playingTrack?.info?.title}"
        )


        if (!started) {
            queue.offer(track)
        }

    }


    fun queueSearchResults(tracks: List<AudioTrack>) {
        if (tracks.isEmpty()) {
            println("❌ No hay resultados para reproducir")
            return
        }

        fallbackQueue.clear()

        val firstTrack = tracks.first()

        tracks.drop(1).forEach {
            fallbackQueue.offer(it)
        }

        println("🎯 Resultado principal: ${firstTrack.info.title}")
        println("🔧 Resultados de fallback disponibles: ${fallbackQueue.size}")

        queueTrack(firstTrack)
    }


    fun nextTrack() {

        player.startTrack(queue.poll(), false)

    }


    private fun tryNextFallback(): Boolean {

        val next = fallbackQueue.poll()

        if (next == null) {
            println("❌ No quedan resultados alternativos")
            return false
        }

        println("🔄 Intentando resultado alternativo:")
        println("🎵 ${next.info.title}")

        handlingFallback = true

        val started = player.startTrack(next,false)

        println("▶️ Fallback startTrack devolvió: $started")

        return started

        }


    override fun onTrackStart(
        player: AudioPlayer,
        track: AudioTrack
    ) {
        println("🔥 TRACK START:")
        println(track.info.title)
    }

    override fun onTrackException(
        player: AudioPlayer,
        track: AudioTrack,
        exception: FriendlyException
    ) {

        println("💥 TRACK EXCEPTION")
        println("❌ Falló: ${track.info.title}")

        exception.printStackTrace()

        if (!tryNextFallback()) {
            println("❌ No hay más resultados alternativos")
        }

    }

    override fun onTrackStuck(
        player: AudioPlayer,
        track: AudioTrack,
        thresholdMs: Long
    ) {

        println("🚨 TRACK STUCK")

    }

    override fun onTrackEnd(
        player: AudioPlayer,
        track: AudioTrack,
        endReason: AudioTrackEndReason
    ) {

        println("🏁 TRACK END:")
        println(endReason)

        if (handlingFallback) {
            handlingFallback = false
            return
        }

        if (endReason.mayStartNext) {
            nextTrack()
        }

    }

}
