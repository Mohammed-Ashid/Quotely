package com.example.quotely.demo.ServiceImpl;

import com.example.quotely.demo.Entity.Quotes;
import com.example.quotely.demo.Repository.QuoteRepository;
import com.example.quotely.demo.Service.QuoteService;
import com.example.quotely.demo.Vo.Data;
import com.example.quotely.demo.Vo.QuotesVo;
import com.example.quotely.demo.Vo.RandomQuoteSelector;
import com.example.quotely.demo.Vo.ResponseData;
import lombok.RequiredArgsConstructor;
import com.example.quotely.demo.Mapper.QuotesMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;

    @Override
    public ResponseData newQuote(Integer limit) {
        List<QuotesVo> quotesVo =QuotesMapper.toQuotesVoList(quoteRepository.findAll());
        List<Data> data=RandomQuoteSelector.selectRandomQuotes(quotesVo,limit);
        ResponseData responseData=new ResponseData();
        if(data==null||limit<=0){
           responseData= ResponseData.builder()
                    .code("Bad_Request")
                    .status("Bad_Request")
                    .message("Invalid Usage")
                    .data(null)
                    .build();

        }
        else{
           responseData= ResponseData.builder()
                   .code("Success")
                   .status("OK")
                   .message("New quotes generated successfully")
                   .data(data)
                   .build();
        }
    return responseData;
    }

    @Override
    public ResponseData addQuote(QuotesVo quotesVo) {
        Quotes quotes=QuotesMapper.toQuotes(quotesVo);
        quoteRepository.save(quotes);

        ResponseData responseData= ResponseData.builder()
                .code("success")
                .status("success")
                .message("success")
                .data(null)
                .build();
        return responseData;
    }
}
