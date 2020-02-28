package com.myfinancial.model.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProfileType {

    ADMIN(1, "Administrador"),
    USER(2, "Usuário");


    private int cod;
    private String name;


    public static ProfileType toEnum(final Integer cod) {

        if (cod == null) {
            return null;
        }

        for (ProfileType expenseType : ProfileType.values()) {

            if (cod.equals(expenseType.getCod())) {
                return expenseType;
            }
        }

        throw new IllegalArgumentException("Tipo de perfil inválido: " + cod);
    }


    public static ProfileType toEnum(final String name) {

        if (name == null) {
            return null;
        }

        for (ProfileType profileType : ProfileType.values()) {

            if (name.equals(profileType.getName())) {
                return profileType;
            }
        }

        throw new IllegalArgumentException("Tipo de perfil inválido: " + name);
    }
}
