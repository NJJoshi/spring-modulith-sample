package com.javatechie.smartparking_system.event;

import java.time.LocalDateTime;

public record VehicleEnteredEvent(String vehicleNumber,
                                  LocalDateTime entryTime) {
}
