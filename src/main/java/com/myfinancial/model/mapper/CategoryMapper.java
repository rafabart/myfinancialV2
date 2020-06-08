package com.myfinancial.model.mapper;

import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.request.CategoryRequest;
import com.myfinancial.model.domain.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "categoryRequest.id", target = "id")
    @Mapping(source = "categoryRequest.name", target = "name")
    Category to(final CategoryRequest categoryRequest, final Customer customer);

    void toUpdate(@MappingTarget Category category, final CategoryRequest categoryRequest);

    CategoryResponse toReponse(final Category category);
}
