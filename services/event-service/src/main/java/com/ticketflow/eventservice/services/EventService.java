package com.ticketflow.eventservice.services;

import com.ticketflow.eventservice.entities.Event;
import com.ticketflow.eventservice.repos.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository repo;

    public EventService(EventRepository repo) {
        this.repo = repo;
    }

    public List<Event> getAll() {
        return repo.findAll();
    }

    public Optional<Event> getById(Long id) {
        return repo.findById(id);
    }

    public Event create(Event event) {
        return repo.save(event);
    }

    public Optional<Event> update(Long id, Event eventDetails) {
        return repo.findById(id).map(event -> {
            event.setTitle(eventDetails.getTitle());
            event.setDescription(eventDetails.getDescription());
            event.setVenue(eventDetails.getVenue());
            event.setDate(eventDetails.getDate());
            event.setCapacity(eventDetails.getCapacity());
            event.setPrice(eventDetails.getPrice());
            return repo.save(event);
        });
    }

    public boolean delete(Long id) {
        if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
    }
}