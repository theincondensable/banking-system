package io.incondensable.controller;

import io.incondensable.controller.dto.TransactionRequestDTO;
import io.incondensable.service.transaction.TransactionStrategyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author abbas
 */
@RestController
@RequestMapping("/api/v1.0/transaction")
public class TransactionController {

    private final TransactionStrategyService transactionService;

    public TransactionController(TransactionStrategyService transactionService) {
        this.transactionService = transactionService;
    }

    @PutMapping
    public ResponseEntity<String> makeTransaction(@Validated @RequestBody TransactionRequestDTO req) {
        transactionService.execute(req);
        return ResponseEntity.ok("Successful Transaction");
    }

}
