package fr.dawan.business.article;

import fr.dawan.business.category.Category;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ArticleController.class, ArticleService.class, ArticleMapper.class})
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ArticleRepository repository;

    @ParameterizedTest
    @MethodSource("findByTitleProvider")
    void findByTitle(Page<Article> result, String title, ResultMatcher statusExpected, ResultMatcher jsonExpected) throws Exception {
        // Arrange
        Pageable pageable = Pageable.ofSize(20);
        String url = "/articles/" + title;

        // Act
        Mockito.when(repository.findByNomLike("%" + title + "%", pageable)).thenReturn(result);

        mockMvc.perform(
            MockMvcRequestBuilders.get(url)
        )
        // Assert
        .andExpect(
                statusExpected
        )
        .andExpect(
                jsonExpected
        );
    }

    private static Stream<Arguments> findByTitleProvider() {
        Article article1 = new Article("Casque Cloudset", 99.99d, null);
        Article article2 = new Article("Casque Mindset", 24.99d, null);
        Article article3 = new Article("Haltère 5kg", 6.99d, null);
        Article article4 = new Article("Le clown du voisin", -5.99d, null);

        return Stream.of(
            Arguments.of(new PageImpl<>(List.of(article1, article2)), "Casque", status().isOk(), jsonPath("$").isNotEmpty()),
            Arguments.of(new PageImpl<>(List.of(article3)), "5kg", status().isOk(), jsonPath("$").isNotEmpty()),
            Arguments.of(new PageImpl<>(List.of(article4)), "clown", status().isOk(), jsonPath("$").isNotEmpty()),
                Arguments.of(new PageImpl<>(List.of(article4)), "je n'existe pas", status().isNotFound(), jsonPath("$").doesNotExist())
        );
    }
}