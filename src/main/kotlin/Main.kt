package org.discordfy

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    System.setProperty(
        "org.slf4j.simpleLogger.log.dev.lavalink.youtube",
        "trace"
    )

    val bot = DiscordfyBot()
    bot.start()

}