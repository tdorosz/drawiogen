package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import com.tdorosz.drawiogen.drawio.util.DrawioStyleToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.awt.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


public abstract class BaseStyle<T extends Stylable<T>, S extends BaseStyle<T, S>> {

    @ToStringExclude
    protected T parent;

    protected BinaryState html;
    private BinaryState editable;
    private BinaryState movable;
    private DrawioColor fillColor;
    private DrawioColor strokeColor;
    private BinaryState sketch;
    private BinaryState shadow;
    private BinaryState enumerate;
    private String enumerateValue;

    public String toStyleString() {
        return ReflectionToStringBuilder
                .toString(this, DrawioStyleToStringStyle.getInstance());
    }

    public T styleEditCommit() {
        return parent.style(toStyleString());
    }

    public BinaryState html() {
        return html;
    }

    @SuppressWarnings("unchecked")
    public S html(BinaryState html) {
        this.html = html;
        return (S) this;
    }

    public BinaryState editable() {
        return editable;
    }

    @SuppressWarnings("unchecked")
    public S editable(BinaryState editable) {
        this.editable = editable;
        return (S) this;
    }

    public BinaryState movable() {
        return movable;
    }

    @SuppressWarnings("unchecked")
    public S movable(BinaryState movable) {
        this.movable = movable;
        return (S) this;
    }

    public BinaryState sketch() {
        return sketch;
    }

    @SuppressWarnings("unchecked")
    public S sketch(BinaryState sketch) {
        this.sketch = sketch;
        return (S) this;
    }

    public BinaryState shadow() {
        return shadow;
    }

    @SuppressWarnings("unchecked")
    public S shadow(BinaryState shadow) {
        this.shadow = shadow;
        return (S) this;
    }

    public BinaryState enumerate() {
        return enumerate;
    }

    @SuppressWarnings("unchecked")
    public S enumerate(BinaryState enumerate) {
        this.enumerate = enumerate;
        return (S) this;
    }

    public String enumerateValue() {
        if (enumerateValue == null) {
            return null;
        }

        return URLDecoder.decode(enumerateValue, StandardCharsets.UTF_8);
    }

    @SuppressWarnings("unchecked")
    public S enumerateValue(String enumerateValue) {
        this.enumerateValue = URLEncoder.encode(enumerateValue, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");
        return (S) this;
    }

    public DrawioColor fillColor() {
        return fillColor;
    }

    @SuppressWarnings("unchecked")
    public S fillColor(DrawioColor fillColor) {
        this.fillColor = fillColor;
        return (S) this;
    }

    @SuppressWarnings("unchecked")
    public S fillColor(Color color) {
        this.fillColor = new DrawioColor(color);
        return (S) this;
    }

    public DrawioColor strokeColor() {
        return strokeColor;
    }

    @SuppressWarnings("unchecked")
    public S strokeColor(DrawioColor strokeColor) {
        this.strokeColor = strokeColor;
        return (S) this;
    }

    @SuppressWarnings("unchecked")
    public S strokeColor(Color color) {
        this.strokeColor = new DrawioColor(color);
        return (S) this;
    }
}
