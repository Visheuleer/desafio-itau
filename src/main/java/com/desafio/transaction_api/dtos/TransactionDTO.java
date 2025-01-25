package com.desafio.transaction_api.dtos;

import java.time.OffsetDateTime;

public record TransactionDTO(Double value, OffsetDateTime dateTime) {
}
