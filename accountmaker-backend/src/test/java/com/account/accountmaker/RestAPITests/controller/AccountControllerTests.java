package com.account.accountmaker.RestAPITests.controller;

import com.account.accountmaker.repository.AccountRepository;
import com.account.accountmaker.repository.CustomerRepository;
import com.account.accountmaker.request.CreateAccountRequest;
import com.account.accountmaker.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext
public class AccountControllerTests {
    private static final String ACCOUNT_ENDPOINT = "/v1/account";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    @Autowired
    public CustomerRepository customerRepository;


    @Autowired
    public AccountRepository accountRepository;

    public final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void init() {
    }

    @Test
    public void testCreateAccount_whenCustomerExists_ReturnCreatedAccount() throws Exception {
        CreateAccountRequest request = getCreateAccountRequest(1L, new BigDecimal(0));

        this.mockMvc.perform(post(ACCOUNT_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request))
                .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restAPIResponseStatus", is("Success")))
                .andExpect(jsonPath("$.message", is("Account created successfully")))
                .andExpect(jsonPath("$.response.accountId", notNullValue()))
                .andExpect(jsonPath("$.response.initialCredit", is(0)))
                .andExpect(jsonPath("$.response.transaction", nullValue()));
    }

    @Test
    public void testCreateAccount_whenCustomerExistsAndInitialCreditIsNotZero_ReturnCreatedAccountWithTransactions() throws Exception {
        CreateAccountRequest request = getCreateAccountRequest(1L, new BigDecimal(100));

        this.mockMvc.perform(post(ACCOUNT_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request))
                .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restAPIResponseStatus", is("Success")))
                .andExpect(jsonPath("$.message", is("Account created successfully")))
                .andExpect(jsonPath("$.response.accountId", notNullValue()))
                .andExpect(jsonPath("$.response.initialCredit", is(100)))
                .andExpect(jsonPath("$.response.transaction", hasSize(1)));
    }

    @Test
    public void testCreateAccount_whenCustomerDoesNotExists_ReturnCreatedAccountWithTransactions() throws Exception {
        CreateAccountRequest request = getCreateAccountRequest(20L, new BigDecimal(100));

        this.mockMvc.perform(post(ACCOUNT_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request))
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restAPIResponseStatus", is("Error")))
                .andExpect(jsonPath("$.message", is("No value present")))
                .andExpect(jsonPath("$.response", nullValue()));
    }


    private CreateAccountRequest getCreateAccountRequest(Long id, BigDecimal initialCredit) {
        CreateAccountRequest request = new CreateAccountRequest(initialCredit, id);
        return request;
    }


}
