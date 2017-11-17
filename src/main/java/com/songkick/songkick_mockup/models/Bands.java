package com.songkick.songkick_mockup.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bands")
public class Bands {
    @Id
    @Column(nullable = false, name = "songkick_id")
    private long id;
    @Column(nullable = false, name = "songkick_bandname")
    private String band;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "songkick_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")}
    )
    private List<User> users;
}

