package middleTermProject;


import middleTermProject.Screen.LibraryManagerScreen;
import middleTermProject.Screen.LibraryUserScreen;
import middleTermProject.Screen.LoginScreen;
import middleTermProject.System.LibraryManagerSystem;
import middleTermProject.System.LibraryUserSystem;
import middleTermProject.System.LoginSystem;
import middleTermProject.System.UserSystem;
import middleTermProject.Zdelete.BookManagment;
import middleTermProject.Zdelete.UserManagment;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    public LoginScreen loginScreen(){ return new LoginScreen(); }
    @Bean
    public LoginSystem loginSystem(){ return new LoginSystem(); }
    @Bean
    public LibraryUserScreen libraryUserScreen(){ return new LibraryUserScreen(); }
    @Bean
    public LibraryManagerScreen libraryManagerScreen(){ return new LibraryManagerScreen();}
    @Bean
    public LibraryUserSystem libraryUserSystem(){ return new LibraryUserSystem();}
    @Bean
    public LibraryManagerSystem libraryManagerSystem(){ return new LibraryManagerSystem(); }
    @Bean
    public UserSystem userSystem(){ return new UserSystem(); }



}
