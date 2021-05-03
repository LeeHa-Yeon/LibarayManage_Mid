package middleTermProject;

import middleTermProject.Screen.LoginScreen;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) throws IOException {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);

        LoginScreen loginScreen = (LoginScreen) ctx.getBean("loginScreen");
        loginScreen.memuPrint();

//        Test test = (Test) ctx.getBean("test");
//        test.testPrint();


        ctx.close();
    }
}
