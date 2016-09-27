package se.infinera.metro.alarm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import se.infinera.metro.alarm.acceptancetest.testimplementation.AddNodeAcceptanceTest;

@Slf4j

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        AddNodeAcceptanceTest addNodeAcceptanceTest = ctx.getBean(AddNodeAcceptanceTest.class);
        addNodeAcceptanceTest.run();
    }
}
