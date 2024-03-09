package com.example.quotely.demo.Vo;

import com.example.quotely.demo.Entity.Quotes;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomQuoteSelector {

    public static List<Data> selectRandomQuotes(List<QuotesVo> quotes, int numberOfQuotesToSelect) {
        List<Data> selectedQuotes = new ArrayList<>();
        Random random = new Random();

        int totalQuotes = quotes.size();

        // Ensure we don't try to select more quotes than available
        int numberOfQuotes = Math.min(numberOfQuotesToSelect, totalQuotes);

        for (int i = 0; i < numberOfQuotes; i++) {
            int randomIndex = random.nextInt(totalQuotes);
            QuotesVo randomQuote = quotes.get(randomIndex);

            Data data = new Data();
            data.setId(randomQuote.getId());
            data.setCategory(randomQuote.getCategory()); // Assuming Quotes has getCategory method
            data.setDataList(List.of(randomQuote.getContent())); // Assuming Quotes has getContent method

            selectedQuotes.add(data);
        }

        return selectedQuotes;
    }

}
//package com.example.quotely.demo.Vo;
//
//import com.example.quotely.demo.Entity.Quotes;
//import lombok.Builder;
//import lombok.experimental.SuperBuilder;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//@Builder
//@SuperBuilder
//public class RandomQuoteSelector {
//
//    public static List<String> selectRandomQuotes(List<QuotesVo> quotes, int numberOfQuotesToSelect) {
//        List<String> selectedQuotesOutput = new ArrayList<>();
//        Random random = new Random();
//
//        int totalQuotes = quotes.size();
//
//        // Ensure we don't try to select more quotes than available
//        int numberOfQuotes = Math.min(numberOfQuotesToSelect, totalQuotes);
//
//        for (int i = 0; i < numberOfQuotes; i++) {
//            int randomIndex = random.nextInt(totalQuotes);
//            QuotesVo randomQuote = quotes.get(randomIndex);
//
//            Data data = new Data();
//            data.setId(randomQuote.getId());
//            data.setCategory(randomQuote.getCategory()); // Assuming Quotes has getCategory method
//            data.setDataList(List.of(randomQuote.getContent())); // Assuming Quotes has getContent method
//
//            // Format the Data object as a string and add it to the list
//            selectedQuotesOutput.add(formatDataAsString(data));
//        }
//
//        return selectedQuotesOutput;
//    }
//
//    private static String formatDataAsString(Data data) {
//        return String.format("id=%d, category='%s', dataList=%s",
//                data.getId(), data.getCategory(), data.getDataList());
//    }
//}

