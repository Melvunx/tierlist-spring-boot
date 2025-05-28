package com.melvunx.tierlist.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tier_list")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TierList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title, author, theme;


    @OneToMany(mappedBy = "tierList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Classement> classements;

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
