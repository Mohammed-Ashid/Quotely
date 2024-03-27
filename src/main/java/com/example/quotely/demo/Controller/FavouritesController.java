package com.example.quotely.demo.Controller;

import com.example.quotely.demo.Responses.FavouriteResponseData;
import com.example.quotely.demo.Responses.QuotesResponseData;
import com.example.quotely.demo.Service.FavouriteService;
import com.example.quotely.demo.Vo.FavouritesVo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favourites")
@RequiredArgsConstructor
@Builder
public class FavouritesController {
    private final FavouriteService favouriteService;

    @PostMapping("/addtofavourites")
    public ResponseEntity<FavouriteResponseData> addToFavourites(@RequestBody FavouritesVo favouritesVo){
        FavouriteResponseData favouriteResponse=favouriteService.addToFavourites(favouritesVo);
        return ResponseEntity.ok(favouriteResponse);
    }

    @PostMapping("/viewfavourites")
    public ResponseEntity<QuotesResponseData> viewFavourites(@RequestParam Long userId){
        QuotesResponseData quotesResponseData =favouriteService.viewFavourites(userId);
        return ResponseEntity.ok(quotesResponseData);
    }
    @PostMapping("/removefavourites")
    public ResponseEntity<FavouriteResponseData> removeFavourites(@RequestBody FavouritesVo favouritesVo){
        FavouriteResponseData favouriteResponseData=favouriteService.removeFavourites(favouritesVo);
        return ResponseEntity.ok(favouriteResponseData);
    }
}
