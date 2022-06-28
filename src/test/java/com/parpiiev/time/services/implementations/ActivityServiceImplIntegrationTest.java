package com.parpiiev.time.services.implementations;

import com.parpiiev.time.model.Activity;
import com.parpiiev.time.model.Category;
import com.parpiiev.time.repository.ActivityRepository;
import com.parpiiev.time.repository.CategoryRepository;
import com.parpiiev.time.services.interfaces.ActivityService;
import com.parpiiev.time.utils.dto.ActivityDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class ActivityServiceImplIntegrationTest {

    @Autowired
    private ActivityRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeAll
    void setUp() {
        Category category = new Category("test");
        categoryRepository.save(category);
        Activity activity = new Activity("general", category);
        Activity activity1 = new Activity("administration", category);
        Activity activity2 = new Activity("management", category);
        List<Activity> activityList = new ArrayList<>();
        activityList.add(activity);
        activityList.add(activity1);
        activityList.add(activity2);
        repository.saveAll(activityList);
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getByName() {
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
    void findPaginatedSorted() {
    }

    @Test
    void findPaginated() {
    }

    @Test
    void getActivityByCategory_Id() {
    }

    @Test
    void findPaginatedSortedFiltered() {
    }
}