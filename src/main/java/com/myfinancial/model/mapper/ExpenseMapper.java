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

    @Mapping(target = "expenseType", source = "expenseRequest.expenseType", qualifiedByName = "expenseTypeMapper")
    @Mapping(target = "customer", source = "customer")
    @Mapping(target = "id", source = "expenseRequest.id")
    Expense to(final ExpenseRequest expenseRequest, final Customer customer);

    @Mapping(target = "expenseType", source = "expenseType", qualifiedByName = "expenseTypeMapper")
    void toUpdate(@MappingTarget Expense expense, final ExpenseRequest expenseRequest);

    @Mapping(target = "expenseType", source = "expenseType.name")
    ExpenseResponse toReponse(final Expense expense);

    List<ExpenseResponse> toResponseList(final List<Expense> expenseList);


    @Named("expenseTypeMapper")
    default ExpenseType expenseTypeMapper(final String expenseType) {
        return ExpenseType.toEnum(expenseType);
    }
}
