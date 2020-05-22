package com.myfinancial.model.mapper;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.entity.Expense;
import com.myfinancial.model.domain.request.ExpenseRequest;
import com.myfinancial.model.domain.response.ExpenseResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CategoryMapper.class})
public interface ExpenseMapper {


    @Mapping(source = "expenseRequest.id", target = "id")
    @Mapping(source = "customer", target = "customer")
    Expense to(final ExpenseRequest expenseRequest, final Customer customer);

    void toUpdate(@MappingTarget Expense expense, final ExpenseRequest expenseRequest);

    ExpenseResponse toReponse(final Expense expense);

    List<ExpenseResponse> toResponseList(final List<Expense> expenseList);
}
