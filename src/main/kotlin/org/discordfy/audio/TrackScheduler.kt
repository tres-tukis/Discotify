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

    fun nextTrack() {

        player.startTrack(queue.poll(), false)

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

        exception.printStackTrace()

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


        if (endReason.mayStartNext) {
            nextTrack()
        }

    }

}
