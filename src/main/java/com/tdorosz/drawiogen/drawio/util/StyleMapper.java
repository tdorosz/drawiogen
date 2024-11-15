package com.tdorosz.drawiogen.drawio.util;

import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import com.tdorosz.drawiogen.drawio.element.style.WhiteSpace;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Map;

@Slf4j
public class StyleMapper {

    public static <T> T mapStyleToObject(String style, T targetObject) {
        Map<String, String> styleMap = StyleParser.parse(style);
        Field[] fields = targetObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            String key = field.getName();

            if (styleMap.containsKey(key)) {
                String value = styleMap.get(key);
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                try {
                    if (fieldType == BinaryState.class) {
                        field.set(targetObject, BinaryState.fromValue(value));
                    } else if (fieldType == DrawioColor.class) {
                        field.set(targetObject, DrawioColor.fromColor(java.awt.Color.decode(value)));
                    } else if (fieldType == WhiteSpace.class) {
                        field.set(targetObject, WhiteSpace.fromValue(value));
                    } else {
                        log.warn("Unsupported field type: {}", fieldType);
                    }
                } catch (IllegalAccessException e) {
                    log.error("Unsupported field type: {}", fieldType, e);
                    throw new RuntimeException(e);
                }
            }

        }
        return targetObject;
    }

}