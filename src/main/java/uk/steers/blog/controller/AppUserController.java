package uk.steers.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.steers.blog.entity.AppUser;
import uk.steers.blog.exceptions.UsernameTakenException;
import uk.steers.blog.service.AppUserService;

@RestController
@RequestMapping("/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser appUser) throws UsernameTakenException {
        return new ResponseEntity<>(appUserService.saveUser(appUser), HttpStatus.CREATED);
    }
}
