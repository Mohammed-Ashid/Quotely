package com.example.quotely.demo.Vo;
import com.example.quotely.demo.Entity.Quotes;
import com.example.quotely.demo.Entity.StatusOfQuotesUsage;
import com.example.quotely.demo.Enums.GeneralStatus;
import com.example.quotely.demo.Repository.CategoriesRepository;
import com.example.quotely.demo.Repository.QuoteRepository;
import com.example.quotely.demo.Repository.StatusOfQuotesUsageRepository;

import com.example.quotely.demo.Repository.UserCategoriesRepository;
import com.example.quotely.demo.Responses.QuotesData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
@EnableJpaRepositories(basePackages = "com.example.quotely.demo.Repository")
@EntityScan(basePackages = "com.example.quotely.demo.Entity")
@Service



public class RandomQuoteSelector {
    private final StatusOfQuotesUsageRepository statusOfQuoteUsageRepository;

    private final CategoriesRepository categoriesRepository;
    private final QuoteRepository quoteRepository;
    private final UserCategoriesRepository userCategoriesRepository;

    Long userUsedCategorySize;
    boolean userQuoteExists;

    @Autowired
    public RandomQuoteSelector(StatusOfQuotesUsageRepository statusOfQuoteUsageRepository, CategoriesRepository categoriesRepository, QuoteRepository quoteRepository, UserCategoriesRepository userCategoriesRepository) {
        this.statusOfQuoteUsageRepository = statusOfQuoteUsageRepository;
        this.categoriesRepository = categoriesRepository;
        this.quoteRepository = quoteRepository;
        this.userCategoriesRepository = userCategoriesRepository;
    }



    public boolean doesUserQuoteExist(Long suserId, Long quotesId) {
//
        Optional<List<Long>> userIdOptional = statusOfQuoteUsageRepository.findUserIdByQuotesId(quotesId);
            // Check if the userIdOptional is present and matches the provided userId


        boolean isUserIdPresent = userIdOptional.map(userIdList -> userIdList.contains(suserId)).orElse(false);
        return isUserIdPresent;
    }

    private boolean doesUserCategoryExist(Long squotesCategoryId, Optional<List<Long>> userSelectedCategory) {
        if (userSelectedCategory.isPresent() && !userSelectedCategory.get().isEmpty()) {
            List<Long> categories = userSelectedCategory.get();
            // Iterate over user selected categories
            for (Long categoryId : categories) {
                // If any category matches squotesCategoryId, return true
                if (categoryId.equals(squotesCategoryId)) {
                    return true;
                }
            }
        }
        // If no match found or userSelectedCategory is empty or not present, return false
        return false;
    }


    public Long userUsedQuotesSizeFinder(Long suserId, Optional<List<Long>> userSelectedCategory){
        Optional<List<Long>> quotesIdOptional=statusOfQuoteUsageRepository.findUserUsedQuotes(suserId);
        Long size = Long.valueOf(quotesIdOptional.map(List::size).orElse(0));
        Long categorySize = 0L;

        if (quotesIdOptional.isPresent()) {
            List<Long> quotesIds = quotesIdOptional.get();

            for (Long quoteId : quotesIds) {
                Optional<Quotes> quoteOptional = quoteRepository.findById(quoteId);
                if (quoteOptional.isPresent()) {
                    Quotes quote = quoteOptional.get();
                    Long categoryId = quote.getCategoryId();
                    if (categoryId != null && userSelectedCategory.isPresent() && userSelectedCategory.get().contains(categoryId)) {
                        categorySize++;
                    }
                }
            }
        }
userUsedCategorySize=categorySize;



        return size;

    }


