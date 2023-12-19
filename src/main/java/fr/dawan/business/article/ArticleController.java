package fr.dawan.business.article;

import fr.dawan.business.generic.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("articles")
public class ArticleController extends GenericController<Article, ArticleService> {
    public ArticleController(ArticleService service) {
        super(service);
    }
}
