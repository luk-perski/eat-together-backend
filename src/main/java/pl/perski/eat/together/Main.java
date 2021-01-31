package pl.perski.eat.together;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.perski.eat.together.api.v2.dto.account.AccountMapper;
import pl.perski.eat.together.api.v2.dto.account.AccountMapperImpl;
import pl.perski.eat.together.api.v2.dto.event.EventMapperImpl;
import pl.perski.eat.together.api.v2.dto.group.GroupMapperImpl;
import pl.perski.eat.together.api.v2.dto.user.UserMapperImpl;

@EnableJpaAuditing
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication()
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccountMapper accountMapper() {
        return new AccountMapperImpl();
    }

    @Bean
    public EventMapperImpl eventMapper() {
        return new EventMapperImpl();
    }

    @Bean
    public GroupMapperImpl groupMapper() {
        return new GroupMapperImpl();
    }

    @Bean
    public UserMapperImpl userMapper() {
        return new UserMapperImpl();
    }
}


