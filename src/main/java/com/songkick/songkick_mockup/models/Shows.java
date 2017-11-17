package com.songkick.songkick_mockup.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shows")
public class Shows {
    @Id
    @Column
    private long show_id;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "show_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")}
    )
    private List<User> users;
}
