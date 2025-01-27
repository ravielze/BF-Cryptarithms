package cryptarithms.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * References from:
 * https://stackoverflow.com/questions/53211694/change-color-and-format-of-java-util-logging-logger-output-in-eclipse
 * 
 * and Modified for personal use
 */
public class LogFormatter extends Formatter {

    // ANSI escape code
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Here you can configure the format of the output and 
    // its color by using the ANSI escape codes defined above.

    // format is called for every console log message
    @Override
    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder();
        switch (record.getLevel().getName()){
            case "SEVERE":
                builder.append(ANSI_RED);
                break;
            case "WARNING":
                builder.append(ANSI_YELLOW);
                break;
            case "FINE":
                builder.append(ANSI_GREEN);
                break;
            default:
                builder.append(ANSI_WHITE);
                break;
        }

        builder.append("[");
        builder.append(calcDate(record.getMillis()));
        builder.append(" " + record.getLevel().getName());
        builder.append("]:");

        builder.append(ANSI_WHITE);
        builder.append(" ");
        builder.append(record.getMessage());

        Object[] params = record.getParameters();

        if (params != null)
        {
            builder.append("\t");
            for (int i = 0; i < params.length; i++)
            {
                builder.append(params[i]);
                if (i < params.length - 1)
                    builder.append(", ");
            }
        }

        builder.append(ANSI_RESET);
        builder.append("\n");
        return builder.toString();
    }

    private String calcDate(long millisecs) {
        SimpleDateFormat date_format = new SimpleDateFormat("dd MMM HH:mm:ss");
        Date resultdate = new Date(millisecs);
        return date_format.format(resultdate);
    }
    
}
