package com.melvunx.tierlist.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rank_list")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RankList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String list, author, theme;

    @Column(nullable = false)
    private LocalDate createdAt, updatedAt;

    @OneToMany(mappedBy = "ranked_list", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Classement> classements;

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
