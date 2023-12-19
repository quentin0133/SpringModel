package fr.dawan.business.article;

import fr.dawan.business.generic.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ArticleServiceImpl extends GenericServiceImpl<Article, ArticleRepository> {
    public ArticleServiceImpl(ArticleRepository repository) {
        super(repository);
    }
}
