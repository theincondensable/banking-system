package io.incondensable.service.transaction;

import io.incondensable.controller.dto.TransactionRequestDTO;
import io.incondensable.model.TransactionTypeEnum;
import io.incondensable.validator.transaction.TransactionValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author abbas
 */
@Service
@RequiredArgsConstructor
public class TransactionStrategyService {

    private final Map<TransactionTypeEnum, TransactionStrategy> transactionStrategies;
    private final Map<TransactionTypeEnum, TransactionValidator> transactionValidators;

    public void execute(TransactionRequestDTO req) {
        TransactionTypeEnum transactionType = TransactionTypeEnum.findType(req.getTransactionType());

        transactionValidators.get(transactionType).validateRequest(req);
        transactionStrategies.get(transactionType).execute(req);
    }

}
