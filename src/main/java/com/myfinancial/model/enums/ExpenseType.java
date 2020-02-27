package com.myfinancial.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpenseType {

    EXPENSE(1, "Despesa"),
    INCOME(2, "Receita");


    private int cod;
    private String name;


    public static ExpenseType toEnum(final Integer cod) {

        if (cod == null) {
            return null;
        }

        for (ExpenseType expenseType : ExpenseType.values()) {

            if (cod.equals(expenseType.getCod())) {
                return expenseType;
            }
        }

        throw new IllegalArgumentException("Tipo de despesa inválido: " + cod);
    }
}
