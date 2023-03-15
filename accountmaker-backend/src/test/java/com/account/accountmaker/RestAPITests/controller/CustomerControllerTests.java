package com.account.accountmaker.RestAPITests.controller;

import com.account.accountmaker.model.Account;
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
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class CustomerControllerTests {
    private static final String CUSTOMER_ENDPOINT = "/v1/customer";
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
    public void testGetCustomer() throws Exception {
        this.mockMvc.perform(get(CUSTOMER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restAPIResponseStatus", is("Success")))
                .andExpect(jsonPath("$.message", is("Customers retrieved successfully")))
                .andExpect(jsonPath("$.response", hasSize(5)));
    }


    @Test
    public void testGetCustomerById() throws Exception {
        this.mockMvc.perform(get(CUSTOMER_ENDPOINT + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restAPIResponseStatus", is("Success")))
                .andExpect(jsonPath("$.message", is("Customer retrieved successfully")))
                .andExpect(jsonPath("$.response.customerId", is(1)))
                .andExpect(jsonPath("$.response.name", is("JILL")))
                .andExpect(jsonPath("$.response.surname", is("DOE")))
                .andExpect(jsonPath("$.response.account", hasSize(0)))
        ;
    }

    @Test
    public void testGetCustomerById_whenAccountAddedWithTransaction_ReturnCustomer() throws Exception {
        CreateAccountRequest request = getCreateAccountRequest(1L, new BigDecimal(100));
        createAccount(request);

        this.mockMvc.perform(get(CUSTOMER_ENDPOINT + "/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restAPIResponseStatus", is("Success")))
                .andExpect(jsonPath("$.message", is("Customer retrieved successfully")))
                .andExpect(jsonPath("$.response.customerId", is(1)))
                .andExpect(jsonPath("$.response.name", is("JILL")))
                .andExpect(jsonPath("$.response.surname", is("DOE")))
                .andExpect(jsonPath("$.response.account", hasSize(1)))
                .andExpect(jsonPath("$.response.account[0].transaction", hasSize(1)))
        ;
    }

    private void createAccount(CreateAccountRequest request) throws Exception {
        this.mockMvc.perform(post(ACCOUNT_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)));
    }

    private CreateAccountRequest getCreateAccountRequest(Long id, BigDecimal initialCredit) {
        CreateAccountRequest request = new CreateAccountRequest(initialCredit, id);
        return request;
    }
}
