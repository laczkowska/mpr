package com.example.library.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class CustomerDto {
    private UUID id;
    private String name;
    private boolean hasCurrentLoan;

    // Add getters and setters as needed

    public CustomerDto(UUID id, String name, boolean hasCurrentLoan) {
        this.id = id;
        this.name = name;
        this.hasCurrentLoan = hasCurrentLoan;
    }
}
