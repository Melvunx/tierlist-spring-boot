package com.melvunx.tierlist.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "classement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;


    //Un classement peut avoir plusieurs ranked | On peut fetch les ranked seulement si l'on veut
    @OneToMany(mappedBy = "classement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ranked> rankedItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tier_list_id")
    private TierList tierList;

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
