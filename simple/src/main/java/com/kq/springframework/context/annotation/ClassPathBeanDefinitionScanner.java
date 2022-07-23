package com.kq.springframework.context.annotation;

import com.kq.springframework.core.io.FileSystemResource;
import com.kq.springframework.core.io.support.PathMatchingResourcePatternResolver;
import com.kq.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author kq
 * @date 2022-07-18 9:08
 * @since 2020-0630
 */
public class ClassPathBeanDefinitionScanner {

    // ClassUtils.getDefaultClassLoader()

    private PathMatchingResourcePatternResolver patternResolver;

    public ClassPathBeanDefinitionScanner(){
        patternResolver = new PathMatchingResourcePatternResolver();
    }

    public int scan(String... basePackages) {
//        int beanCountAtScanStart = this.registry.getBeanDefinitionCount();

            doScan(basePackages);

        // Register annotation config processors, if necessary.
//        if (this.includeAnnotationConfig) {
//            AnnotationConfigUtils.registerAnnotationConfigProcessors(this.registry);
//        }
//
//        return (this.registry.getBeanDefinitionCount() - beanCountAtScanStart);

        return 1;
    }

    public void doScan(String... basePackages) {




        for (String basePackage : basePackages) {

            try {
                FileSystemResource[] rs = patternResolver.getResources(basePackage);

                for(FileSystemResource file : rs) {
//                    Class clazz = Class.forName()
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

//            Class clazz = Class.forName();
        }

    }

    /**
     * 简单的判断类是否存在@Component
     * @param clazz
     * @return
     * @throws IOException
     */
    protected boolean isCandidateComponent(Class clazz) throws IOException {
//        for (TypeFilter tf : this.excludeFilters) { // 先排除过滤  excludeFilters有默认config的配置类 @Configuration
//            if (tf.match(metadataReader, getMetadataReaderFactory())) {
//                return false;
//            }
//        } // includeFilters: 默认有@Component @ManagedBean
//        for (TypeFilter tf : this.includeFilters) {
//            if (tf.match(metadataReader, getMetadataReaderFactory())) {
//                return isConditionMatch(metadataReader);
//            }
//        }

        return clazz.isAnnotationPresent(Component.class);

    }

}
