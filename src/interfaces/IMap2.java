package interfaces;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public interface IMap2 {
    
    default void fromMap(Map<String, String> map) {
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String value = map.get(field.getName());
            if (value != null) {
                try {
                    Object converted = convertValue(field.getType(), value);
                    field.set(this, converted);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Cannot set field " + field.getName(), e);
                }
            }
        }
    }

    default Map<String, String> toMap() {
        Map<String, String> result = new LinkedHashMap<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                result.put(field.getName(), value != null ? value.toString() : null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot access field " + field.getName(), e);
            }
        }
        return result;
    }

    private Object convertValue(Class<?> type, String value) {
        if (type.equals(String.class)) {
            return value;
        } else if (type.equals(int.class) || type.equals(Integer.class)) {
            return Integer.parseInt(value);
        } else if (type.equals(long.class) || type.equals(Long.class)) {
            return Long.parseLong(value);
        } else if (type.equals(double.class) || type.equals(Double.class)) {
            return Double.parseDouble(value);
        } else if (type.equals(float.class) || type.equals(Float.class)) {
            return Float.parseFloat(value);
        } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            return Boolean.parseBoolean(value);
        } else if (type.equals(BigDecimal.class)) {
            return new BigDecimal(value);
        } else if (type.equals(Date.class)) {
            // sql.Date richiede formato "yyyy-[m]m-[d]d"
            return Date.valueOf(value);
        } else if (type.equals(Time.class)) {
            // sql.Time richiede formato "hh:mm:ss"
            return Time.valueOf(value);
        } else if (type.equals(Timestamp.class)) {
            // sql.Timestamp richiede "yyyy-[m]m-[d]d hh:mm:ss[.f...]"
            return Timestamp.valueOf(value);
        } else {
            // fallback: lascia stringa se non riconosciuto
            return value;
        }
    }
    
}
