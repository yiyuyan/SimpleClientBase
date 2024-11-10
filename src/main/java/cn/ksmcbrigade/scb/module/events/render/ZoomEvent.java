package cn.ksmcbrigade.scb.module.events.render;

import net.neoforged.bus.api.Event;

public class ZoomEvent extends Event {
    public double foV;

    public ZoomEvent(double foV){
        this.foV = foV;
    }
}
