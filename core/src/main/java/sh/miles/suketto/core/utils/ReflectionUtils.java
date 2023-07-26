package sh.miles.suketto.core.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Provides a handful of utilities for reflection
 */
public final class ReflectionUtils {

    /**
     * Utility class
     */
    private ReflectionUtils() {
    }

    /**
     * Creates a new instance of a class from the provided class and constructor parameters
     *
     * @param clazz  the class to make a new instance of
     * @param params the parameters to pass in to the constructor
     * @param <T>    the class T
     * @return a new instance of the class with the parameters or null if errors occur
     */
    public static <T> T newInstance(@NotNull final Class<T> clazz, @NotNull final Object[] params) {
        Objects.requireNonNull(clazz);
        Objects.requireNonNull(params);

        try {
            final Constructor<T> constructor = clazz.getConstructor(classesFromParameters(params));
            constructor.setAccessible(true);
            final T instance = constructor.newInstance(params);
            constructor.setAccessible(false);
            return instance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            return null;
        }
    }

    /**
     * Creates a new instance of a class from the provided class path and constructor paramaters
     *
     * @param classPath the classPath
     * @param params    the parameters
     * @param <T>       the Type
     * @return the intended instance
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(@NotNull final String classPath, @NotNull final Object[] params) {
        Objects.requireNonNull(classPath);
        Objects.requireNonNull(params);

        try {
            final Class<T> clazz = (Class<T>) Class.forName(classPath);
            return newInstance(clazz, params);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Attempts to retrieve a fields value from the specified info
     *
     * @param instance  the instance of the object to get the field from
     * @param fieldName the name of the field
     * @param fieldType the type of the field
     * @param <T>       The Field Type
     * @return the value of the field or null if errors occur or the field is null
     */
    public static <T> T getField(@NotNull final Object instance, @NotNull final String fieldName, @NotNull final Class<T> fieldType) {
        return getField(instance.getClass(), instance, fieldName, fieldType);
    }

    /**
     * Attempts to retrieve a static field's value from the specified info
     *
     * @param parentClass the parentClass of the field
     * @param fieldName   the fields name
     * @param fieldType   the type of the field
     * @param <T>         the field type
     * @return the value of the field or null if errors occur or the field is null
     */
    public static <T> T getField(@NotNull final Class<?> parentClass, @NotNull final String fieldName, @NotNull final Class<T> fieldType) {
        return getField(parentClass, null, fieldName, fieldType);
    }

    /**
     * Attempts to retrieve a fields value from the specified info
     *
     * @param parentClass the parentClass which contains the field
     * @param instance    the instance of the class if the field isn't static
     * @param fieldName   the name of the field to get
     * @param fieldType   the type of the field
     * @param <T>         the Field Type
     * @return the value of the field or null if errors occur or the field is null
     */
    private static <T> T getField(@NotNull final Class<?> parentClass, @Nullable final Object instance, @NotNull final String fieldName, @NotNull final Class<T> fieldType) {
        Objects.requireNonNull(parentClass);
        Objects.requireNonNull(fieldName);
        Objects.requireNonNull(fieldType);

        try {
            final Field field = parentClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            final T value = fieldType.cast(field.get(instance));
            field.setAccessible(false);
            return value;
        } catch (NoSuchFieldException | IllegalAccessException | ClassCastException e) {
            return null;
        }
    }

    /**
     * Creates an array of classes from an array of object parameters
     *
     * @param parameters the objects to convert to parameters
     * @return an array of parameters
     */
    @Contract("_ -> new")
    public static Class<?>[] classesFromParameters(@NotNull final Object[] parameters) {
        Objects.requireNonNull(parameters);

        final Class<?>[] clazzes = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            clazzes[i] = parameters[i].getClass();
        }

        return clazzes;
    }


}
