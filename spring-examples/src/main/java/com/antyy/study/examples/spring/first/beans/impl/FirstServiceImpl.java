package com.antyy.study.examples.spring.first.beans.impl;

import com.antyy.study.examples.spring.first.annotation.InjectAfterInit;
import com.antyy.study.examples.spring.first.annotation.InjectBeforeInit;
import com.antyy.study.examples.spring.first.beans.FirstService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;

@Slf4j
public class FirstServiceImpl implements FirstService {


    private String initializedInConstructor;
    @InjectBeforeInit
    private String initializedInBPPPreInit;
    private String initializedInInit;
    @InjectAfterInit
    private String initializedInBppPostInit;
    private String initializedInEvent;

    public FirstServiceImpl() {
        initializedInConstructor = "Initialized In Constructor";

        log.info("********* First service *************");
        log.info("Phase 1. Java class constructor");
        log.info("*************************************\n");

     //   printAll();
    }

    @PostConstruct
    public void init() {
        initializedInInit = "Initialized in post construct";

        log.info("********* First service *************");
        log.info("Phase 2. Post Construct init method");
        log.info("*************************************\n");

    //    printAll();
    }


    @EventListener
    public void handleEvent(ContextRefreshedEvent cre) {
        initializedInEvent = "Initialized in context refreshed event.";

        log.info("********* First service *************");
        log.info("Phase 3. Context refreshed event.");
        log.info("*************************************\n");

     //   printAll();

    }


    public void doSomeWork() {
        printAll();
    }

    public String getSomeMessage() {
        return initializedInConstructor;
    }

    public void printAll() {
        log.info("constr = {}", initializedInConstructor);
        log.info("BPP Pre init = {}", initializedInBPPPreInit);
        log.info("Init = {}", initializedInInit);
        log.info("BPP post init  = {}", initializedInBppPostInit);
        log.info("Event = {}", initializedInEvent);
        log.info("\n");
    }

}
