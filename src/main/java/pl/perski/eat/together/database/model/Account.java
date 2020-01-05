package pl.perski.eat.together.database.model;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account extends AuditModel {
    @Id
    @GeneratedValue(generator = "account_generator")
    @SequenceGenerator(
            name = "account_generator",
            sequenceName = "account_sequence"
    )
    private Long id;

    @Column(name = "event_history")
    private String[] eventHistory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getEventHistory() {
        return eventHistory;
    }

    public void setEventHistory(String[] eventHistory) {
        this.eventHistory = eventHistory;
    }
}
