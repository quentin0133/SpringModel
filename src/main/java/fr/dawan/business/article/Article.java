package fr.dawan.business.article;

import fr.dawan.business.generic.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articles")
public class Article extends BaseEntity {
    public String nom;
    public double prix;
}
