package com.jinxin.jetpacktest.databinding;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JinXin 2020/9/23
 */
public class RecyclerViewViewModel {

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Book book = new Book("标题" + i, "作者" + i, "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=652698136,4159754933&fm=26&gp=0.jpg");
            books.add(book);
        }
        return books;
    }
}
