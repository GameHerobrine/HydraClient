package com.oldschoolminecraft.client.event;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class EventManager
{
	private HashMap<Class<? extends Event>, CopyOnWriteArrayList<Data>> REGISTRY_MAP;

	public EventManager()
	{
		REGISTRY_MAP = new HashMap<Class<? extends Event>, CopyOnWriteArrayList<Data>>();
	}

	public void register(Object o)
	{
		System.out.println(o.getClass().getName() + " registered as event listener!");
		
		Arrays.stream(o.getClass().getDeclaredMethods()).forEach(new Consumer<Method>()
		{
			@Override
			public void accept(Method method)
			{
				if (!isMethodBad(method))
					register(method, o);
			}
		});

		REGISTRY_MAP.values().forEach(new Consumer<CopyOnWriteArrayList<Data>>()
		{
			@Override
			public void accept(CopyOnWriteArrayList<Data> flexibleArray)
			{
				flexibleArray.sort((new Comparator<Data>()
				{
					@Override
					public int compare(Data o1, Data o2)
					{
						return (o1.getPriority().getValue() - o2.getPriority().getValue());
					}
				}));
			}
		});
	}

	private void register(Method method, Object o)
	{
		@SuppressWarnings("unchecked")
		Class<? extends Event> clazz = (Class<? extends Event>) method.getParameterTypes()[0];
		Data methodData = new Data(o, method, method.getAnnotation(EventTarget.class).priority());
		if (!methodData.getTarget().isAccessible())
			methodData.getTarget().setAccessible(true);
		if (REGISTRY_MAP.containsKey(clazz))
		{
			if (!REGISTRY_MAP.get(clazz).contains(methodData))
				REGISTRY_MAP.get(clazz).add(methodData);
		} else
		{
			REGISTRY_MAP.put(clazz, new CopyOnWriteArrayList<Data>(Collections.singletonList(methodData)));
		}
	}

	public void unregister(Object o)
	{
		REGISTRY_MAP.values().forEach(new Consumer<CopyOnWriteArrayList<Data>>()
		{
			@Override
			public void accept(CopyOnWriteArrayList<Data> flexibleArray)
			{
				flexibleArray.removeIf(new Predicate<Data>()
				{
					@Override
					public boolean test(Data methodData)
					{
						return methodData.getSource().equals(o);
					}
				});
			}
		});
		REGISTRY_MAP.entrySet().removeIf(new Predicate<Entry<Class<? extends Event>, CopyOnWriteArrayList<Data>>>()
		{
			@Override
			public boolean test(Entry<Class<? extends Event>, CopyOnWriteArrayList<Data>> hashSetEntry)
			{
				return hashSetEntry.getValue().isEmpty();
			}
		});
	}

	private boolean isMethodBad(Method method)
	{
		return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventTarget.class);
	}

	public CopyOnWriteArrayList<Data> get(Class<? extends Event> clazz)
	{
		return REGISTRY_MAP.get(clazz);
	}
}