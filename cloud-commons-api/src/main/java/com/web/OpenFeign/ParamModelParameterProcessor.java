package com.web.OpenFeign;

import feign.MethodMetadata;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @ProjectName: cloudTX
 * @Package: com.web.OpenFeign
 * @ClassName: ParamModelParameterProcessor
 * @Author: gwl
 * @Description:
 * @Date: 2020/8/5 11:48
 * @Version: 1.0
 */


public class ParamModelParameterProcessor implements AnnotatedParameterProcessor {

    private static final Class<ParamModel> ANNOTATION = ParamModel.class;

    public Class<? extends Annotation> getAnnotationType() {
        return ANNOTATION;
    }

    @Override
    public boolean processArgument(AnnotatedParameterContext context, Annotation annotation, Method method) {

        int parameterIndex = context.getParameterIndex();
        Class parameterType = method.getParameterTypes()[parameterIndex];
        MethodMetadata data = context.getMethodMetadata();

        Field[] fields = parameterType.getDeclaredFields();

        for(Field field: fields) {
            String name = field.getName();
            context.setParameterName(name);

            Collection query = context.setTemplateParameter(name, (Collection)data.template().queries().get(name));
            data.template().query(name, query);
        }
        data.indexToExpander().put(context.getParameterIndex(), new ModelExpander());

        return true;
    }
}
