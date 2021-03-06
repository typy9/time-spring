package com.parpiiev.time.controllers;

import com.parpiiev.time.exceptions.activity.ActivityAlreadyExistsException;
import com.parpiiev.time.services.interfaces.ActivityService;
import com.parpiiev.time.services.interfaces.CategoryService;
import com.parpiiev.time.utils.dto.ActivityDTO;
import com.parpiiev.time.utils.dto.CategoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.parpiiev.time.controllers.Paths.*;

@RequiredArgsConstructor @Slf4j
@Controller
public class ActivityController {

    private final ActivityService<ActivityDTO> activityService;
    private final CategoryService<CategoryDTO> categoryService;
    private static final String MODEL_ATTRIBUTE_ACTIVITIES = "activities";

    @GetMapping(ALL_ACTIVITIES_PAGE)
    public String listActivities(Model model, HttpSession session,
                                 @RequestParam("page") Optional<Integer> page,
                                 @RequestParam("size") Optional<Integer> size,
                                 @RequestParam(defaultValue = "id") String sortBy,
                                 @RequestParam(defaultValue = "") Optional<Integer> filterByCategory) {

        log.debug("Start ActivityController GET listActivities");

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(4);

        if (filterByCategory.isPresent()) {

            List<Optional<ActivityDTO>> filteredActivities = activityService
                    .getActivityByCategoryId(filterByCategory.get());
            List<ActivityDTO> activities = filteredActivities
                    .stream()
                    .map(Optional::get).toList();
            model.addAttribute(MODEL_ATTRIBUTE_ACTIVITIES, activities);
            session.setAttribute("filterByCategory", filterByCategory.get());
        } else {

            Page<ActivityDTO> activityPage = getActivityDTOS(sortBy, currentPage, pageSize);

            List<ActivityDTO> activities = activityPage.getContent();

            model.addAttribute(MODEL_ATTRIBUTE_ACTIVITIES, activities);
            model.addAttribute("activityPage", activityPage);

            int totalPages = activityPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }

        }

        model.addAttribute("currentPage", currentPage);
        List<CategoryDTO> category = new ArrayList<>();
        model.addAttribute("category", category);
        List<CategoryDTO> categories =  categoryService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("activityDTO", new ActivityDTO());

        return ALL_ACTIVITIES_FILE;
    }

    @GetMapping(ACTIVITIES_DELETE_PAGE)
    public String deleteActivity(@PathVariable("id") int id, Model model) {

        log.debug("Start ActivityController GET deleteActivity");

        Optional<ActivityDTO> activity = activityService.getById(id);

        if(activity.isPresent()) {
            activityService.delete(id);
            List<ActivityDTO> activities = activityService.getAll();
            model.addAttribute(MODEL_ATTRIBUTE_ACTIVITIES, activities);
        }
        return BACK_TO_ALL_ACTIVITIES_PAGE;
    }

    @PostMapping(ALL_ACTIVITIES_PAGE)
    public String addActivity(Model model, @ModelAttribute @Valid ActivityDTO activityDTO,
                          BindingResult bindingResult, @RequestParam int categoryId){

        log.debug("Start UserController GET addActivity");

        if (bindingResult.hasErrors()) {

            List<ActivityDTO> activities =  activityService.getAll();
            model.addAttribute(MODEL_ATTRIBUTE_ACTIVITIES, activities);
            return ALL_ACTIVITIES_PAGE;
        }

        String name = activityDTO.getName();


        if (name == null || categoryId <= 0) {

            model.addAttribute("isInvalidData", true);
            return ALL_ACTIVITIES_PAGE;
        }

        boolean isSaved;

        try {
            activityDTO.setActivity_category_id(categoryId);
            isSaved = activityService.save(activityDTO);

        } catch (ActivityAlreadyExistsException e) {
            model.addAttribute("isUserExists", true);
            log.warn("Such activity already exists", e);
            return BACK_TO_ALL_ACTIVITIES_PAGE;
        }

        if (isSaved) {
            model.addAttribute("activityRegistered", true);
        }

        List<ActivityDTO> activities =  activityService.getAll();
        model.addAttribute(MODEL_ATTRIBUTE_ACTIVITIES, activities);
        return BACK_TO_ALL_ACTIVITIES_PAGE;
    }

    @GetMapping(ACTIVITIES_UPDATE_PAGE)
    public String updateActivity(@PathVariable("id") int id, Model model){
        log.debug("Start ACTIVITYController GET updateActivity");

        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setActivity_id(id);
        model.addAttribute("activityDTO", activityDTO);
        List<CategoryDTO> category = new ArrayList<>();
        model.addAttribute("category", category);
        List<CategoryDTO> categories =  categoryService.getAll();
        model.addAttribute("categories", categories);
        return ACTIVITY_UPDATE_FILE;
    }


    @PostMapping(ACTIVITIES_UPDATE_PAGE)
    public String updateActivity(@PathVariable("id") int id, @Valid ActivityDTO activityDTO,
                                 BindingResult bindingResult, Model model,
                                 @RequestParam int categoryId) {

        log.info("Start ActivityController POST updateActivity");
        log.debug("ID is : {}", id);
        log.debug("ActivityDTO retrieved from the form is {}", activityDTO);

        if (bindingResult.hasErrors()) {
            return BACK_TO_ALL_ACTIVITIES_PAGE;
        }

        activityDTO.setActivity_id(id);
        activityDTO.setActivity_category_id(categoryId);
        activityService.update(activityDTO);

        List<ActivityDTO> activities = activityService.getAll();
        model.addAttribute(MODEL_ATTRIBUTE_ACTIVITIES, activities);
        return BACK_TO_ALL_ACTIVITIES_PAGE;
    }

    private Page<ActivityDTO> getActivityDTOS(String sortBy, int currentPage, int pageSize) {
        if (sortBy.equals("users")) {
            return activityService
                    .findPaginatedSortByUsers(PageRequest.of(currentPage - 1, pageSize));
        }
        return activityService.findPaginatedSorted(PageRequest.of(currentPage - 1, pageSize), sortBy);
    }
}
