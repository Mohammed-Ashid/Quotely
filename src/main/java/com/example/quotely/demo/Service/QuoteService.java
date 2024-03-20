package com.example.quotely.demo.Service;

import com.example.quotely.demo.Vo.QuotesVo;
import com.example.quotely.demo.Responses.QuotesResponseData;

public interface QuoteService {
    public QuotesResponseData newQuote(Long limit, Long userId);

   public QuotesResponseData addQuote(QuotesVo quotesVo);

   public   QuotesResponseData addToFavourites(Long userId, Long quotesId);

    QuotesResponseData removeFromFavourites(Long userId, Long quotesId);
}
