package fr.dawan.business.article;

import fr.dawan.business.category.Category;
import fr.dawan.business.generic.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "articles")
public class Article extends BaseEntity implements Serializable {
    private String nom;
    private double prix;
    @ManyToOne
    private Category category;
}
