package com.melvunx.tierlist.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Entity
@Table(name = "ranked")
public class Ranked {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;
    @Getter
    @Setter
    private int name;
    @Getter
    @Setter
    private int image;
    @Getter
    private String createdAt;
    @Getter
    @Setter
    private String updatedAt;

    public Ranked() {}

    public Ranked(int id, int image, int name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }
}
