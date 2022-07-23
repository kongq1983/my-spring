package com.kq.springframework.beans.factory.config;

/**
 * @author kq
 * @date 2022-07-18 14:00
 * @since 2020-0630
 */
public class RootBeanDefinition implements BeanDefinition{

    private volatile Object beanClass;

    public RootBeanDefinition(Class<?> beanClass) {
        super();
        setBeanClass(beanClass);
    }

    public RootBeanDefinition(String beanClassName) {
        setBeanClassName(beanClassName);
    }

    @Override
    public void setBeanClassName(String beanClassName) {
        this.beanClass = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        Object beanClassObject = this.beanClass;
        if (beanClassObject instanceof Class) {
            return ((Class<?>) beanClassObject).getName();
        }
        else {
            return (String) beanClassObject;
        }
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }
}
