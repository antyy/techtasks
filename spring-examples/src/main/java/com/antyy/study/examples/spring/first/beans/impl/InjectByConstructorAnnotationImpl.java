package com.antyy.study.examples.spring.first.beans.impl;

import com.antyy.study.examples.spring.first.beans.FirstService;
import com.antyy.study.examples.spring.first.beans.InjectByConstructorAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectByConstructorAnnotationImpl implements InjectByConstructorAnnotation {

    private FirstService firstService;

    @Autowired
    public InjectByConstructorAnnotationImpl(FirstService firstService) {
        this.firstService = firstService;
        System.out.println(firstService);
        firstService.doSomeWork();
    }

    public void callFirstService() {
        firstService.doSomeWork();
    }
}
