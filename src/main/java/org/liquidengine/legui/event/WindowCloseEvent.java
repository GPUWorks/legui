package org.liquidengine.legui.event;

import org.liquidengine.legui.component.Controller;

/**
 * Created by ShchAlexander on 03.02.2017.
 */
public class WindowCloseEvent extends AbstractEvent {

    public WindowCloseEvent(Controller controller) {
        super(controller);
    }

}