package com.example.quotely.demo.Controller;

import com.example.quotely.demo.DataTransferObject.NewQuoteRequest;
import com.example.quotely.demo.Entity.Quotes;
import com.example.quotely.demo.Mapper.QuotesMapper;
import com.example.quotely.demo.Service.QuoteService;
import com.example.quotely.demo.Responses.QuotesData;
import com.example.quotely.demo.Vo.QuotesVo;
import com.example.quotely.demo.Responses.QuotesResponseData;
import lombok.AllArgsConstructor;
import lombok.Builder;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import java.util.Optional;


@AllArgsConstructor
@RestController
@RequestMapping("/quotes")
@Builder


public class QuotesController {
    @Autowired
    private final QuoteService quoteService;
    private final QuotesResponseData responseData;

    @PostMapping("/addnew")
    public ResponseEntity<QuotesResponseData> addQuote(@RequestBody QuotesVo quotesVo){
        Quotes quotes= QuotesMapper.toQuotes(quotesVo);
        QuotesResponseData result=quoteService.addQuote(quotesVo);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/newset")
    public ResponseEntity<QuotesResponseData> newQuote(@RequestBody NewQuoteRequest request){


        try {
          QuotesResponseData result=quoteService.newQuote(request.getLimit(), request.getUserId(),request.getAuthKey());


            // Return ResponseEntity with your responseData
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Handle exceptions here
            QuotesData dataResult = QuotesData.builder()
                    .id(0)
                    .category("quotes/newset")
                    .dataList("Cannot Generate Quotes")
                    .build();

            QuotesResponseData errorResponse = QuotesResponseData.builder()
                    .code("Bad_Request")
                    .status("Bad_Request")
                    .message("Invalid usage")
                    .data(Optional.ofNullable(Collections.singletonList(dataResult))) // Assuming data field expects a String in case of an error
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

//    @PostMapping("/addtofavourites")
//    public ResponseEntity<QuotesResponseData> addToFavourites(@RequestParam Long userId,@RequestParam Long quotesId){
//        QuotesResponseData result=quoteService.addToFavourites(userId,quotesId);
//        return ResponseEntity.ok(result);
//    }
//
//    @PostMapping("/removefromfavourites")
//    public ResponseEntity<QuotesResponseData> removeFromFavourites(@RequestParam Long userId,@RequestParam Long quotesId){
//        QuotesResponseData result=quoteService.removeFromFavourites(userId,quotesId);
//        return ResponseEntity.ok(result);
//    }
}
