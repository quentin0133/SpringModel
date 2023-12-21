package fr.dawan.business.article;

import fr.dawan.business.generic.GenericServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends GenericServiceImpl<Article, ArticleRepository, ArticleDto, ArticleMapper> implements ArticleService {
    public ArticleServiceImpl(ArticleRepository repository, ArticleMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Page<ArticleDto> findByNom(String title, Pageable page) {
        Page<Article> byNomLike = repository.findByNomLike("%" + title + "%", page);
        return byNomLike.map(mapper::toDto);
    }

    @Override
    public Page<Article> findByCategoryNom(String name, Pageable page) {
        return repository.findByCategory_NameLike("%" + name + "%", page);
    }
}
