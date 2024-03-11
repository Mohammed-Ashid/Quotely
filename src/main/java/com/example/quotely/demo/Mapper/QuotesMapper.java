package com.example.quotely.demo.Mapper;

import com.example.quotely.demo.Entity.Quotes;
import com.example.quotely.demo.Vo.QuotesVo;

import java.util.List;
import java.util.stream.Collectors;

public class QuotesMapper {
    public static List<QuotesVo> toQuotesVoList(List<Quotes> quotes) {
    return quotes.stream().map(QuotesMapper::toQuotesVo)
            .collect(Collectors.toList());

    }

    private static QuotesVo toQuotesVo(Quotes quotes) {
  return QuotesVo.builder()
          .quotesId(quotes.getQuotesId())
          .category(quotes.getCategory())
          .content(quotes.getContent())
          .build();
    }

    public static Quotes toQuotes(QuotesVo quotesVo) {
        return Quotes.builder()
                .quotesId(quotesVo.getQuotesId())
                .category(quotesVo.getCategory())
                .content(quotesVo.getContent())
                .build();
    }
}
