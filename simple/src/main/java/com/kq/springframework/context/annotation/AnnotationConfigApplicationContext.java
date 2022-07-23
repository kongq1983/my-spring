package com.kq.springframework.context.annotation;

import com.kq.springframework.support.GenericApplicationContext;

/**
 * @author kq
 * @date 2022-07-18 9:03
 * @since 2020-0630
 */
public class AnnotationConfigApplicationContext extends GenericApplicationContext {

    private ClassPathBeanDefinitionScanner scanner;

    /**
     * 目前先私有
     */
    private AnnotationConfigApplicationContext(){
        scanner = new ClassPathBeanDefinitionScanner();
    }


    public AnnotationConfigApplicationContext(String... basePackages) {
        this();
        scan(basePackages);
        refresh();
    }


    public void scan(String... basePackages) {
        this.scanner.scan(basePackages);
    }


}
