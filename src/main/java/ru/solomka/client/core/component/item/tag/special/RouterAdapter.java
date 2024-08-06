package ru.solomka.client.core.component.item.tag.special;

import org.jetbrains.annotations.NotNull;
import ru.solomka.client.core.component.item.tag.special.route.Init;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public interface RouterAdapter {

    @SuppressWarnings("unchecked")
    static void callReference(@NotNull Object clazz) {
        List<Field> fields = Arrays.stream(clazz.getClass().getDeclaredFields()).toList();

        fields.stream().filter(field -> field.isAnnotationPresent(Init.class)).forEach(field -> {
            Init init = field.getAnnotation(Init.class);

            field.setAccessible(true);

            Class<Object> primitiveInitialClass = (Class<Object>) init.clazz();
            String primitiveInitialMethod = init.method();
            String primitiveInitialField = init.field();

            if(primitiveInitialClass != null) {

                Object instance;
                try {
                    instance = primitiveInitialClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                Object result;

                if(!primitiveInitialMethod.isEmpty()) {
                    Method currentMethod;
                    try {
                        currentMethod = instance.getClass().getMethod(primitiveInitialMethod);
                    } catch (NoSuchMethodException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        result = currentMethod.invoke(instance);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    List<Field> remapFields = Arrays.stream(instance.getClass().getDeclaredFields()).peek(c -> c.setAccessible(true)).toList();
                    try {
                        result = Objects.requireNonNull(remapFields.stream().filter(a -> a.getName().equals(primitiveInitialField)).findFirst().orElse(null)).get(instance);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }

                try {
                    field.set(clazz, result);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                try {
                    field.set(clazz, init.value());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}