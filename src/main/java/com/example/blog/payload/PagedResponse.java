package com.example.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse <T>{
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public void setContent(List<T> content) {
        if(content == null){
            this.content = null;
        } else {
            this.content = Collections.unmodifiableList(content);
        }

    }
}
