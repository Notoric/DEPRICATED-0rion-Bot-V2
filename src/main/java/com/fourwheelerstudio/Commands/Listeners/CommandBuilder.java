package com.fourwheelerstudio.Commands.Listeners;

import java.util.logging.Logger;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;

public class CommandBuilder {
    /**
     * this function builds the user's view of the bots commands and explanations
     * it loops through each guild and adds the commands to them one by one
     * @param Orion the bot's JDA object
     */
    public static void BuildCommands(JDA Orion) {
        Logger logger = Logger.getLogger("orion");

        for (Guild gld : Orion.getGuilds()) {
            logger.info("Adding commands for Guild: \"" + gld.getName() + "\" with ID: [" + gld.getId() + "]");

            gld.upsertCommand("join", "joins the voice channel").queue();
            logger.info("Added command /join.");

            gld.upsertCommand("leave", "leaves the voice channel").queue();
            logger.info("Added command /leave.");

            gld.upsertCommand("destroy", "leaves the voice channel").queue();
            logger.info("Added command /destroy.");

            gld.upsertCommand("stop", "leaves the voice channel").queue();
            logger.info("Added command /stop.");

            gld.upsertCommand("play", "add a song to the queue")
                .addOption(OptionType.STRING, "song", "The song you want to play, either a search query or a URL", true)
                .addOption(OptionType.STRING, "platform", "The platform to search, NOT REQUIRED if you provide a URL", false).queue();
            logger.info("Added command /play.");

            gld.upsertCommand("queue", "add a song to the queue")
                .addOption(OptionType.STRING, "song", "The song you want to play, either a search query or a URL", true)
                .addOption(OptionType.STRING, "platform", "The platform to search, NOT REQUIRED if you provide a URL", false).queue();
            logger.info("Added command /queue.");

            gld.upsertCommand("website", "you can find our website here");
            logger.info("Added command /website.");
        }
    }
}