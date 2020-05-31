package com.antyy.study.examples.spring.first;

import com.antyy.study.examples.spring.first.beans.FirstService;
import com.antyy.study.examples.spring.first.beans.InjectByConstructorAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class AppExample {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ExampleAppConfiguration.class);

        InjectByConstructorAnnotation serviceExample = context.getBean(InjectByConstructorAnnotation.class);
        serviceExample.callFirstService();
    }
}
