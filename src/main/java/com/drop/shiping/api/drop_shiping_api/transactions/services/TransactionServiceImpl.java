package com.drop.shiping.api.drop_shiping_api.transactions.services;

import com.drop.shiping.api.drop_shiping_api.auth.services.AuthService;
import com.drop.shiping.api.drop_shiping_api.products.entities.Product;
import com.drop.shiping.api.drop_shiping_api.products.repositories.ProductRepository;
import com.drop.shiping.api.drop_shiping_api.transactions.dtos.NewTransactionDTO;
import com.drop.shiping.api.drop_shiping_api.transactions.dtos.TransactionResponseDTO;
import com.drop.shiping.api.drop_shiping_api.transactions.dtos.UserInfoDTO;
import com.drop.shiping.api.drop_shiping_api.transactions.entities.ProductItem;
import com.drop.shiping.api.drop_shiping_api.transactions.entities.Transaction;
import com.drop.shiping.api.drop_shiping_api.transactions.mappers.TransactionMapper;
import com.drop.shiping.api.drop_shiping_api.users.entities.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.drop.shiping.api.drop_shiping_api.transactions.repositories.TransactionRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final AuthService  authService;
    private final TransactionRepository repository;
    private final ProductRepository productRepository;

    public TransactionServiceImpl(AuthService authService, TransactionRepository repository,  ProductRepository productRepository) {
        this.authService = authService;
        this.repository = repository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TransactionResponseDTO> findAllByUser(String token, String userRef, Pageable pageable) {
        Optional<User> userOptional = authService.getUser(token);
        User user = new User();

        if (userOptional.isPresent()) {
            user = userOptional.get();
            userRef = null;
        }

        Page<Transaction> transactions = repository
                .findTransactionsByUser_IdOrUserReference(user.getId(), userRef, pageable);
        return transactions.map(TransactionMapper.MAPPER::transactionToResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransactionResponseDTO> findOne(String id) {
        Optional<Transaction> transaction = repository.findById(id);
        return transaction.map(TransactionMapper.MAPPER::transactionToResponseDTO);
    }

    @Override
    @Transactional
    public String createTransaction(NewTransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setTotalPrice(dto.totalPrice());
        transaction.setTransactionDate(dto.transactionDate());
        transaction.setStatus("PROCESSING");

        dto.products().forEach(product -> {
            Optional<Product> productDB = productRepository.findById(product.productId());
            ProductItem item = new ProductItem();

            productDB.ifPresent(productItem -> {
                item.setProduct(productItem);
                item.setTransaction(transaction);
                item.setQuantity(product.quantity());
                transaction.getProducts().add(item);
            });
        });

        repository.save(transaction);
        return transaction.getId();
    }

    @Override
    @Transactional
    public Map<String, String> addUserInfo(String userReference, HttpServletResponse response, String id,
    UserInfoDTO userDTO, String token) {
        Transaction transaction = repository.findById(id).orElseThrow();
        Optional<User> userOptional = authService.getUser(token);

        if (userOptional.isPresent())
            userOptional.ifPresent(transaction::setUser);

        if (userReference != null && userOptional.isEmpty()) {
            transaction.setUserReference(userReference);
        } else if (userReference == null) {
            String reference = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("userReference", reference);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setMaxAge(60 * 60 * 24 * 365);
            cookie.setPath("/");
            response.addCookie(cookie);

            transaction.setUserReference(reference);
        }

        transaction.setUserEmail(userDTO.userEmail());
        transaction.setUserNames(userDTO.userNames());
        transaction.setUserNumber(String.valueOf(userDTO.userNumber()));
        transaction.setUserAddress(userDTO.userAddress());

        repository.save(transaction);
        return Map.of(
                "userReference",
                Boolean.parseBoolean(transaction.getUserReference())
                        ? transaction.getUserReference()
                        : ""
        );
    }

    @Override
    @Transactional
    public String updateProducts(String id, List<String> productIds) {
        Transaction transaction = repository.findById(id).orElseThrow();
        List<Product> productList = productRepository.findAllById(productIds);



//        transaction.getProducts().removeIf(p -> !productIds.contains(p.getProduct().getId()));

//        transaction.getProducts().removeAll(itemsToRemove);

//        transactionOp.ifPresent(transaction -> {
//            transaction.setProducts(productList);
//            repository.save(transaction);
//        });

        return "";
    }

    @Override
    @Transactional
    public Optional<Transaction> delete(String id) {
        Optional<Transaction> transactionOp = repository.findById(id);
        transactionOp.ifPresent(repository::delete);
        return transactionOp;
    }

    public boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}
