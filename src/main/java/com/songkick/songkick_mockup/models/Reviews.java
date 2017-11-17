package com.songkick.songkick_mockup.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String body;
    @Column(nullable = false)
    private int rating;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    public Reviews () {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
