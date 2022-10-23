package phase3.server.controller;

import phase3.server.dataBase.Context;

import java.time.format.DateTimeFormatter;

public class Controller {
    public static DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter dformatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected Context context;
    public Controller(){
        context = new Context();
    }
}