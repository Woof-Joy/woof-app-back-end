package org.woof.woofjoybackend.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PositionalField {
    int start(); // Posição inicial do campo no arquivo
    int length(); // Comprimento do campo
}