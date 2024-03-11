package com.example.quotely.demo.Service;

import com.example.quotely.demo.Vo.Data;
import com.example.quotely.demo.Vo.QuotesVo;
import com.example.quotely.demo.Vo.ResponseData;

import java.util.List;

public interface QuoteService {
    public ResponseData newQuote(Integer limit);

   public ResponseData addQuote(QuotesVo quotesVo);
}
