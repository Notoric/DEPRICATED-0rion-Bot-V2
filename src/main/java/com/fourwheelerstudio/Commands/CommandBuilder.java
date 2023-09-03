package com.fourwheelerstudio.Commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

public class CommandBuilder {
    /**
     * this function builds the user's view of the bots commands and explanations
     * it loops through each guild and adds the commands to them one by one
     * @param Orion the bot's JDA object
     */
    public static void BuildCommands(JDA Orion) {
        for (Guild gld : Orion.getGuilds()) {
            gld.upsertCommand("join", "joins the voice channel").queue();
            gld.upsertCommand("leave", "leaves the voice channel").queue();
        }
    }
}