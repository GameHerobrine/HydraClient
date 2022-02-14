package com.oldschoolminecraft.client.event.events;

import com.oldschoolminecraft.client.event.Event;
import com.oldschoolminecraft.client.event.Type;

public class EventKeyboard extends Event
{
private int keyCode;
	
	public EventKeyboard(int keyCode)
	{
		super(Type.PRE);
		this.keyCode = keyCode;
	}
	
	public int getKeyCode()
	{
		return keyCode;
	}
}
