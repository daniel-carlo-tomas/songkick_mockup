package com.songkick.songkick_mockup.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @Column
    private long show_id;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = {@JoinColumn(name = "show_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")}
    )
    private List<User> users;

    public long getShow_id() {
        return show_id;
    }

    public void setShow_id(long show_id) {
        this.show_id = show_id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
