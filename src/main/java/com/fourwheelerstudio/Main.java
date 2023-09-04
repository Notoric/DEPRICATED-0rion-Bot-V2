package com.fourwheelerstudio;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.fourwheelerstudio.Commands.CommandBuilder;
import com.fourwheelerstudio.Commands.CommandListener;
import com.fourwheelerstudio.Commands.DisconnectListener;

import io.github.cdimascio.dotenv.Dotenv;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {
    /**
     * Builds the bot and initialised the event listeners
     * @throws InterruptedException
     * @throws IOException
     * @token gets the bots token
     * @logger initialises the logger and sets the output file in /logs/
     */
    public static void main(String[] args) throws InterruptedException, IOException {

        Dotenv dotenv = Dotenv.load();
        String token = dotenv.get("TOKEN");

        final Logger logger = Logger.getLogger("orion");
        FileHandler fh;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd-HH.mm.ss");

        /*
         * Tries to create log file in log folder
         */
        try {
            fh = new FileHandler("logs" + System.getProperty("file.separator") + dtf.format(LocalDateTime.now()) + ".log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter); 
        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  

        JDA Orion = JDABuilder.createDefault(token)
            .addEventListeners(new CommandListener(), new DisconnectListener())
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .build().awaitReady();

        CommandBuilder.BuildCommands(Orion);
    }
}