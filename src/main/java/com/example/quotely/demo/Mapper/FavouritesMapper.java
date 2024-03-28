package com.example.quotely.demo.Mapper;

import com.example.quotely.demo.Entity.Favourites;
import com.example.quotely.demo.Enums.GeneralStatus;
import com.example.quotely.demo.Vo.FavouritesVo;

public class FavouritesMapper {
    public static Favourites toFavourites(FavouritesVo favouritesVo){
        return Favourites.builder()
                .quotesId(favouritesVo.getQuotesId())
                .usersId(favouritesVo.getUsersId())
                .authKey(favouritesVo.getAuthKey())
                .status(GeneralStatus.ACTIVE)
                .build();
    }


}
