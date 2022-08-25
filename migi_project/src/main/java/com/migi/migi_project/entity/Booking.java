package com.migi.migi_project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "booking")
@Getter
@Setter
public class Booking {
    @Id
    @Column(name = "idbooking")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "service_id")
    private Integer serviceId;

    @Column(name = "service_worker_id")
    private Integer serviceWorkerId;

    @Column(name = "appointment_date")
    private Instant appointmentDate;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "pet_name")
    private String petName;

    @Column(name = "pet_type")
    private String petType;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private Integer status;
}
