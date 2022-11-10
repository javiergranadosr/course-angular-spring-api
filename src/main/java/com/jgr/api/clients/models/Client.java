package com.jgr.api.clients.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "clients")
@Data
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surnames;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    private static final long serialVersionUID = 1L;
}
