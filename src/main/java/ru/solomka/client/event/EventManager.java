package ru.solomka.client.event;

import ru.solomka.client.event.impl.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventManager {

    private static final List<Event> EVENTS = new ArrayList<>();

    public static void initEvents(Event ...events) {
        EVENTS.addAll(Arrays.stream(events).toList());
    }

//    public Event findById(String id) {
//       return EVENTS.stream().filter(s -> s.getId().equals(id)).findAny().orElse(null);
//    }
}