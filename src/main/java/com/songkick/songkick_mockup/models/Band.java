package com.songkick.songkick_mockup.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bands")
public class Band {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, name = "jambase_id")
    private long jambaseId;
    @Column(nullable = false, name = "jambase_bandname")
    private String bandname;

    @ManyToMany(mappedBy="bands", cascade = CascadeType.ALL)
    private List<User> users;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBandname() {
        return bandname;
    }

    public void setBandname(String bandname) {
        this.bandname = bandname;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public long getJambaseId() {
        return jambaseId;
    }

    public void setJambaseId(long jambaseId) {
        this.jambaseId = jambaseId;
    }
}

