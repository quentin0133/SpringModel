package fr.dawan.business.article;

import fr.dawan.business.generic.GenericController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController extends GenericController<ArticleDto, ArticleService> {
    public ArticleController(ArticleService service) {
        super(service);
    }

    @GetMapping("/{title:[A-Za-z1-9]*[A-Za-z].[A-Za-z1-9]*}")
    public List<ArticleDto> findByNom(@PathVariable String title, Pageable pageable) {
        return service.findByNom(title, pageable).getContent();
    }

    @GetMapping("/byCategorie/{name:[A-Za-z1-9]*[A-Za-z].[A-Za-z1-9]*}")
    public Page<Article> findByCategoryName(@PathVariable String name, Pageable pageable) {
        return service.findByCategoryNom(name, pageable);
    }
}
