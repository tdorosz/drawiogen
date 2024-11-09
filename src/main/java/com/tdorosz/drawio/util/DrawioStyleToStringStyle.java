package com.tdorosz.drawio.util;

import org.apache.commons.lang3.builder.ToStringStyle;

public class DrawioStyleToStringStyle extends ToStringStyle {

    public static final DrawioStyleToStringStyle INSTANCE = new DrawioStyleToStringStyle();

    public DrawioStyleToStringStyle() {
        this.setUseClassName(false);
        this.setUseIdentityHashCode(false);
        this.setContentStart("");
        this.setContentEnd(";");
        this.setArrayStart("[");
        this.setArrayEnd("]");
        this.setFieldSeparator(";");
        this.setFieldNameValueSeparator("=");
        this.setNullText("");
    }

    @Override
    public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {
        if (value == null) {
            return;
        }

        this.appendFieldStart(buffer, fieldName);
        this.appendInternal(buffer, fieldName, value, this.isFullDetail(fullDetail));
        this.appendFieldEnd(buffer, fieldName);
    }


    public static DrawioStyleToStringStyle getInstance() {
        return INSTANCE;
    }
}
