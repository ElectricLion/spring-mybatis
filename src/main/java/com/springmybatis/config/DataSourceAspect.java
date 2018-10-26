package com.springmybatis.config;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component

@Aspect
public class DataSourceAspect {
    Logger logger = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("@annotation(com.springmybatis.config.ReadOnlyConnection)")

    public void dataSourcePointcut() {

    }


    @Around("dataSourcePointcut()")

    public Object doAround(ProceedingJoinPoint pjp) {

        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();

        Method method = methodSignature.getMethod();

        ReadOnlyConnection typeAnno = method.getAnnotation(ReadOnlyConnection.class);

        DbContextHolder.DbType sourceEnum = typeAnno.value();


        if (sourceEnum == DbContextHolder.DbType.WRITE) {
            logger.info("++++++++++++++切换读库+++++++++++++++");
            DbContextHolder.setDbType(DbContextHolder.DbType.WRITE);

        } else if (sourceEnum == DbContextHolder.DbType.READ) {
            logger.info("++++++++++++++切换读库+++++++++++++++");
            DbContextHolder.setDbType(DbContextHolder.DbType.READ);

        }
        Object result = null;

        try {

            result = pjp.proceed();

        } catch (Throwable throwable) {

            throwable.printStackTrace();

        } finally {

            DbContextHolder.resetDbType();

        }


        return result;

    }

}