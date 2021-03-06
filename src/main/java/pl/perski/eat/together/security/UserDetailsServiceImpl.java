package pl.perski.eat.together.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.perski.eat.together.database.model.AccountData;
import pl.perski.eat.together.database.repository.AccountRepository;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AccountRepository accountRepository;

    public UserDetailsServiceImpl(AccountRepository applicationUserRepository) {
        this.accountRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountData applicationUser = accountRepository.findAccountByEmail(email);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(applicationUser.getEmail(), applicationUser.getPassword(), Collections.emptyList());
    }
}
