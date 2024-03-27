package com.example.quotely.demo.Mapper;

import com.example.quotely.demo.Entity.Categories;
import com.example.quotely.demo.Enums.GeneralStatus;
import com.example.quotely.demo.Vo.CategoriesVo;

public class CategoriesMapper {
    public static Categories  toCategories(CategoriesVo categoriesVo){
        return Categories.builder()
                .categoriesId(categoriesVo.getCategoriesId())
                .categoriesName(categoriesVo.getCategoriesName())
                .status(GeneralStatus.ACTIVE)
                .build();
    }
}
