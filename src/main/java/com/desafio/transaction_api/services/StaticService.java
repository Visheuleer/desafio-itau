package com.desafio.transaction_api.services;

import com.desafio.transaction_api.dtos.StaticDTO;
import com.desafio.transaction_api.dtos.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaticService {
    TransactionService transactionService;

    StaticDTO getStatics(Integer secondsRange){
        List<TransactionDTO> transactions = transactionService.getTransactions(secondsRange);
        DoubleSummaryStatistics statics = transactions.stream().mapToDouble(TransactionDTO::value).summaryStatistics();
        return new StaticDTO(statics.getCount(), statics.getSum(), statics.getAverage(), statics.getMin(), statics.getMax());
    }
}
