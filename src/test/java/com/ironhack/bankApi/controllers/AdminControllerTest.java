package com.ironhack.bankApi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.controllers.DTOs.AccountHolderDTO;
import com.ironhack.bankApi.controllers.DTOs.CreditCardDTO;
import com.ironhack.bankApi.controllers.DTOs.SavingsDTO;
import com.ironhack.bankApi.models.users.AccountHolder;
import com.ironhack.bankApi.models.utils.Address;
import com.ironhack.bankApi.repositories.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
public class AdminControllerTest {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    StudentCheckingAccountRepository studentCheckingAccountRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
SavingsRepository savingsRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private AccountHolder primaryOwner;
    private AccountDTO accountDTO;
    private CreditCardDTO creditCardDTO;
    private SavingsDTO savingsDTO;
    private AccountHolder student;
    private AccountHolder accountHolder;
    private ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());
    PasswordEncoder passwordEncoder;


    @BeforeEach
    public void setUp(){
        passwordEncoder = new BCryptPasswordEncoder();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        accountHolder = new AccountHolder("abcde", passwordEncoder.encode("12345"), "Juan Pablo", LocalDate.of(1990, 10, 10), new Address(), new Address());
        student = new AccountHolder("asde", passwordEncoder.encode("1234"), "Juan Jose", LocalDate.of(2002, 10, 10), new Address(), new Address());
        accountDTO = new AccountDTO(1500.00,1234,2L);
        creditCardDTO = new CreditCardDTO(1234,2L);
        savingsDTO = new SavingsDTO(1500.00,1234,2L);

        //System.err.println(BigDecimal.valueOf(0.0025));
        //System.out.println(BigDecimal.valueOf(savingsDTO.getInterestRate()).compareTo(BigDecimal.valueOf(0.0025)));
    }
    /*
    @AfterEach
    public void tear(){
        studentCheckingAccountRepository.deleteAll();
        checkingAccountRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }
     */


    @Test
    @DisplayName("Check if Checking account created correctly")
    void post_CheckingAccount_isCreated() throws Exception {
        accountHolderRepository.save(accountHolder);
        accountHolderRepository.save(student);
        String body = objectMapper.writeValueAsString(accountDTO);
        System.err.println(body);

        MvcResult mvcResult = mockMvc.perform(post("/api/admin/newCheckingAccount").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        assertEquals(BigDecimal.valueOf(1500.00).setScale(2, RoundingMode.HALF_EVEN),accountRepository.findByMainCheckingAccountList(accountHolderRepository.findByUsername("abcde").get()).get(0).getBalance().getAmount());
    }
    @Test
    @DisplayName("Check if Student account created correctly")
    void post_StudentCheckingAccount_isCreated() throws Exception {
        accountDTO.setPrimaryOwner(3L);
        String body = objectMapper.writeValueAsString(accountDTO);
        System.err.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/api/admin/newCheckingAccount").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        System.err.println(studentCheckingAccountRepository.findAll());
        assertFalse(studentCheckingAccountRepository.findAll().isEmpty());
    }
    @Test
    @DisplayName("Check if CreditCard is created correctly")
    void post_CreditCard_isCreated() throws Exception {
        String body = objectMapper.writeValueAsString(creditCardDTO);
        System.err.println(body);
        MvcResult mvcResult = mockMvc.perform(post("/api/admin/newCreditCard").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        System.err.println(creditCardRepository.findAll());
        assertFalse(creditCardRepository.findAll().isEmpty());
    }
    @Test
    @DisplayName("Check if Savings is created correctly")
    void post_Savings_isCreated() throws Exception {
        String body = objectMapper.writeValueAsString(savingsDTO);
        System.err.println(body);
        System.err.println(BigDecimal.valueOf(0.0025));
        MvcResult mvcResult = mockMvc.perform(post("/api/admin/newSavings").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        System.err.println(savingsRepository.findAll());
        assertFalse(savingsRepository.findAll().isEmpty());
    }
    @Test
    @DisplayName("Test find All users")
    void findAllUsers_OK() throws Exception {
        Address primaryAddress = new Address("Calle falsa", 123, "08211","Cuenca");
        AccountHolderDTO accountHolder = new AccountHolderDTO("abc","12345", "Juan Pablo", LocalDate.of(1990,10,10),primaryAddress);
        String body = objectMapper.writeValueAsString(accountHolder);
        System.err.println(body);

        MvcResult mvcResult = mockMvc.perform(post("/api/newAccountHolder").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        MvcResult mvcResult1 = mockMvc.perform(get("/api/admin/getUsers")).andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult1.getResponse().getContentAsString().contains("abc"));
        System.out.println(mvcResult1.getResponse().getContentAsString());

    }

}
