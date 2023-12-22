package fr.dawan.business.category;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    private CategoryServiceImpl service;

    @Mock
    private CategoryMapper mapper;
    @Mock
    private CategoryRepository repository;

    @BeforeEach
    void init() {
        service = new CategoryServiceImpl(repository, mapper);
    }

    @Test
    void findAllTest() {
        // Arrange
        Pageable pageable = Pageable.unpaged();
        Category category1 = new Category("Sport", null);
        Category category2 = new Category("Technologie", null);
        Page<Category> categoryList = new PageImpl<>(List.of(category1, category2));

        CategoryDto categoryDto1 = new CategoryDto(0L, 0, category1.getName(), null, 0);
        CategoryDto categoryDto2 = new CategoryDto(0L, 0, category2.getName(),null, 0);
        List<CategoryDto> expected = List.of(categoryDto1, categoryDto2);

        Mockito.when(repository.findAll(pageable)).thenReturn(categoryList);
        Mockito.when(mapper.toDto(any(Category.class))).thenAnswer(invocation -> {
            Category a = invocation.getArgument(0);
            return new CategoryDto(0L, 0, a.getName(), null, 0);
        });

        // Act
        Page<CategoryDto> result = service.findAll(pageable);

        // Asset
        assertTrue(result.getContent().containsAll(expected));
    }
}