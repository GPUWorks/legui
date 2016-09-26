package org.liquidengine.legui.render.nvg.component;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.component.Component;
import org.liquidengine.legui.component.optional.TextState;
import org.liquidengine.legui.component.border.Border;
import org.liquidengine.legui.context.LeguiContext;
import org.liquidengine.legui.render.nvg.NvgLeguiComponentRenderer;
import org.liquidengine.legui.util.Util;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGPaint;

import static org.liquidengine.legui.util.NVGUtils.rgba;
import static org.liquidengine.legui.util.NvgRenderUtils.*;
import static org.lwjgl.nanovg.NanoVG.*;

/**
 * Created by Shcherbin Alexander on 9/23/2016.
 */
public class NvgButtonRenderer extends NvgLeguiComponentRenderer {
    private NVGColor colorA = NVGColor.calloc();
    private NVGColor colorB = NVGColor.calloc();
    private NVGPaint paintA = NVGPaint.calloc();

    @Override
    public void render(Component component, LeguiContext context, long nvgContext) {
        createScissor(nvgContext, component);
        {
            Button agui = (Button) component;
            Vector2f pos = Util.calculatePosition(component);
            Vector2f size = component.getSize();

            Border border = agui.getBorder();

            nvgSave(nvgContext);
            // render background
            {
                Vector4f backgroundColor = component.getBackgroundColor();
                nvgBeginPath(nvgContext);
                nvgFillColor(nvgContext, rgba(backgroundColor, colorA));
                nvgRoundedRect(nvgContext, pos.x, pos.y, size.x, size.y, component.getCornerRadius());
                nvgFill(nvgContext);

                nvgLinearGradient(nvgContext, pos.x, pos.y, pos.x, pos.y + size.y, rgba(1f, 1f, 1f, 0.2f, colorA), rgba(0f, 0f, 0f, 0.2f, colorB), paintA);
                nvgFillPaint(nvgContext, paintA);
                nvgFill(nvgContext);
            }

            // Render text
            {
                TextState textState = agui.getTextState();
                renderTextStateToBounds(nvgContext, pos, size, textState);
            }

            // Render border
            if (border != null) {
                border.render(context);
            }

            nvgRestore(nvgContext);

        }
        resetScissor(nvgContext);
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}