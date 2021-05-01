package middleTermProject;

import middleTermProject.Screen.LoginScreen;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);

        LoginScreen loginScreen = (LoginScreen) ctx.getBean("loginScreen");
        loginScreen.memuPrint();


        ctx.close();
    }
}
