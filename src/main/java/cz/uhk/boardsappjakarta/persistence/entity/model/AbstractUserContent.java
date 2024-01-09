package cz.uhk.boardsappjakarta.persistence.entity.model;

import cz.uhk.boardsappjakarta.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@MappedSuperclass
public abstract class AbstractUserContent implements UserContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;

    private String content;

    @Column(insertable = false)
    private boolean removed;

    @Column(name="created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name="updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;
}
