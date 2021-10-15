package org.dio.jaxrs.diojaxrs.enums;


import java.util.Arrays;
import java.util.stream.Stream;

public enum Race {
    HUMAN("human"),
    ELF("elf"),
    ORC("orc"),
    DWARF("dwarf");

    private final String value;

    Race(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Race of(String value) {
        return Stream.of(Race.values())
                .filter(it -> it.getValue().equals(value))
                .findFirst()
                .orElseThrow();
    }

}
