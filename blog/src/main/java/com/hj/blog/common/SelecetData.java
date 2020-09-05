package com.hj.blog.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelecetData {

    private String name;
    private Long value;
    private String selected;
    private String disabled;

}
