package com.melvunx.tierlist.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "classement")
@AllArgsConstructor
public class Classement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;
    @Getter
    @Setter
    private String title, updatedAt;
    @Getter
    private int rankedId;
    @Getter
    private String createdAt;

    public Classement() {}

    public Classement(int id, String title, int rankedId) {
        this.id = id;
        this.title = title;
        this.rankedId = rankedId;
    }
}
