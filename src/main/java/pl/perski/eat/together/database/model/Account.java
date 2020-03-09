package pl.perski.eat.together.database.model;

import javax.persistence.*;


@Entity
@Table(name = "accounts")
public class Account extends AuditModel {

    @Column(name = "event_history")
    String eventHistory;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Lob
//    @Column(name = "event_history", columnDefinition = "text[]")
//    private String[] eventHistory;
//
//    public String[] getEventHistory() {
//        return eventHistory;
//    }
//
//    public void setEventHistory(String[] eventHistory) {
//        this.eventHistory = eventHistory;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventHistory() {
        return eventHistory;
    }

    public void setEventHistory(String eventHistory) {
        this.eventHistory = eventHistory;
    }
}
