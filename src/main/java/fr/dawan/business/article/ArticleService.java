package fr.dawan.business.article;

import fr.dawan.business.generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ArticleService extends GenericService<ArticleDto> {
    Page<ArticleDto> findByNom(String title, Pageable page);
    Page<Article> findByCategoryNom(String name, Pageable page);
}