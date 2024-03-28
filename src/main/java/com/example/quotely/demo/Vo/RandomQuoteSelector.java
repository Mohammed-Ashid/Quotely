package com.example.quotely.demo.Vo;
import com.example.quotely.demo.Entity.StatusOfQuotesUsage;
import com.example.quotely.demo.Enums.GeneralStatus;
import com.example.quotely.demo.Repository.StatusOfQuotesUsageRepository;

import com.example.quotely.demo.Responses.QuotesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
@Component
@EnableJpaRepositories(basePackages = "com.example.quotely.demo.Repository")
@EntityScan(basePackages = "com.example.quotely.demo.Entity")
@Service



public class RandomQuoteSelector {
    private final StatusOfQuotesUsageRepository statusOfQuoteUsageRepository;

    @Autowired
    public RandomQuoteSelector(StatusOfQuotesUsageRepository statusOfQuoteUsageRepository) {
        this.statusOfQuoteUsageRepository = statusOfQuoteUsageRepository;
    }

    public boolean doesUserQuoteExist(Long suserId, Long quotesId) {
//
        Optional<List<Long>> userIdOptional = statusOfQuoteUsageRepository.findUserIdByQuotesId(quotesId);
            // Check if the userIdOptional is present and matches the provided userId


        boolean isUserIdPresent = userIdOptional.map(userIdList -> userIdList.contains(suserId)).orElse(false);
        return isUserIdPresent;
    }
    public Long userUsedQuotesSizeFinder(Long suserId){
        Optional<List<Long>> quotesIdOptional=statusOfQuoteUsageRepository.findUserUsedQuotes(suserId);
        Long size = Long.valueOf(quotesIdOptional.map(List::size).orElse(0));

        return size;

    }


    public Optional<List<QuotesData>> selectRandomQuotes(List<QuotesVo> quotes, Long numberOfQuotesToSelect, Long suserId,String authKey)
    {
        if (quotes == null || quotes.isEmpty() || numberOfQuotesToSelect <= 0)
        {
            return Optional.empty(); // Return an empty Optional if the input is invalid
        }
        Long userUsedQuotesSize=userUsedQuotesSizeFinder(suserId);

        List<QuotesData> selectedQuotes = new ArrayList<>();
        Random random = new Random();
        int totalQuotes = quotes.size();
        int balanceQuotesSize= (int) (totalQuotes-userUsedQuotesSize);
        if(balanceQuotesSize==0){
            return null;
        }

        // Ensure we don't try to select more quotes than available
        long numberOfQuotes = Math.min(numberOfQuotesToSelect, totalQuotes);

            Integer iterationCount=0;
            for (int i = 0; i < numberOfQuotes; i++)
            {
                int randomIndex = random.nextInt(totalQuotes);
                QuotesVo randomQuote = quotes.get(randomIndex);

                // Fetching quotesId from the randomQuote
                Long squotesId = randomQuote.getQuotesId();


                // Check if the current userId exists in the list
                boolean userQuoteExists = doesUserQuoteExist(suserId, squotesId);


                    if(selectedQuotes.size()==balanceQuotesSize) {
                        return Optional.ofNullable(selectedQuotes);
                    }





                // If userId exists, skip this quote and continue to the next iteration
                if (userQuoteExists) {
                    i--;
                    continue;
                }





                StatusOfQuotesUsage statusOfQuotesUsage=StatusOfQuotesUsage.builder()
                        .quotesId(squotesId)
                        .usersId(suserId)
                        .authKey(authKey)
                        .status(GeneralStatus.ACTIVE)
                        .build();
                statusOfQuoteUsageRepository.save(statusOfQuotesUsage);

                QuotesData data = new QuotesData();
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

