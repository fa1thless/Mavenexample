import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;

    public App (Client client, EventLogger logger){
            this.client = client;
            this.eventLogger = logger;
    }


    public void logEvent (Event event, String msg){
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event,"Some event for 2");


    }
}
