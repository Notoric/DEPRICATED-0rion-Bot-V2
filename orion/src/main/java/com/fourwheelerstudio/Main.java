package com.fourwheelerstudio;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fourwheelerstudio.Commands.CommandBuilder;

import io.github.cdimascio.dotenv.Dotenv;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    /**
     * Builds the bot and initialised the event listeners
     * @throws InterruptedException
     * @token gets the bots token
     * 
     */
    public static void main(String[] args) throws InterruptedException {

        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("TOKEN");

        System.out.println(token);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH.mm.ss");
        File logFile = new File("logs" + System.getProperty("file.separator") + dtf.format(LocalDateTime.now()) + ".log");

        JDA Orion = JDABuilder.createDefault(token).addEventListeners().enableIntents(GatewayIntent.MESSAGE_CONTENT).build().awaitReady();
        CommandBuilder.BuildCommands(Orion);
    
    }
}