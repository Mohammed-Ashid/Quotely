package com.example.quotely.demo.ServiceImpl;

import com.example.quotely.demo.Entity.Favourites;
import com.example.quotely.demo.Entity.Quotes;
import com.example.quotely.demo.Mapper.FavouritesMapper;
import com.example.quotely.demo.Repository.FavouritesRepository;
import com.example.quotely.demo.Repository.QuoteRepository;
import com.example.quotely.demo.Responses.FavouriteResponseData;
import com.example.quotely.demo.Responses.FavouritesData;
import com.example.quotely.demo.Responses.QuotesData;
import com.example.quotely.demo.Responses.QuotesResponseData;
import com.example.quotely.demo.Service.FavouriteService;
import com.example.quotely.demo.Vo.FavouritesVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FavouritesServiceImpl implements FavouriteService {
    private final FavouritesRepository favouritesRepository;
    private final QuoteRepository quoteRepository;

    @Override
    public FavouriteResponseData removeFavourites(FavouritesVo favouritesVo) {
        Long userId = favouritesVo.getUsersId();
        Long quotesId = favouritesVo.getQuotesId();

        // Delete the row with the given userId and quotesId
        favouritesRepository.deleteByUserIdAndQuotesId(userId, quotesId);
        FavouritesData favouritesData=FavouritesData.builder()
                .userId(userId)
                .quotesId(quotesId)
                .build();
        FavouriteResponseData favouriteResponseData=FavouriteResponseData.builder()
                .code("Success")
                .status("ok")
                .message("Removed From Favourites")
                .data(Optional.of(Collections.singletonList(favouritesData)))
                .build();
        return favouriteResponseData;
    }

    @Override
    public FavouriteResponseData addToFavourites(FavouritesVo favouritesVo) {
        Favourites favourites= FavouritesMapper.toFavourites(favouritesVo);
        Date date=new Date();
        favourites.setCreatedAt(date);
        favouritesRepository.save(favourites);
        FavouritesData favouritesData=FavouritesData.builder()
                .quotesId(favouritesVo.getQuotesId())
                .userId(favouritesVo.getUsersId())
                .build();
        FavouriteResponseData favouriteResponseData=FavouriteResponseData.builder()
                .code("Success")
                .status("ok")
                .message("Added to Favourites")
                .data(Optional.ofNullable(Collections.singletonList(favouritesData)))
                .build();
        return favouriteResponseData;
    }

    @Override
    public QuotesResponseData viewFavourites(Long userId) {
        List<QuotesData> selectedQuotes = new ArrayList<>();
        // Fetch list of quote IDs belonging to the userId
        Optional<List<Long>> quoteIds = favouritesRepository.findUserIdByQuotesId(userId);
        if (quoteIds.isPresent()) {
            List<Long> quotesIdList = quoteIds.get();
            for (Long quoteId : quotesIdList) {
                Optional<Quotes> quoteOptional = quoteRepository.findById(quoteId);
                if (quoteOptional.isPresent()) {
                    Quotes quote = quoteOptional.get();
                    QuotesData data = new QuotesData();
                    data.setId(quote.getQuotesId());
                    data.setCategory(quote.getCategory());
                    data.setDataList(List.of(quote.getContent()));

                    selectedQuotes.add(data);
                    // Now you can save the content or perform any other operation
                }
            }
        }

        QuotesResponseData responseData= QuotesResponseData.builder()
                .code("success")
                .status("ok")
                .message("List of favourites quotes")
                .data(Optional.of(selectedQuotes))
                .build();
        return responseData;
    }
}
