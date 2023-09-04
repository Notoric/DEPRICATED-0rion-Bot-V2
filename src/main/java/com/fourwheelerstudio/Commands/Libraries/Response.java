package com.fourwheelerstudio.Commands.Libraries;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.Color;

public class Response {
    /**
     * A message response with a bold title and a plain body
     * @param event the event to respond to
     * @param title the large text at the top
     * @param body the smaller text at the bottom
     * @param colour the colour of the left margin
     */
    public static void genericResponse(SlashCommandInteractionEvent event, String title, String body, Color colour) {

        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(colour);
        eb.setTitle(title);
        eb.setDescription(body);

        event.getHook().sendMessageEmbeds(eb.build()).queue();
    }
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
    /**
     * Called when the bot is force disconnected, sends a message
     * notifying the server what has happened. This message is sent
     * to the default channel (unless a different channel is set as
     * that servers preferred channel for the bot.)
     * @param event the event to respond to
     */
    public static void Disconnected(GuildVoiceUpdateEvent event) {
        
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(new Color(255, 85, 0));
        eb.setTitle("Goodbye.");
        eb.setDescription("I have been destroyed and have left  🔊 **" + event.getOldValue().getName() + "**");
//                       \/ this bit here is the problem \/
        event.getGuild().getDefaultChannel().asTextChannel().sendMessageEmbeds(eb.build()).queue(); // need to implement channel logic (db)
    }
    /**
     * Called when the bot is force moved, sends a message
     * notifying the server what has happened. This message is sent
     * to the default channel (unless a different channel is set as
     * that servers preferred channel for the bot.)
     * @param event the event to respond to
     */
    public static void Moved(GuildVoiceUpdateEvent event) {
        
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(new Color(255, 85, 0));
        eb.setTitle("Active channel moved.");
        eb.setDescription("I have been moved from  🔊 **" + event.getChannelLeft().getName() + "** to 🔊 **" + event.getChannelJoined().getName() + "**");
//                       \/ this bit here is the problem \/
        event.getGuild().getDefaultChannel().asTextChannel().sendMessageEmbeds(eb.build()).queue(); // need to implement channel logic (db)      
    }
}
