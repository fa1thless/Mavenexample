package loggers;
import beans.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileLogger(String filename, int cacheSize) {
        super(filename);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);

    }

    public void destroy(){
        if (!cache.isEmpty()){
            writeEventsFromCache();
        }
    }

    @Override
    public void logEvent(Event event){
        cache.add(event);

        if (cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache(){
        cache.stream().forEach(super::logEvent);
    }


}
