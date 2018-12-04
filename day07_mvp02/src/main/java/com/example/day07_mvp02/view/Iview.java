package com.example.day07_mvp02.view;

public interface Iview<E> {
    public void success(E e);
    public void error(String str);
}
