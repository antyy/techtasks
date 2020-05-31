package com.antyy.study.examples.spring.first.beans.impl;

import com.antyy.study.examples.spring.first.beans.SecondService;
import com.antyy.study.examples.spring.first.beans.ThirdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public class ThirdServiceImp  implements ThirdService {

    @Autowired
    private SecondService secondService;

    public ThirdServiceImp(){
        if (nonNull(secondService)){
            log.info("Calling second service ");
            secondService.reformActionUsingFirstService();
        } else{
            log.info("Second service is null in constructor of third service");
        }
    }


    @PostConstruct
    public void init(){
        if (nonNull(secondService)){
            log.info("Calling second service in init method of first service ");
            secondService.reformActionUsingFirstService();
        } else{
            log.info("Second service is null in init method of third service");
        }
    }

    @EventListener
    public void handleEvent(ContextRefreshedEvent cre) {
        if (nonNull(secondService)){
            log.info("Calling second service in handle Event of first service ");
            secondService.reformActionUsingFirstService();
        } else{
            log.info("Second service is null in handle Event of third service");
        }
    }

    @Override
    public void anotherWork() {

    }
}
