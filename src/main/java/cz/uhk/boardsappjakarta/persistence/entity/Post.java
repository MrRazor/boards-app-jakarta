package cz.uhk.boardsappjakarta.persistence.entity;

import cz.uhk.boardsappjakarta.persistence.entity.model.AbstractUserContent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name="Posts")
public class Post extends AbstractUserContent {

    @Column(length = 255)
    private String title;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments;
}