package se.infinera.metro.alarm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import se.infinera.metro.alarm.testimplementation.AddNodeAcceptanceTest;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        AddNodeAcceptanceTest addNodeAcceptanceTest = ctx.getBean(AddNodeAcceptanceTest.class);
        addNodeAcceptanceTest.run();
    }
}
