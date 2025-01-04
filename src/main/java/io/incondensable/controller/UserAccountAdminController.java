package io.incondensable.controller;

import io.incondensable.controller.dto.admin.UserAccountAdminResponseDTO;
import io.incondensable.controller.dto.admin.UserAccountCreateRequestDTO;
import io.incondensable.service.user.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author abbas
 */
@RestController
@RequestMapping("/api/v1.0/admin/user-account")
public class UserAccountAdminController {

    private final UserAccountService userAccountService;

    public UserAccountAdminController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping
    public ResponseEntity<List<UserAccountAdminResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userAccountService.getAllUsers());
    }

    @GetMapping("/id/{userAccountId}")
    public ResponseEntity<UserAccountAdminResponseDTO> getUserAccountById(@PathVariable Long userAccountId) {
        return ResponseEntity.ok(userAccountService.getUserAccountById(userAccountId));
    }

    @GetMapping("/accountNumber/{accountNumber}")
    public ResponseEntity<UserAccountAdminResponseDTO> getUserAccountById(@PathVariable String accountNumber) {
        return ResponseEntity.ok(userAccountService.getUserAccountByAccountNumber(accountNumber));
    }

    @PostMapping
    public ResponseEntity<UserAccountAdminResponseDTO> getUserAccountById(@Validated @RequestBody UserAccountCreateRequestDTO req) {
        return ResponseEntity.ok(userAccountService.createUserAccount(req));
    }

}
