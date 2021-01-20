package cryptarithms;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;

import cryptarithms.logger.LogFormatter;

public class CBF {

    private static CBF instance;
    private final Logger logger = Logger.getLogger("CryptaBF");

    {
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        Formatter formatter = new LogFormatter();
        handler.setFormatter(formatter);

        logger.addHandler(handler);
    }

    public Logger getLogger(){
        return this.logger;
    }

    public static CBF getInstance(){
        return instance;
    }

    public CBF(){
        instance = this;
    }

    public void run(){
        getLogger().info("test");
    }
    
}
