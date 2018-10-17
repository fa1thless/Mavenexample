import beans.Client;
import beans.Event;
import loggers.EventLogger;
import loggers.FileEventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;
    private FileEventLogger fileEventLogger;

    public App (Client client, EventLogger logger, FileEventLogger fileEventLogger){
            this.client = client;
            this.eventLogger = logger;
            this.fileEventLogger = fileEventLogger;
    }


    public void logEvent (Event event, String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
        fileEventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 1");


        event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 2");

        ctx.close();
    }
}
