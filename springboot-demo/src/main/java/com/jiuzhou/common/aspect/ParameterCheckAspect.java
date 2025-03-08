package com.jiuzhou.common.aspect;

import com.alibaba.fastjson.JSON;
import com.jiuzhou.exception.ServiceException;
import io.swagger.annotations.ApiModelProperty;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class ParameterCheckAspect {

    @Pointcut("execution(public * com.jiuzhou.controller.*.*(..))")
    public void controllerMethods() {}

    @Before("controllerMethods()")
    public void checkParameters(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Annotation[] annotations = parameter.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(RequestBody.class)) {
                    Object arg = joinPoint.getArgs()[i];
                    checkFields(arg);
                }
            }
        }
    }

    private void checkFields(Object arg){

        List<String> emptyFields = new ArrayList<>();

        Field[] fields = arg.getClass().getDeclaredFields();
        //检查 NotNull注解
        validFieldNotNull(arg,fields,emptyFields);
        // 判断是否有属性为空的字段
        if (!emptyFields.isEmpty()) {
            // 处理属性为空的逻辑，例如返回错误信息或抛出异常
            throw new ServiceException(500,JSON.toJSONString(emptyFields)+"不能为空");
        }
    }

    private void validFieldNotNull(Object arg,Field[] fields,List<String> emptyFields){
        for (Field field : fields) {
            // 判断字段是否加了NotNull注解
            if (field.isAnnotationPresent(ApiModelProperty.class)) {
                // 设置字段可访问，否则会抛出IllegalAccessException异常
                field.setAccessible(true);
                try {
                    ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                    // 判断字段值是否为空
                    if (apiModelProperty.required()&&(field.get(arg) == null||field.get(arg)=="")) {
                        emptyFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
