package com.hj.blog.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGridView {

    private Integer code=0;
    private String msg="";

    private Object data;

    public DataGridView(Object data) {
        this.data = data;
    }


}
