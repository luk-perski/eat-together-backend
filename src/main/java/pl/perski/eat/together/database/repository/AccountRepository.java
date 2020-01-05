package pl.perski.eat.together.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.perski.eat.together.database.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
