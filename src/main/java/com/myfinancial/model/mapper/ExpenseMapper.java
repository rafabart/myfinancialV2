package com.myfinancial.model.mapper;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.entity.Expense;
import com.myfinancial.model.domain.enums.ExpenseType;
import com.myfinancial.model.domain.request.ExpenseRequest;
import com.myfinancial.model.domain.response.ExpenseResponse;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CategoryMapper.class})
public interface ExpenseMapper {


    @Mapping(target = "expenseType", ignore = true)
    @Mapping(source = "customer", target = "customer")
    @Mapping(source = "expenseRequest.id", target = "id")
    Expense to(final ExpenseRequest expenseRequest, final Customer customer);

    @Mapping(target = "expenseType", ignore = true)
    void toUpdate(@MappingTarget Expense expense, final ExpenseRequest expenseRequest);

    @Mapping(source = "expenseType.name", target = "expenseType")
    ExpenseResponse toReponse(final Expense expense);

    List<ExpenseResponse> toResponseList(final List<Expense> expenseList);


    @AfterMapping
    default void setExpenseType(@MappingTarget Expense expense, final ExpenseRequest expenseRequest) {
        expense.setExpenseType(ExpenseType.toEnum(expenseRequest.getExpenseType()));
    }
}
