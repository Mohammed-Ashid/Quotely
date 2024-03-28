package com.example.quotely.demo.ServiceImpl;

import com.example.quotely.demo.Entity.Quotes;
import com.example.quotely.demo.Entity.Users;
import com.example.quotely.demo.Repository.FavouritesRepository;
import com.example.quotely.demo.Repository.QuoteRepository;
import com.example.quotely.demo.Repository.UserRepository;
import com.example.quotely.demo.Service.QuoteService;
import com.example.quotely.demo.Responses.QuotesData;
import com.example.quotely.demo.Vo.QuotesVo;
import com.example.quotely.demo.Vo.RandomQuoteSelector;
import com.example.quotely.demo.Responses.QuotesResponseData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.example.quotely.demo.Mapper.QuotesMapper;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;
    private final RandomQuoteSelector randomQuoteSelector;
    private final UserRepository userRepository;
    private  final FavouritesRepository favouritesRepository;

    @Override
    @Transactional
    public QuotesResponseData newQuote(Long limit, Long userId, String authKey) {
        QuotesResponseData responseData = new QuotesResponseData();
        Optional<Users> userOptional = userRepository.findById(userId);
        String auth=authKey;
        Users users=userOptional.get();
        if (userOptional.isEmpty() ) {
            QuotesData dataResult = QuotesData.builder()
                    .id(0)
                    .category("user")
                    .dataList("No user exist ")
                    .build();
            responseData = QuotesResponseData.builder()
                    .code("Bad_Request")
                    .status("Bad_Request")
                    .message("Invalid Usage")
                    .data(Optional.ofNullable(Collections.singletonList(dataResult)))
                    .build();
            return responseData;
        }else {
            List<QuotesVo> quotesVo = QuotesMapper.toQuotesVoList(quoteRepository.findAll());
            Optional<List<QuotesData>> data = randomQuoteSelector.selectRandomQuotes(quotesVo, limit, userId,authKey);



            if (data == null || limit <= 0 || data.isEmpty()) {
                QuotesData dataResult = QuotesData.builder()
                        .id(0)
                        .category("user")
                        .dataList("invalid ")
                        .build();
                responseData = QuotesResponseData.builder()
                        .code("Bad_Request")
                        .status("Bad_Request")
                        .message("Invalid Usage")
                        .data(Optional.ofNullable(Collections.singletonList(dataResult)))
                        .build();


            } else {
                responseData = QuotesResponseData.builder()
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
    public QuotesResponseData addQuote(QuotesVo quotesVo) {
        Quotes quotes=QuotesMapper.toQuotes(quotesVo);
        Date date=new Date();
        quotes.setCreatedAt(date);

        quoteRepository.save(quotes);
        QuotesData dataResult = QuotesData.builder()
                .id(quotes.getQuotesId())
                .category(quotesVo.getCategory())
                .dataList("New quote added")
                .build();

        QuotesResponseData responseData= QuotesResponseData.builder()
                .code("success")
                .status("success")
                .message("success")
                .data(Optional.ofNullable(Collections.singletonList(dataResult)))
                .build();
        return responseData;
    }

//    @Override
//    public QuotesResponseData addToFavourites(Long userId, Long quotesId) {
//        Favourites favourites=new Favourites();
//        favourites.setUsersId(userId);
//        favourites.setQuotesId(quotesId);
//        Date date=new Date();
//        favourites.setCreatedAt(date);
//        favourites.setStatus(GeneralStatus.ACTIVE);
//        favouritesRepository.save(favourites);
//        QuotesData quotesData=QuotesData.builder()
//                .id(quotesId)
//                .dataList("Favourites Updated")
//                .build();
//        QuotesResponseData quotesResponseData= QuotesResponseData.builder()
//                .code("Success")
//                .status("Ok")
//                .message("Added to Favourites")
//                .data(Optional.ofNullable(Collections.singletonList(quotesData)))
//                .build();
//        return quotesResponseData;
//    }

//    @Override
//    public QuotesResponseData removeFromFavourites(Long userId, Long quotesId) {
//        Optional<Favourites> favourites=favouritesRepository.findByUserIdAndQuotesId(userId,quotesId);
//        if(favourites.isPresent()){
//
//        }
//        return null;
//    }
}
