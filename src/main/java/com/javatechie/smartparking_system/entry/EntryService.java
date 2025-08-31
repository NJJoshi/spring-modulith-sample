package com.javatechie.smartparking_system.entry;

import com.javatechie.smartparking_system.allocation.SlotAllocationService;
import com.javatechie.smartparking_system.event.VehicleEnteredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntryService {

    // save vehicle entry details to DB
    // allocate a parking slot
    // send notification to the user

    private final ParkingEntryRepository repository;
    private final ApplicationEventPublisher publisher;

    private SlotAllocationService slotAllocationService;

    public EntryService(ParkingEntryRepository repository,
                        ApplicationEventPublisher publisher,
                        SlotAllocationService slotAllocationService) {
        this.repository = repository;
        this.publisher = publisher;
        this.slotAllocationService = slotAllocationService;
    }


    public void vehicleEntry(String vehicleNumber) {
        if(slotAllocationService.getNextAvailableSlot() == null) {
            throw new RuntimeException("No slot available");
        }
        ParkingEntry parkingEntry = new ParkingEntry(null, vehicleNumber, LocalDateTime.now(), null, true);
        repository.save(parkingEntry);
        // publish an event
        publisher.publishEvent(new VehicleEnteredEvent(vehicleNumber, parkingEntry.getEntryTime()));

    }
}
