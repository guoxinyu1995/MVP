package com.example.day01_register.ui.activity;

public interface Iview<E> {
    void success(E e);
    void error(String str);
}
