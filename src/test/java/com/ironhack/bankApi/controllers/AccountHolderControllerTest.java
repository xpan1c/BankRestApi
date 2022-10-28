package com.ironhack.bankApi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.ironhack.bankApi.controllers.DTOs.AccountHolderDTO;
import com.ironhack.bankApi.models.utils.Address;
import com.ironhack.bankApi.repositories.AccountHolderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountHolderControllerTest {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());
    private Address primaryAddress;
    private LocalDate birthDay;
    private AccountHolderDTO accountHolder;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        primaryAddress = new Address("Calle falsa", 123, "08211","Cuenca");
        birthDay = LocalDate.of(1990,10,10);
        accountHolder = new AccountHolderDTO("abc","12345", "Juan Pablo");
        accountHolder.setDateOfBirth(birthDay);
        accountHolder.setPrimaryAddress(primaryAddress);
    }
    @Test
    @DisplayName("Check if Account Holder is created correctly")
    void post_AccountHolder_isCreated() throws Exception {
        accountHolder.setUsername("admin");
        String body = objectMapper.writeValueAsString(accountHolder);
        System.err.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/api/newAccountHolder").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andReturn();
        accountHolder.setMailingAddress(primaryAddress);
        accountHolder.setUsername("abc");
        body = objectMapper.writeValueAsString(accountHolder);
        MvcResult mvcResult1 = mockMvc.perform(post("/api/newAccountHolder").content(body)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertTrue(accountHolderRepository.findByUsername("abc").isPresent());
    }
}
