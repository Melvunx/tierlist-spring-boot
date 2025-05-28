package com.melvunx.tierlist.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "ranked")
public class Ranked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classement_id")
    private Classement classement;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name =  "updated_at", nullable = false)
    private LocalDate updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDate.now();
    }
}
