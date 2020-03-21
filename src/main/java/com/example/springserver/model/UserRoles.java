package com.example.springserver.model;

import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "userRoles")
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}
