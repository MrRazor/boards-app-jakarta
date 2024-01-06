package cz.uhk.boardsappjakarta.persistence.entity.model;

import cz.uhk.boardsappjakarta.persistence.entity.User;

import java.sql.Timestamp;

public interface UserContent {
    Long getId();

    void setId(Long id);

    User getAuthor();

    void setAuthor(User author);

    String getContent();

    void setContent(String content);

    boolean isRemoved();

    void setRemoved(boolean removed);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    Timestamp getUpdatedAt();

    void setUpdatedAt(Timestamp updatedAt);
}
