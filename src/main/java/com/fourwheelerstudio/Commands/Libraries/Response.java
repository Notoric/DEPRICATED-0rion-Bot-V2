package com.fourwheelerstudio.Commands.Libraries;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.Color;

public class Response {
    /**
     * A message response where there is a small line of text,
     * then a bold larger line of text, then another small line of text.
     * @param event the event to respond to
     * @param subtext the small text at the top
     * @param body the large text in the middle
     * @param footer the small text at the bottom
     * @param colour the colour of the left margin
     */
    public static void genericResponseA(SlashCommandInteractionEvent event, String subtext, String body, String footer, Color colour) {

        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(colour);
        eb.setDescription(subtext);

        eb.addField(body, footer, false);

        event.getHook().sendMessageEmbeds(eb.build()).queue();
    }
    /**
     * A red margined response notifying the user that there
     * was an error when executing their command. 
     * @param event the event to respond to
     * @param error the body of the text
     */
    public static void errorResponse(SlashCommandInteractionEvent event, String error) {
        
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Color.red);
        eb.setTitle("❌ Error");
        eb.setDescription(error);

        event.getHook().sendMessageEmbeds(eb.build()).queue();
    }
}
