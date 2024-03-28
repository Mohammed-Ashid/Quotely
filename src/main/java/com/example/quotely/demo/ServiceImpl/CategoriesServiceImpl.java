package com.example.quotely.demo.ServiceImpl;

import com.example.quotely.demo.Entity.Categories;
import com.example.quotely.demo.Entity.UserCategories;
import com.example.quotely.demo.Enums.GeneralStatus;
import com.example.quotely.demo.Mapper.CategoriesMapper;
import com.example.quotely.demo.Repository.CategoriesRepository;
import com.example.quotely.demo.Repository.UserCategoriesRepository;
import com.example.quotely.demo.Responses.CategoriesData;
import com.example.quotely.demo.Responses.CategoryResponseData;
import com.example.quotely.demo.Service.CategoriesService;
import com.example.quotely.demo.Vo.CategoriesVo;
import com.example.quotely.demo.Vo.UserCategoriesVo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CategoriesServiceImpl implements CategoriesService {

    private final UserCategoriesRepository userCategoriesRepository;
    private final CategoriesRepository categoriesRepository;

    @Transactional
    @Override
    public CategoryResponseData addToCategory(UserCategoriesVo userCategoriesVo) {

        Long userId = userCategoriesVo.getUsersId();
        String auth=userCategoriesVo.getAuthKey();
        Optional<List<Long>> categoriesIdOptional = userCategoriesVo.getCategoriesId();
        List<CategoriesData> savedCategories = new ArrayList<>();

        // Delete existing rows for the given userId
        userCategoriesRepository.deleteByUserId(userId);

        CategoryResponseData categoryResponseData = new CategoryResponseData();

        if (categoriesIdOptional.isPresent()) {
            List<Long> categoriesId = categoriesIdOptional.get();
            for (Long categoryId : categoriesId) {
                UserCategories category = new UserCategories();
                category.setUsersId(userId);
                category.setAuthKey(auth);
                category.setCategoriesId(categoryId);
                category.setStatus(GeneralStatus.ACTIVE);
                userCategoriesRepository.save(category);

                CategoriesData categoriesData=new CategoriesData();
                categoriesData.setUserId(userId);
                categoriesData.setCategoriesId(categoryId);
                savedCategories.add(categoriesData);


            }
        }




        CategoryResponseData categoryResponseData1=new CategoryResponseData();
        categoryResponseData1=CategoryResponseData.builder()
                .code("success")
                .status("ok")
                .message("Preferences Made")
                .data(Optional.of(savedCategories))
                .build();
        return categoryResponseData1;
    }

    @Override
    public CategoryResponseData createCategory(CategoriesVo categoriesVo) {
        Categories categories= CategoriesMapper.toCategories(categoriesVo);
        Date date=new Date();
        categories.setCreatedAt(date);
        categoriesRepository.save(categories);
        CategoriesData categoriesData=CategoriesData.builder()
                .categoriesId(categoriesVo.getCategoriesId())
                .categoriesname(categoriesVo.getCategoriesName())
                .build();
        CategoryResponseData categoryResponseData=CategoryResponseData.builder()
                .code("success")
                .status("ok")
                .message("new category created")
                .data(Optional.ofNullable(Collections.singletonList(categoriesData)))
                .build();
        return categoryResponseData;
    }
}


