package io.incondensable.service.transaction;

import io.incondensable.global.LoggerObserver;
import io.incondensable.model.TransactionTypeEnum;
import io.incondensable.model.entity.AccountBalance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;

/**
 * @author abbas
 */
@Service
@PropertySource("classpath:custom-configs.properties")
public class FileTransactionLogger implements LoggerObserver {

    @Value("${transaction.log.file.path}")
    private String filePath;

    @Override
    public void onTransaction(String accountNumber, Double amount, TransactionTypeEnum transactionType) {
        String logMessage = String.format("%s - Transaction Type: %s, Amount: %.2f, AccountNumber: %s%s\n",
                LocalDateTime.now(),
                transactionType,
                amount,
                accountNumber);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(logMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
