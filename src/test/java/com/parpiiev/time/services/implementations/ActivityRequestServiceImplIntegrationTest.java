package com.parpiiev.time.services.implementations;

import com.parpiiev.time.repository.ActivityRepository;
import com.parpiiev.time.repository.ActivityRequestRepository;
import com.parpiiev.time.services.interfaces.ActivityRequestService;
import com.parpiiev.time.services.interfaces.ActivityService;
import com.parpiiev.time.utils.dto.ActivityDTO;
import com.parpiiev.time.utils.dto.ActivityRequestDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class ActivityRequestServiceImplIntegrationTest {

    @Autowired
    private ActivityRequestRepository repository;
    @Autowired
    private ActivityRequestService<ActivityRequestDTO> service;


    @BeforeAll
    void setUp() {
//        Category category = new Category(1, "general");
//        Category category1 = new Category(2, "administration");
//        Category category2 = new Category(3, "management");
//        List<Category> categoryList = new ArrayList<>();
//        categoryList.add(category);
//        categoryList.add(category1);
//        categoryList.add(category2);
//        repository.saveAll(categoryList);
    }


    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAllByUserId() {
    }
}