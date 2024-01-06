package cz.uhk.boardsappjakarta.persistence.entity;

import cz.uhk.boardsappjakarta.persistence.entity.model.AuthoritiesId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Data
@Entity(name="Authorities")
@IdClass(AuthoritiesId.class)
public class Authority {

    @Id
    @Column(length = 50)
    private String username;

    @Id
    @Column(name="authority", length = 50)
    private String authorityName;
}
