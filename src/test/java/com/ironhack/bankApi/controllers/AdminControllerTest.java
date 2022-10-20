package com.ironhack.bankApi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.models.AccountHolder;
import com.ironhack.bankApi.models.Address;
import com.ironhack.bankApi.repositories.AccountHolderRepository;
import com.ironhack.bankApi.repositories.CheckingAccountRepository;
import com.ironhack.bankApi.repositories.StudentCheckingAccountRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
public class AdminControllerTest {
    @Autowired
    CheckingAccountRepository checkingAccountRepository;
    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private AccountHolder primaryOwner;
    private AccountDTO accountDTO;
    private AccountHolder student;
    private AccountHolder accountHolder;
    private ObjectMapper objectMapper = new ObjectMapper();



    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        accountHolder = new AccountHolder("abc", "12345", "Juan Pablo", LocalDate.of(1990, 10, 10), new Address(), new Address());
        student = new AccountHolder("asde", "1234", "Juan Jose", LocalDate.of(2002, 10, 10), new Address(), new Address());
        accountDTO = new AccountDTO(BigDecimal.valueOf(1500),1234,1L,BigDecimal.valueOf(1200.00).setScale(2, RoundingMode.HALF_EVEN), BigDecimal.valueOf(20.00).setScale(2, RoundingMode.HALF_EVEN));
    }
    @AfterEach
    public void tear(){
        studentCheckingAccountRepository.deleteAll();
        checkingAccountRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }


    @Test
    @DisplayName("Check if Checking account created correctly")
    void post_CheckingAccount_isCreated() throws Exception {
        accountHolderRepository.save(accountHolder);
        accountHolderRepository.save(student);
        String body = objectMapper.writeValueAsString(accountDTO);
        System.err.println(body);

        MvcResult mvcResult = mockMvc.perform(post("/api/admin/newCheckingAccount").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertFalse(studentCheckingAccountRepository.findById(1L).isPresent());
        assertTrue(checkingAccountRepository.findById(1L).isPresent());
    }
    @Test
    @DisplayName("Check if Student account created correctly")
    void post_StudentCheckingAccount_isCreated() throws Exception {
        accountHolderRepository.save(accountHolder);
        accountHolderRepository.save(student);
        accountDTO.setPrimaryOwner(4L);
        String body = objectMapper.writeValueAsString(accountDTO);
        System.err.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/api/admin/newCheckingAccount").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        System.err.println(studentCheckingAccountRepository.findAll().get(0).getId());
        assertTrue(studentCheckingAccountRepository.findById(2L).isPresent());
        assertFalse(checkingAccountRepository.findById(2L).isPresent());
    }
}
