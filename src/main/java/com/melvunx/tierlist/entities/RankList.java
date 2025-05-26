package com.melvunx.tierlist.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rank_list")
@AllArgsConstructor
public class RankList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;
    @Getter
    @Setter
    private String list, author, theme, updatedAt;
    @Getter
    private int classementId;
    @Getter
    private String createdAt;

    public RankList() {}

    public RankList(int id, int classementId, String theme, String author, String list) {
        this.id = id;
        this.classementId = classementId;
        this.theme = theme;
        this.author = author;
        this.list = list;
    }
}
