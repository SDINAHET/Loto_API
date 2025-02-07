// filepath: /c:/Users/steph/Documents/portfolio/portfolio/Loto_API/src/main/java/com/fdjloto/api/model/Ticket.java
package com.fdjloto.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "numbers")
    private String numbers;

    @Column(name = "draw_date")
    private String drawDate;

    @Column(name = "draw_day")
    private String drawDay;
}
