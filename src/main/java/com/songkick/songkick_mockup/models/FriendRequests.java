package com.songkick.songkick_mockup.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "friendrequests")
public class FriendRequests {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private boolean approval;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    @JsonManagedReference
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @JsonManagedReference
    private User receiver;

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReciever() {
        return receiver;
    }

    public void setReciever(User receiver) {
        this.receiver = receiver;
    }
}
