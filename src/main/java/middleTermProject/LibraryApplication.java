package middleTermProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
        TestManager testManager = (TestManager) ctx.getBean("testManager");

        testManager.mainMemu();

        ctx.close();
    }
}
