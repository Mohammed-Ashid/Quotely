package com.example.quotely.demo.ServiceImpl;

import com.example.quotely.demo.Entity.Quotes;
import com.example.quotely.demo.Entity.Users;
import com.example.quotely.demo.Repository.QuoteRepository;
import com.example.quotely.demo.Repository.UserRepository;
import com.example.quotely.demo.Service.QuoteService;
import com.example.quotely.demo.Vo.Data;
import com.example.quotely.demo.Vo.QuotesVo;
import com.example.quotely.demo.Vo.RandomQuoteSelector;
import com.example.quotely.demo.Vo.ResponseData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.quotely.demo.Mapper.QuotesMapper;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final RandomQuoteSelector randomQuoteSelector;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseData newQuote(Long limit, Long userId) {
        ResponseData responseData = new ResponseData();
        Optional<Users> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            Data dataResult = Data.builder()
                    .id(0)
                    .category("user")
                    .dataList("No user exist ")
                    .build();
            responseData = ResponseData.builder()
                    .code("Bad_Request")
                    .status("Bad_Request")
                    .message("Invalid Usage")
                    .data(Optional.ofNullable(Collections.singletonList(dataResult)))
                    .build();
            return responseData;
        }else {
            List<QuotesVo> quotesVo = QuotesMapper.toQuotesVoList(quoteRepository.findAll());
            Optional<List<Data>> data = randomQuoteSelector.selectRandomQuotes(quotesVo, limit, userId);
//        boolean datapresent= data.isPresent();


            if (data == null || limit <= 0 || data.isEmpty()) {
                Data dataResult = Data.builder()
                        .id(0)
                        .category("user")
                        .dataList("invalid ")
                        .build();
                responseData = ResponseData.builder()
                        .code("Bad_Request")
                        .status("Bad_Request")
                        .message("Invalid Usage")
                        .data(Optional.ofNullable(Collections.singletonList(dataResult)))
                        .build();


            } else {
                responseData = ResponseData.builder()
                        .code("Success")
                        .status("OK")
                        .message("New quotes generated successfully")
                        .data(data)
                        .build();

            }
            return responseData;
        }
    }

    @Override
    public ResponseData addQuote(QuotesVo quotesVo) {
        Quotes quotes=QuotesMapper.toQuotes(quotesVo);
        quoteRepository.save(quotes);
        Data dataResult = Data.builder()
                .id(quotesVo.getQuotesId())
                .category(quotesVo.getCategory())
                .dataList("New quote added")
                .build();

        ResponseData responseData= ResponseData.builder()
                .code("success")
                .status("success")
                .message("success")
                .data(Optional.ofNullable(Collections.singletonList(dataResult)))
                .build();
        return responseData;
    }
}
