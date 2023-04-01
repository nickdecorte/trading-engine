package io.trading.engine.core.common;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;

public class ClassReflectionFactory<T> {

    protected BarSeries barSeries;

    public ClassReflectionFactory(BarSeries barSeries) {
        this.barSeries = barSeries;
    }

    protected T makeInstance(
        String type,
        Map<String, Object> arguments,
        Map<String, Indicator> variables
    ) {
        Constructor<?>[] constructors = getConstructorsByClassType(type);
        Object[] args = makeArgumentArray(arguments, variables);

        Exception lastException = null;
        for (Constructor constructor : constructors) {
            try {
                return (T) constructor.newInstance(args);
            } catch (Exception e) {
                lastException = e;
            }
        }

        throw new RuntimeException("No matching constructor found for: " + type, lastException);
    }

    private static Constructor<?>[] getConstructorsByClassType(String classType) {
        try {
            return Class.forName(classType).getConstructors();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private Object[] makeArgumentArray(Map<String, Object> arguments, Map<String, Indicator> indicators) {
        List<Object> objects = new ArrayList<>();

        for(Map.Entry<String, Object> entry : arguments.entrySet()) {
            if (entry.getKey().equals("barSeries")) {
                objects.add(barSeries);
            } else if (entry.getKey().startsWith("ref")) {
                objects.add(indicators.get(entry.getValue()));
            } else {
                objects.add(entry.getValue());
            }
        }

        return objects.toArray();
    }

}
