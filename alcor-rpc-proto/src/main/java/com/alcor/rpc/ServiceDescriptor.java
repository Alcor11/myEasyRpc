package com.alcor.rpc;

import lombok.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author guchun
 * @description 表示服务
 * @date 2022/6/6 19:34
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    private String clazz;
    private String method;
    private String returnType;
    private String[] parameterTypes;

    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor sdq = new ServiceDescriptor();
        sdq.setClazz(clazz.getName());
        sdq.setMethod(method.getName());
        sdq.setReturnType(method.getReturnType().getName());

        Class[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameterTypes[i] = parameterClasses[i].getName();
        }
        sdq.setParameterTypes(parameterTypes);
        return sdq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceDescriptor that = (ServiceDescriptor) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public String toString() {
        return "clazz=" + clazz
                + "method=" + method
                + "returnType=" + returnType
                + "parameterType=" + Arrays.toString(parameterTypes);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
