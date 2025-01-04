package io.incondensable.controller;

import io.incondensable.controller.dto.account.AccountInformationResponseDTO;
import io.incondensable.service.balance.AccountBalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author abbas
 */
@RestController
@RequestMapping("/api/v1.0/account")
public class AccountController {

    private final AccountBalanceService accountBalanceService;

    public AccountController(AccountBalanceService accountBalanceService) {
        this.accountBalanceService = accountBalanceService;
    }

    @GetMapping("/info")
    public ResponseEntity<AccountInformationResponseDTO> displayAccountInformation(@RequestParam String accountNumber) {
        return ResponseEntity.ok(accountBalanceService.getAccountInformation(accountNumber));
    }

}
