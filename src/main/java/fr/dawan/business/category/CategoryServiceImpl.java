package fr.dawan.business.category;

import fr.dawan.business.generic.GenericServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends GenericServiceImpl<Category, CategoryRepository, CategoryDto, CategoryMapper> implements CategoryService {
    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper mapper) {
        super(repository, mapper);
    }
}
