package com.myfinancial.model.domain.response;

import com.myfinancial.model.domain.entity.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryResponse {

    private Long id;

    private String name;


    public CategoryResponse(final Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
