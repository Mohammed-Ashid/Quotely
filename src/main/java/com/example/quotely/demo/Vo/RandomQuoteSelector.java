package com.example.quotely.demo.Vo;

import com.example.quotely.demo.Repository.StatusOfQuotesUsageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
@RequiredArgsConstructor
@Component
@EnableJpaRepositories(basePackages = "com.example.quotely.demo.Repository")
@EntityScan(basePackages = "com.example.quotely.demo.Entity")


public class RandomQuoteSelector {
    @Autowired
    public static StatusOfQuotesUsageRepository statusOfQuoteUsageRepository;

    @Transactional
    public static Optional<Long> getUserIdByQuotesId(Long QuotesId)
    {
        return statusOfQuoteUsageRepository.findUserIdByQuotesId(QuotesId);
    }

    public static Optional<List<Data>> selectRandomQuotes(List<QuotesVo> quotes, int numberOfQuotesToSelect, Long userId)
    {
        if (quotes == null || quotes.isEmpty() || numberOfQuotesToSelect <= 0)
        {
            return Optional.empty(); // Return an empty Optional if the input is invalid
        }

        List<Data> selectedQuotes = new ArrayList<>();
        Random random = new Random();
        int totalQuotes = quotes.size();

        // Ensure we don't try to select more quotes than available
        int numberOfQuotes = Math.min(numberOfQuotesToSelect, totalQuotes);
        if (totalQuotes < numberOfQuotesToSelect)
        {
            return Optional.empty();
        }
        else
        {

            for (int i = 0; i < numberOfQuotes; i++)
            {
                int randomIndex = random.nextInt(totalQuotes);
                QuotesVo randomQuote = quotes.get(randomIndex);

                // Fetching quotesId from the randomQuote
                Long quotesId = randomQuote.getQuotesId();

                // Fetching userIds who have used the quote
                Optional<Long> usrId=getUserIdByQuotesId(quotesId);

                // Check if the current userId exists in the list
                boolean userIdExists = usrId.map(id -> id.equals(userId)).orElse(false);

                // If userId exists, skip this quote and continue to the next iteration
                if (userIdExists) {
                    i--;
                    continue;
                }





                statusOfQuoteUsageRepository.saveUserIdAndQuotesId(userId, quotesId);

                Data data = new Data();
                data.setId(randomQuote.getQuotesId());
                data.setCategory(randomQuote.getCategory());
                data.setDataList(List.of(randomQuote.getContent()));

                selectedQuotes.add(data);



            }


            if (selectedQuotes.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(selectedQuotes);
            }
        }
    }
}

//    public static Optional<Data> selectRandomQuotes(List<QuotesVo> quotes, int numberOfQuotesToSelect) {
//        List<Data> selectedQuotes = new ArrayList<>();
//        Random random = new Random();
//
//        int totalQuotes = quotes.size();
//
//        // Ensure we don't try to select more quotes than available
//        int numberOfQuotes = Math.min(numberOfQuotesToSelect, totalQuotes);
//        if (numberOfQuotes < numberOfQuotesToSelect) {
//            return null;
//        } else {
//
//            for (int i = 0; i < numberOfQuotes; i++) {
//                int randomIndex = random.nextInt(totalQuotes);
//                QuotesVo randomQuote = quotes.get(randomIndex);
//
//                Data data = new Data();
//                data.setId(randomQuote.getQuotesId());
//                data.setCategory(randomQuote.getCategory()); // Assuming Quotes has getCategory method
//                data.setDataList(List.of(randomQuote.getContent())); // Assuming Quotes has getContent method
//
//                selectedQuotes.add(data);
//            }
//
//            return selectedQuotes;
//        }
//    }

//public  Optional<Data> selectRandomQuote(List<QuotesVo> quotes) {
//    if (quotes == null || quotes.isEmpty()) {
//        return Optional.empty(); // Return an empty Optional if the input is invalid
//    }
//
//    Random random = new Random();
//    int totalQuotes = quotes.size();
//    int randomIndex = random.nextInt(totalQuotes);
//    QuotesVo randomQuote = quotes.get(randomIndex);
//
//    Data data = new Data();
//    data.setId(randomQuote.getQuotesId());
//    data.setCategory(randomQuote.getCategory());
//    data.setDataList(List.of(randomQuote.getContent()));
//
//    return Optional.of(data);
//}


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

