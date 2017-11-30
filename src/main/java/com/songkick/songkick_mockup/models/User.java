package com.songkick.songkick_mockup.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    //    @NotBlank(message = "username can't be blank")
    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String firstName;

    @Column
    private String lastName;

    //    @NotBlank(message = "email can't be blank")
    @Column(nullable = false, unique = true)
    @Email
    private String email;

    //    @NotBlank(message = "password can't be blank")
//    @Size(min = 8, message ="password must be at least 8 characters long")
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    //    @NotBlank(message = "city can't be blank")
    @Column(nullable = false)
    private String city;

    //    @NotBlank(message = "state can't be blank")
    @Column(nullable = false)
    private String state;

    //    @NotBlank(message = "zipcode can't be blank")
    @Column(nullable = false)
    private Long zipcode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @JsonBackReference
    private List<Review> reviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "bands_users",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "jambase_id")}
    )
    private List<Band> bands;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "show_id")}
    )
    private List<Show> shows;

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public List<FriendRequest> getSenders() {
        return senders;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sender")
    @JsonBackReference
    private List<FriendRequest> senders;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver")
    @JsonBackReference
    private List<FriendRequest> receivers;

    public User() {
    }

    public User(User copy) {
        id = copy.id;
        username = copy.username;
        firstName = copy.firstName;
        lastName = copy.lastName;
        email = copy.email;
        password = copy.password;
        city = copy.city;
        state = copy.state;
        zipcode = copy.zipcode;
        reviews = copy.reviews;
        bands = copy.bands;
        shows = copy.shows;
        senders = copy.senders;
        receivers = copy.receivers;


    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Band> getBands() {
        return bands;
    }

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }

    public List<FriendRequest> getSendes() {
        return senders;
    }

    public void setSenders(List<FriendRequest> senders) {
        this.senders = senders;
    }

    public List<FriendRequest> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<FriendRequest> receivers) {
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

    public Long getZipcode() {
        return zipcode;
    }

    public void setZipcode(Long zipcode) {
        this.zipcode = zipcode;
    }
}
