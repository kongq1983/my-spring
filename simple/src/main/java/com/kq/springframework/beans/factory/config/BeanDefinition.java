package com.kq.springframework.beans.factory.config;

/**
 * @author kq
 * @date 2022-07-18 13:55
 * @since 2020-0630
 */
public interface BeanDefinition {


    void setBeanClassName(String beanClassName);

    String getBeanClassName();

//    void setLazyInit(boolean lazyInit);
//
//    boolean isLazyInit();
//
//    void setInitMethodName(String initMethodName);
//
//
//    String getInitMethodName();
//
//    /**
//     * Set the name of the destroy method.
//     * @since 5.1
//     */
//    void setDestroyMethodName(String destroyMethodName);
//
//    /**
//     * Return the name of the destroy method.
//     * @since 5.1
//     */
//    String getDestroyMethodName();
//
//    boolean isSingleton();

}
