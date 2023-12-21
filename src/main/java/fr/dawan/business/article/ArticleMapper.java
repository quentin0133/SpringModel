package fr.dawan.business.article;

import fr.dawan.business.generic.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ArticleMapper extends GenericMapper<Article, ArticleDto> {
}
