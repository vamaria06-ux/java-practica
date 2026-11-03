package org.example;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class MethodRunner {
    private static final Logger LOG = Log.get(MethodRunner.class);

    public static void runAnnotated(Object target) {
        for (Method method : target.getClass().getDeclaredMethods()) {
            Repeat repeat = method.getAnnotation(Repeat.class);
            if (repeat == null) {
                continue;
            }

            int modifiers = method.getModifiers();
            if (!Modifier.isProtected(modifiers) && !Modifier.isPrivate(modifiers)) {
                continue;
            }

            method.setAccessible(true);
            Object[] args = buildArgs(method.getParameterTypes(), 0);

            LOG.info("Вызываю " + method.getName() + " " + repeat.value() + " раз(а)");
            for (int i = 0; i < repeat.value(); i++) {
                try {
                    method.invoke(target, args);
                } catch (IllegalAccessException e) {
                    LOG.severe("Нет доступа к методу " + method.getName() + ": " + e);
                } catch (InvocationTargetException e) {
                    LOG.warning("Метод " + method.getName() + " выбросил исключение: " + e.getCause());
                }
            }
        }
    }

    private static final int MAX_DEPTH = 5;

    private static Object[] buildArgs(Class<?>[] types, int depth) {
        Object[] args = new Object[types.length];
        for (int i = 0; i < types.length; i++) {
            args[i] = defaultValue(types[i], depth);
        }
        return args;
    }

    private static Object defaultValue(Class<?> type, int depth) {
        if (type == boolean.class || type == Boolean.class) return false;
        if (type == char.class || type == Character.class) return 'a';
        if (type == byte.class || type == Byte.class) return (byte) 0;
        if (type == short.class || type == Short.class) return (short) 0;
        if (type == int.class || type == Integer.class) return 0;
        if (type == long.class || type == Long.class) return 0L;
        if (type == float.class || type == Float.class) return 0f;
        if (type == double.class || type == Double.class) return 0d;
        if (type == String.class) return "";

        if (type.isArray()) {
            return Array.newInstance(type.getComponentType(), 0);
        }
        if (type.isEnum()) {
            Object[] constants = type.getEnumConstants();
            return constants.length > 0 ? constants[0] : null;
        }
        if (type == List.class || type == Collection.class) return new ArrayList<>();
        if (type == Set.class) return new HashSet<>();
        if (type == Map.class) return new HashMap<>();

        if (depth >= MAX_DEPTH) {
            return null;
        }

        Constructor<?>[] constructors = type.getDeclaredConstructors();
        Arrays.sort(constructors, Comparator.comparingInt(Constructor::getParameterCount));
        for (Constructor<?> constructor : constructors) {
            try {
                constructor.setAccessible(true);
                Object[] args = buildArgs(constructor.getParameterTypes(), depth + 1);
                return constructor.newInstance(args);
            } catch (ReflectiveOperationException | RuntimeException e) {
            }
        }

        LOG.warning("Не удалось создать значение для типа " + type.getSimpleName()
                + ", подставляю null");
        return null;
    }
}