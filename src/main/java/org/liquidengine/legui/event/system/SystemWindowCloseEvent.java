package org.liquidengine.legui.event.system;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by Shcherbin Alexander on 6/10/2016.
 */
public class SystemWindowCloseEvent implements LeguiSystemEvent {
    public final long window;

    public SystemWindowCloseEvent(long window) {
        this.window = window;
    }

    public long getWindow() {
        return window;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("window", window)
                .toString();
    }
}