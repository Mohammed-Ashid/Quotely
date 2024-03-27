package com.example.quotely.demo.Service;

import com.example.quotely.demo.Responses.CategoryResponseData;
import com.example.quotely.demo.Vo.CategoriesVo;
import com.example.quotely.demo.Vo.UserCategoriesVo;

public interface CategoriesService {

  public CategoryResponseData addToCategory(UserCategoriesVo categoriesVo);

 public CategoryResponseData createCategory(CategoriesVo categoriesVo);
}
