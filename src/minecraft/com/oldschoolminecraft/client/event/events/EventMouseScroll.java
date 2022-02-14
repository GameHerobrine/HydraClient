package com.oldschoolminecraft.client.event.events;

import com.oldschoolminecraft.client.event.Event;
import com.oldschoolminecraft.client.event.Type;

public class EventMouseScroll extends Event
{
    public int value;
    
    public EventMouseScroll(int value)
    {
        super(Type.PRE);
        this.value = value;
    }
}