    private Long totalCategoryQuoteSize(Optional<List<Long>> userSelectedCategory) {
        Long totalSize = 0L;

        // Check if userSelectedCategory is present
        if (userSelectedCategory.isPresent()) {
            // Iterate over each category ID
            for (Long categoriesId : userSelectedCategory.get()) {
                // Fetch the numberOfQuotes attribute for the current categoryId
                Long numberOfQuotes = categoriesRepository.findNumberOfQuotesByCategoriesId(categoriesId);

                // Add the numberOfQuotes to the total size
                totalSize += numberOfQuotes;
            }
        }

        return totalSize;
    }


//    private Long userCategorySizeFinder(String authKey, Optional<List<Long>> userSelectedCategory) {
//        Optional<List<Long>> categoryIdOptional=statusOfQuoteUsageRepository.findUserUsedCategory(authKey,userSelectedCategory);
//        Long size=Long.valueOf(categoryIdOptional.map(List::size).orElse(0));
//        return size;
//    }



    public Optional<List<QuotesData>> selectRandomQuotes(List<QuotesVo> quotes, Long numberOfQuotesToSelect, Long suserId,String authKey)
    {
        if (quotes == null || quotes.isEmpty() || numberOfQuotesToSelect <= 0)
        {
            return Optional.empty(); // Return an empty Optional if the input is invalid
        }

        Optional<List<Long>> userSelectedCategory = userCategoriesRepository.findByAuthKey(authKey);

//        this finds the number of quotes used by the user and number of quotes from the preference category.
//        1.select quotes from status of quote usage table where the user id matches
//        2.select it as the userUsedQuoteSize
//        3.For each quote id,find the corresponding quote from quotes table and check whther the user prefence category is matched.if matched categorysize increments.
//        4.The value is saved in userUsedCategorySize.
        Long userUsedQuotesSize=userUsedQuotesSizeFinder(suserId,userSelectedCategory);

//        find the size of quotes in category.
        Long totalCategoryQuoteSize=totalCategoryQuoteSize(userSelectedCategory);


        //category id finding

//        Long userUsedCategorySize=userCategorySizeFinder(authKey,userSelectedCategory);
//        List<Quotes> userSelectedCategoryQuotes = quoteRepository.findByCategoryId(userSelectedCategory);
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
                Long squotesCategoryId=randomQuote.getCategoryId();
                StatusOfQuotesUsage statusOfQuotesUsage = new StatusOfQuotesUsage();
                QuotesData data = new QuotesData();



                boolean userCategoryExist=doesUserCategoryExist(squotesCategoryId,userSelectedCategory);
                userQuoteExists = doesUserQuoteExist(suserId, squotesId);
                if(selectedQuotes.size()==balanceQuotesSize) {
                    return Optional.ofNullable(selectedQuotes);
                }

                if(userUsedCategorySize<totalCategoryQuoteSize) {
                    // If userId exists, skip this quote and continue to the next iteration
                    if ((!userCategoryExist || userQuoteExists)) {
                        i--;
                        continue;
                    }
                    statusOfQuotesUsage = StatusOfQuotesUsage.builder()
                            .quotesId(squotesId)
                            .usersId(suserId)
                            .authKey(authKey)
                            .quotesCategoryId(squotesCategoryId)
                            .status(GeneralStatus.ACTIVE)
                            .build();
                    statusOfQuoteUsageRepository.save(statusOfQuotesUsage);


                    data.setId(randomQuote.getQuotesId());
                    data.setCategory(randomQuote.getCategory());
                    data.setCategoryId(squotesCategoryId);
                    data.setDataList(List.of(randomQuote.getContent()));

                    selectedQuotes.add(data);
                    userUsedQuotesSizeFinder(suserId, userSelectedCategory);
                }
                // afteer all quotes from quotes category are selected ,rest quotes are selected using this.ie,quotes which dosenot belong to the user category
                else {


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





                    statusOfQuotesUsage=StatusOfQuotesUsage.builder()
                            .quotesId(squotesId)
                            .usersId(suserId)
                            .authKey(authKey)
                            .quotesCategoryId(squotesCategoryId)
                            .status(GeneralStatus.ACTIVE)
                            .build();
                    statusOfQuoteUsageRepository.save(statusOfQuotesUsage);


                    data.setId(randomQuote.getQuotesId());
                    data.setCategory(randomQuote.getCategory());
                    data.setDataList(List.of(randomQuote.getContent()));

                    selectedQuotes.add(data);


                }

            }


            if (selectedQuotes.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(selectedQuotes);
            }

    }




}

