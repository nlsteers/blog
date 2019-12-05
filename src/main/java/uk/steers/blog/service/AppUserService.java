package uk.steers.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uk.steers.blog.entity.AppUser;
import uk.steers.blog.exceptions.UsernameTakenException;
import uk.steers.blog.repository.AppUserRepository;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Collections.emptyList;


@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Logger LOGGER = Logger.getLogger(AppUserService.class.getName());

    public String saveUser (AppUser appUser) {
        try {
            if (appUserRepository.findByUsername(appUser.getUsername()) == null) {
                appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
                appUserRepository.save(appUser);
                return String.format("Registered user: %s", appUser.getUsername());
            } else {
                throw new UsernameTakenException(String.format("Could not register %s, username already taken", appUser.getUsername()));
            }
        } catch (UsernameTakenException e) {
            LOGGER.log(Level.WARNING, "Error whilst registering account: username already exists");
            throw e;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(appUser.getUsername(), appUser.getPassword(), emptyList());
    }
}
