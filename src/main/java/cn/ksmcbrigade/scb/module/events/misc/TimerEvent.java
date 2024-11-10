package cn.ksmcbrigade.scb.module.events.misc;

import net.neoforged.bus.api.Event;

public class TimerEvent extends Event {
    public float tickTargetMillis;

    public TimerEvent(float timer){
        this.tickTargetMillis = timer;
    }
}
