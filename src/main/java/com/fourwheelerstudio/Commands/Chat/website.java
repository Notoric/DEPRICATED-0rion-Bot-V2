package com.fourwheelerstudio.Commands.Chat;

import java.awt.Color;
import java.util.logging.Logger;

import com.fourwheelerstudio.Commands.Libraries.Response;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;


public class website {
    /**
     * This command will responds with our website link
     * @param event the slash command event
     */
    public static void websiteCommand(SlashCommandInteractionEvent event) {
        Logger logger = Logger.getLogger("orion");
        event.deferReply().queue();

        Response.genericResponseImage(event, "https://hotemoji.com/images/dl/4/globe-with-meridians-emoji-by-twitter.png", "Website Link", "You can find our website [here](https://www.reddit.com/r/blurrypicturesofcats/comments/jnbwgo/blurry_picture_of_a_cat/)", new Color(255, 85, 0));
        logger.info("@" + event.getGuild().getName() + " #" + event.getChannel().getName() + ": Sent website link");

    }
}
