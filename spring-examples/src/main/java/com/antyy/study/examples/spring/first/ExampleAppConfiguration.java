package com.antyy.study.examples.spring.first;


import com.antyy.study.examples.spring.first.beans.FirstService;
import com.antyy.study.examples.spring.first.beans.SecondService;
import com.antyy.study.examples.spring.first.beans.ThirdService;
import com.antyy.study.examples.spring.first.beans.impl.SecondServiceImpl;
import com.antyy.study.examples.spring.first.beans.impl.FirstServiceImpl;
import com.antyy.study.examples.spring.first.beans.impl.ThirdServiceImp;
import com.antyy.study.examples.spring.first.postprocessor.InjectExampleStringAnnotationBPP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ExampleAppConfiguration {

    @Bean
    public FirstService firstService() {
        return new FirstServiceImpl();
    }

    @Bean
    public InjectExampleStringAnnotationBPP injectExampleStringAnnotationBPP() {
        return new InjectExampleStringAnnotationBPP();
    }

    @Bean
    public SecondService secondService(FirstService firstService){
        return  new SecondServiceImpl(firstService);
    }

    @Bean
    public ThirdService thirdService(){
        return  new ThirdServiceImp();
    }
}
