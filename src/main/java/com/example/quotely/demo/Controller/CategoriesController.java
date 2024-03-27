package com.example.quotely.demo.Controller;

import com.example.quotely.demo.Responses.CategoryResponseData;
import com.example.quotely.demo.Service.CategoriesService;
import com.example.quotely.demo.Vo.CategoriesVo;
import com.example.quotely.demo.Vo.UserCategoriesVo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Builder

@RequiredArgsConstructor

public class CategoriesController {

    private final CategoriesService categoriesService;

    @PostMapping("/createCategory")
    private ResponseEntity<CategoryResponseData> createCategory(@RequestBody CategoriesVo categoriesVo){
        CategoryResponseData categoryResponseData=categoriesService.createCategory(categoriesVo);
        return ResponseEntity.ok(categoryResponseData);
    }

    @PostMapping("/addtocategory")
    private ResponseEntity<CategoryResponseData> addToCategory( @RequestBody UserCategoriesVo categoriesVo){
        CategoryResponseData categoryResponseData=categoriesService.addToCategory(categoriesVo);
        return ResponseEntity.ok(categoryResponseData);
    }

}
