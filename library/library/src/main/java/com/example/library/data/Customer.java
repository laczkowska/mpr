package com.example.library.data;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean hasCurrentLoan;

    public Customer(String name) {
        this.name = name;
        this.hasCurrentLoan = false;
    }
}