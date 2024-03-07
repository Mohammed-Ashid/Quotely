package com.example.quotely.demo.Service;

import com.example.quotely.demo.Vo.Data;

import java.util.List;

public interface QuoteService {
    public List<Data> NewQuote(Integer limit);
}
