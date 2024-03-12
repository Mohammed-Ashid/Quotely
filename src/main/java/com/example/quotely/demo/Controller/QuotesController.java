package com.example.quotely.demo.Controller;

import com.example.quotely.demo.DataTransferObject.NewQuoteRequest;
import com.example.quotely.demo.Service.QuoteService;
import com.example.quotely.demo.Vo.Data;
import com.example.quotely.demo.Vo.QuotesVo;
import com.example.quotely.demo.Vo.ResponseData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Limit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/quotes")
@Builder


public class QuotesController {
    @Autowired
    private final QuoteService quoteService;
    private final ResponseData responseData;

    @PostMapping("/addnew")
    public ResponseEntity<ResponseData> addQuote(@RequestBody QuotesVo quotesVo){
        ResponseData result=quoteService.addQuote(quotesVo);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/newset")
    public ResponseEntity<ResponseData> newQuote(@RequestBody NewQuoteRequest request){


        try {
          ResponseData result=quoteService.newQuote(request.getLimit(), request.getUserId());


            // Return ResponseEntity with your responseData
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Handle exceptions here

            ResponseData errorResponse = ResponseData.builder()
                    .code("Bad_Request")
                    .status("Bad_Request")
                    .message("Invalid usage")
                    .data(null) // Assuming data field expects a String in case of an error
                    .build();

            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}
