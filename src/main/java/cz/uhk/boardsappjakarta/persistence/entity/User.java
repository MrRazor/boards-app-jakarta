package cz.uhk.boardsappjakarta.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name="Users")
public class User {

    @Id
    @Column(length = 50)
    private String username;

    @Column(length = 500)
    private String password;

    @Column(insertable = false)
    private boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Authority.class)
    @JoinColumn(name="username")
    private List<Authority> authorities;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Post> posts;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Comment> comments;
}