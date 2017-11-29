package com.songkick.songkick_mockup.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shows")
public class Show {
    @Id
    @Column
    private long show_id;

    @Column(name = "artists")
    private String artists;

    @Column(name = "venue")
    private String venue;

    @ManyToMany(mappedBy="shows", cascade = CascadeType.ALL)
    private List<User> users;

    public long getId() {
        return show_id;
    }

    public void setId(long show_id) {
        this.show_id = show_id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getArtists() { return artists; }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getVenue() { return venue; }

    public void setVenue(String venue) { this.venue = venue; }
}
