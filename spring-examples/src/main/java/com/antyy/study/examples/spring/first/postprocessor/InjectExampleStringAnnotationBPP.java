package com.antyy.study.examples.spring.first.postprocessor;

import com.antyy.study.examples.spring.first.annotation.InjectAfterInit;
import com.antyy.study.examples.spring.first.annotation.InjectBeforeInit;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.Objects.nonNull;

@Slf4j
public class InjectExampleStringAnnotationBPP implements BeanPostProcessor {
    public static final String DEFAULT_STRING = "Initialized in BPP before init";
    private Map<String, List<String>> injectAfterInitBeansAndFields = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (nonNull(field.getAnnotation(InjectBeforeInit.class))) {
                log.debug("Bean post construct for {} object {}", beanName, bean);
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, DEFAULT_STRING);
            }
            if (nonNull(field.getAnnotation(InjectAfterInit.class))) {
                log.debug("After init injection field found for bean = {}", beanName);
                injectAfterInitBeansAndFields.computeIfAbsent(beanName, t -> new ArrayList<>()).add(field.getName());
            }
        }
        return bean;
    }

    @Override
    @SneakyThrows
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        List<String> fields = null;
        if ((fields = injectAfterInitBeansAndFields.get(beanName)) != null)
            for (String field : fields) {
                log.debug("Setting field {} for bean {}", field, beanName);
                Field currentField = stream(bean.getClass().getDeclaredFields())
                        .filter(t -> t.getName().equals(field))
                        .findFirst().get();
                currentField.setAccessible(true);
                ReflectionUtils.setField(currentField, bean, "Initialized in BPP after init");
            }
        return bean;
    }
}
