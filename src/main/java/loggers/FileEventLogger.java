package loggers;
import beans.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class FileEventLogger implements EventLogger {
    private String filename;
    private File file;

    public FileEventLogger (String filename) {
        this.filename = filename;
    }

    public void init() {
        file = new File(filename);
        if (file.exists() && !file.canWrite()) {
            throw new IllegalArgumentException("Can't write to file " + filename);
        } else if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new IllegalArgumentException("Can't create file", e);
            }


        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n",Charset.defaultCharset(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

