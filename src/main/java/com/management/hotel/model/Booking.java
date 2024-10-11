package com.management.hotel.model;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id", referencedColumnName = "id",nullable = false)
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="room_id", referencedColumnName = "id",nullable = false)
    private Room room;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="plan_id", referencedColumnName = "id",nullable = false)
    private Plan plan;
    private Date checkinDate;
    private Date checkoutDate;
    private Long amount;
    private String status;
    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Payment payment;

    public Booking() {
    }

    public Booking(Customer customer, Room room, Plan plan, Date checkinDate, Date checkoutDate, Long amount, String status) {
        this.customer = customer;
        this.room = room;
        this.plan = plan;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customerId=" + customer.getId() +
                ", roomId=" + room.getId() +
                ", planId=" + plan.getId() +
                ", checkinDate=" + checkinDate +
                ", checkoutDate=" + checkoutDate +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
