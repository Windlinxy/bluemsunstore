package com.bluemsun.util;

public enum Kind {
    KIND_1("食品", "食品"),
    KIND_2("男装","男装"),
    KIND_3("女装","女装"),
    KIND_4("书籍","书籍"),
    KIND_5("日用品","日用品"),
    KIND_6("文具","文具"),
    KIND_7("美妆","美妆"),
    KIND_8("奢品","奢品"),
    KIND_9("其他","其他"),
    KIND_10("玩具","玩具"),
    ;

    private final String key;
    private final String value;

    Kind(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
