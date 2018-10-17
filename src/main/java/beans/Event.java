package beans;

import java.text.DateFormat;
import java.util.Date;

public class Event {
    private int id = (int)(Math.random()*100);
    private String msg;
    private Date date;
    private DateFormat dateFormat;

    public Event (Date date, DateFormat df){
        this.date = date;
        this.dateFormat = df;

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "beans.Event{" +
                " id = " + id +
                ", msg = '" + msg + '\'' +
                ", dateFormat = " + dateFormat.format(date) +
                '}';
    }
}
