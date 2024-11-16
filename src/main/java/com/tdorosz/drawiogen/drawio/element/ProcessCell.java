package com.tdorosz.drawiogen.drawio.element;

import com.tdorosz.drawiogen.drawio.element.style.BinaryState;
import com.tdorosz.drawiogen.drawio.element.style.DrawioColor;
import com.tdorosz.drawiogen.drawio.element.style.WhiteSpace;
import com.tdorosz.drawiogen.drawio.util.DrawioStyleToStringStyle;
import com.tdorosz.drawiogen.drawio.xmlschema.MxCell;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.UUID;

import static com.tdorosz.drawiogen.drawio.util.StyleMapper.mapStyleToObject;

public class ProcessCell extends MxCellBasedShape<ProcessCell> {

    private static final String DEFAULT_STYLE = new Style()
            .html(BinaryState.ON)
            .rounded(BinaryState.OFF)
            .whiteSpace(WhiteSpace.WRAP)
            .toStyleString();

    public static ProcessCell createNew() {
        MxCell mxCell = new MxCell()
                .id(UUID.randomUUID().toString())
                .vertex("1")
                .style(DEFAULT_STYLE)
                .mxGeometry(createDefaultGeometry());

        return new ProcessCell(mxCell);
    }

    public static ProcessCell from(MxCell mxCell) {
        return new ProcessCell(mxCell);
    }

    private ProcessCell(MxCell mxCell) {
        super(mxCell);
    }

    public Style styleEditBegin() {
        return new Style(this, mxCell.style());
    }

    @Data
    @Accessors(fluent = true, chain = true)
    public static class Style {
        @ToStringExclude
        private final ProcessCell parent;

        private String shape = "process";
        private BinaryState editable;
        private BinaryState connectable;
        private BinaryState sketch;
        private BinaryState rounded;
        private BinaryState glass;
        private BinaryState shadow;
        private BinaryState collapsible;
        private BinaryState html;
        private WhiteSpace whiteSpace;
        private DrawioColor fillColor;
        private DrawioColor strokeColor;

        public Style() {
            this("");
        }

        public Style(String style) {
            this(null, style);
        }

        public Style(ProcessCell parent, String style) {
            this.parent = parent;
            mapStyleToObject(style, this);
        }

        public String toStyleString() {
            return ReflectionToStringBuilder
                    .toString(this, DrawioStyleToStringStyle.getInstance());
        }

        public ProcessCell styleEditCommit() {
            return parent.style(toStyleString());
        }
    }
}
