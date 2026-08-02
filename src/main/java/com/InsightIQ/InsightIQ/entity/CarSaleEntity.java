package com.InsightIQ.InsightIQ.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name="Product_sales")
public class CarSaleEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id ;

    @Column ( name = "car_number" , unique = true , nullable = false )
    private String carNumber;

    private String carBrand;
    private String carModel;
    private String carColor;
    private int Year;

    @Column(name = "time_of_purchase")
    private LocalTime timeOfPurchase;
    @Column(name = "date_of_purchase")
    private LocalDate timeOfPurchaseDate;

    @Column(name = "payment_mode")
    private long price;
    private double mileage;;
    private int engine;
    private String fuelType;

    @Column(name = "contact_number")
    private String paymentMode;

    private String state;
    private String city;
    private String country;
    private String customerName;
    private String customerPhoneNumber;
    private String customerEmail;

    @Column(name = "warranty_period")
    private String warrantyPeriod;
}
