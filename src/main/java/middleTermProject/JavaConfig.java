package middleTermProject;


import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
//
    @Bean
    public TestManager testManager(){
        return new TestManager();
    }
//    @Bean
//
//    @Bean
//
//    @Bean


}
