package com.fdjloto.api.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id généré automatiquement par la DB
    @Column(name = "id")
    private long id;

    @Column(nullable = false, unique = true, name = "username") // username ne peut pas être null et doit être unique
    private String username;

    @Column(nullable = false, unique = true, name = "email") // email obligatoire et unique
    private String email;

    @JsonIgnore // Empêche le mot de passe de s'afficher dans les réponses API
    @Column(nullable = false, name = "password") // Mot de passe obligatoire
    private String password;

    // @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonIgnore
    // private List<Score> scores;

    // @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonManagedReference // Gère la référence pour éviter les boucles infinies
    // private Settings settings;
}
