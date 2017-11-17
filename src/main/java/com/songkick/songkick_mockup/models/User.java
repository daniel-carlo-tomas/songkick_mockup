package com.songkick.songkick_mockup.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    private List<Reviews> reviews;
    @ManyToMany(mappedBy="users", cascade = CascadeType.ALL)
    private List<Bands> bands;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    @JsonBackReference
    private List<FriendRequests> senders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    @JsonBackReference
    private List<FriendRequests> receivers;

    public User () {}

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public List<Bands> getBands() {
        return bands;
    }

    public void setBands(List<Bands> bands) {
        this.bands = bands;
    }

    public List<FriendRequests> getSendes() {
        return senders;
    }

    public void setSenders(List<FriendRequests> senders) {
        this.senders = senders;
    }

    public List<FriendRequests> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<FriendRequests> receivers) {
        this.receivers = receivers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
