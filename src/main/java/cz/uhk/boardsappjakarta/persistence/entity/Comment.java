package cz.uhk.boardsappjakarta.persistence.entity;

import cz.uhk.boardsappjakarta.persistence.entity.model.AbstractUserContent;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name="Comments")
public class Comment extends AbstractUserContent {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
