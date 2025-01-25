package com.desafio.transaction_api.services;

import com.desafio.transaction_api.dtos.TransactionDTO;
import com.desafio.transaction_api.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    final ArrayList<TransactionDTO> transactions = new ArrayList<TransactionDTO>();

    public void addTransaction(TransactionDTO transaction){
        log.info("Processo de validação da transação iniciado.");
        if(transaction.value() <= 0){
            log.error("Valor da transação menor ou igual a 0.");
            throw new UnprocessableEntity("Valor da transação menor ou igual a 0.");
        }

        if(transaction.dateTime().isAfter(OffsetDateTime.now())){
            log.error("Data e hora posteriores a permitida.");
            throw new UnprocessableEntity("Data e hora posteriores a permitida.");
        }
        transactions.add(transaction);

        log.info("Transação validada e criada.");
    }

    public void deleteTransactions(){
        transactions.clear();
    }

    public List<TransactionDTO> getTransactions(Integer secondsRange){
        OffsetDateTime datetime = OffsetDateTime.now().minusSeconds(secondsRange);
        return transactions.stream().filter(transaction -> transaction.dateTime().isAfter(datetime)).toList();
    }
}
