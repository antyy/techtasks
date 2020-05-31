package com.antyy.study.examples.spring.first.beans.impl;

import com.antyy.study.examples.spring.first.beans.FirstService;
import com.antyy.study.examples.spring.first.beans.SecondService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecondServiceImpl implements SecondService {
    private final FirstService firstService;


    public SecondServiceImpl(FirstService firstService) {
        this.firstService = firstService;
        log.info("Calling first service in constructor of second service");
        firstService.doSomeWork();
    }

    @Override
    public void reformActionUsingFirstService() {
        firstService.doSomeWork();
    }
}
