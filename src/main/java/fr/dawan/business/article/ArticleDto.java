package fr.dawan.business.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private long id;
    private int version;
    private String nom;
    private double prix;
    /*private String categoryName;
    private long idCategory;*/
    private ArticleCategoryDto category;
}
