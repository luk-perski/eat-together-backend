package pl.perski.eat.together.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.perski.eat.together.database.model.AccountData;

@Repository
public interface AccountRepository extends JpaRepository<AccountData, Integer> {
    AccountData findAccountByUserData(int userData);

    AccountData findAccountByEmail(String email);
}
