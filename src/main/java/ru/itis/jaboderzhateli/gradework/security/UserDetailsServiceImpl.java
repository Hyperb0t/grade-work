package ru.itis.jaboderzhateli.gradework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.jaboderzhateli.gradework.models.User;
import ru.itis.jaboderzhateli.gradework.repositories.UserRepository;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if(login == null || login.equals("")) {
            throw new UsernameNotFoundException("Login is empty.");
        }
        if(!login.trim().equals(login)) {
            throw new UsernameNotFoundException("Unacceptable login format.");
        }
        Optional<User> userOptional = usersRepository.findByLogin(login);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDetailsImpl(user);
        }
        throw new UsernameNotFoundException("User not found.");
    }
}