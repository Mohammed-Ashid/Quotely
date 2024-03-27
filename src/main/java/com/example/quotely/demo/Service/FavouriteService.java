package com.example.quotely.demo.Service;

import com.example.quotely.demo.Responses.FavouriteResponseData;
import com.example.quotely.demo.Responses.QuotesResponseData;
import com.example.quotely.demo.Vo.FavouritesVo;

public interface FavouriteService {
    public FavouriteResponseData removeFavourites(FavouritesVo favouritesVo);


    FavouriteResponseData addToFavourites(FavouritesVo favouritesVo);

   public QuotesResponseData viewFavourites(Long userId);
}
