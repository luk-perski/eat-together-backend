package pl.perski.eat.together.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.perski.eat.together.database.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
//    @Query(value = "SELECT a.eventHistory FROM Account a WHERE a.id = :id")
//    public String[] getAccountEvents(@Param("id") String accountId);
}
