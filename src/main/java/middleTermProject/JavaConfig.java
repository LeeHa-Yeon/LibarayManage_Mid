package middleTermProject;


import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
//
    @Bean
    public UserManagment userManagment(){
        return new UserManagment();
    }
    @Bean
    public BookManagment bookManagment(){
        return new BookManagment();
    }
//    @Bean
//
//    @Bean
//
//    @Bean


}
