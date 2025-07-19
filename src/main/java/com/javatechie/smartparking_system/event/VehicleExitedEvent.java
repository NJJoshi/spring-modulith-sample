package com.javatechie.smartparking_system.event;

import java.time.LocalDateTime;

public record VehicleExitedEvent(String vehicleNumber,
                                 LocalDateTime entryTime,LocalDateTime exitTime) {
}
