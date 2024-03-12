package com.example.quotely.demo.Service;

import com.example.quotely.demo.Vo.QuotesVo;
import com.example.quotely.demo.Vo.ResponseData;

public interface QuoteService {
    public ResponseData newQuote(Long limit, Long userId);

   public ResponseData addQuote(QuotesVo quotesVo);
}
