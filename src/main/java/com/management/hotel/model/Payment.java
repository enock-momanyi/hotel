package com.management.hotel.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Booking booking;
    private Long amount;
    private Date date;
    private String method;

    public Payment(Booking booking, Long amount, Date date, String method) {
        this.booking = booking;
        this.amount = amount;
        this.date = date;
        this.method = method;
    }

    public Long getId() {
        return id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", bookingId=" + booking.getId() +
                ", amount=" + amount +
                ", date=" + date +
                ", method='" + method + '\'' +
                '}';
    }
}
