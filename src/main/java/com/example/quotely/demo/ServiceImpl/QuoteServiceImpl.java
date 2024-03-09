package com.example.quotely.demo.ServiceImpl;

import com.example.quotely.demo.Repository.QuoteRepository;
import com.example.quotely.demo.Service.QuoteService;
import com.example.quotely.demo.Vo.Data;
import com.example.quotely.demo.Vo.QuotesVo;
import com.example.quotely.demo.Vo.RandomQuoteSelector;
import lombok.RequiredArgsConstructor;
import com.example.quotely.demo.Mapper.QuotesMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;

    @Override
    public List<Data> newQuote(Integer limit) {
        List<QuotesVo> quotesVo =QuotesMapper.toQuotesVoList(quoteRepository.findAll());
        List<Data> data=RandomQuoteSelector.selectRandomQuotes(quotesVo,limit);
    return data;
    }
}
