package com.myfinancial.model.mapper;

import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.enums.ExpenseType;
import com.myfinancial.model.domain.request.CategoryRequest;
import com.myfinancial.model.domain.response.CategoryResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(source = "categoryRequest.id", target = "id")
    @Mapping(source = "categoryRequest.name", target = "name")
    @Mapping(source = "customer", target = "customer")
    Category to(final CategoryRequest categoryRequest, final Customer customer);

    void toUpdate(@MappingTarget Category category, final CategoryRequest categoryRequest);

    @ValueMapping(source = "expenseType", target = "expenseType.getName()")
    CategoryResponse toReponse(final Category category);

    List<CategoryResponse> toResponseList(final List<Category> categoryList);
}
