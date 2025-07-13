package com.ryansstore.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductSummaryDTO { // Just named this to separate from interface, demonstration of class v. interface
    private Long Id;
    private String name;
}
