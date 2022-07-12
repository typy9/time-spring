package com.parpiiev.time.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import static com.parpiiev.time.controllers.Paths.INDEX_PAGE;
import static com.parpiiev.time.controllers.Paths.LOGOUT_PAGE;
@Slf4j
@Controller
public class LogoutController {

    @GetMapping(LOGOUT_PAGE)
    public RedirectView getLogout(HttpServletRequest request, SessionStatus sessionStatus) {

        log.info("Start LogoutController GET getLogout");

        request.getSession().setAttribute("userInSystem", false);
        request.getSession().invalidate();
        sessionStatus.setComplete();

        return new RedirectView(INDEX_PAGE);
    }
}
