package com.todo.demo.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TodoRequest {

    @NotBlank
    @Size(min = 3,max = 20)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
