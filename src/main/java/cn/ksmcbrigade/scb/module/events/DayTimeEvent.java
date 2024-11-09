package cn.ksmcbrigade.scb.module.events;

import net.neoforged.bus.api.Event;

public class DayTimeEvent extends Event {
    public long time;

    public DayTimeEvent(long time){
        this.time = time;
    }
}
