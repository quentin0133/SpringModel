package fr.dawan.business.article;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ArticleController.class, ArticleService.class, ArticleMapper.class})
@ActiveProfiles("test")
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ArticleRepository repository;

    private static Stream<Arguments> findByTitleProvider() {
        Article article1 = new Article("Casque Cloudset", 99.99d, null);
        Article article2 = new Article("Casque Mindset", 24.99d, null);
        Article article3 = new Article("Haltère 5kg", 6.99d, null);
        Article article4 = new Article("Le clown du voisin", -5.99d, null);

        Pageable pageable = PageRequest.of(0, 20);

        return Stream.of(
            Arguments.of(new PageImpl<>(List.of(article1, article2), pageable, 2),
                    "Casque", pageable, status().isOk()),
            Arguments.of(new PageImpl<>(List.of(article3), pageable, 1),
                    "5kg", pageable, status().isOk()),
            Arguments.of(new PageImpl<>(List.of(article4), pageable, 1),
                    "clown", pageable, status().isOk()),
            Arguments.of(null, "je n'existe pas", pageable, status().isNotFound())
        );
    }

    @ParameterizedTest
    @MethodSource("findByTitleProvider")
    void findByTitle(Page<Article> result, String title, Pageable pageable, ResultMatcher statusExpected) throws Exception {
        // Arrange
        String url = "/articles/" + title;

        // Act
        Mockito.when(repository.findByNomLike("%" + title + "%", pageable)).thenReturn(result);

        if (result == null) {
            mockMvc.perform(
                MockMvcRequestBuilders.get(url)
            )
            // Assert
            .andExpect(
                statusExpected
            )
            .andExpect(
                jsonPath("$").doesNotExist()
            );
        }
        else {
            mockMvc.perform(
                MockMvcRequestBuilders.get(url)
            )
            // Assert
            .andExpect(
                statusExpected
            )
            .andExpect(
                jsonPath("$").isNotEmpty()
            )
            .andExpect(
                jsonPath("$.numberOfElements").value(result.getContent().size())
            );
        }
    }
}