package com.myfinancial.model.domain.enums;

import lombok.*;

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


    public static ExpenseType toEnum(final String name) {

        if (name == null) {
            return null;
        }

        for (ExpenseType expenseType : ExpenseType.values()) {

            if (name.equals(expenseType.getName())) {
                return expenseType;
            }
        }

        throw new IllegalArgumentException("Tipo de despesa inválido: " + name);
    }
}
