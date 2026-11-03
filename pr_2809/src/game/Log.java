package game;

import java.io.PrintStream;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class Log {
    private static boolean configured = false;

    public static Logger get(Class<?> cls) {
        configure();
        return Logger.getLogger(cls.getName());
    }

    private static synchronized void configure() {
        if (configured) {
            return;
        }
        Logger root = Logger.getLogger("");
        for (Handler h : root.getHandlers()) {
            root.removeHandler(h);
        }
        Handler handler = new StreamHandler(System.out, new GameFormatter()) {
            @Override
            public synchronized void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        handler.setLevel(Level.ALL);
        root.addHandler(handler);
        root.setLevel(Level.INFO);
        configured = true;
    }

    private static class GameFormatter extends Formatter {
        @Override
        public String format(LogRecord r) {
            String text = formatMessage(r);
            if (r.getLevel() == Level.INFO) {
                return text + System.lineSeparator();
            }
            return "[" + r.getLevel() + "] " + text + System.lineSeparator();
        }
    }
}
