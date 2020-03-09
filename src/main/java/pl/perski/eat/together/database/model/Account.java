package pl.perski.eat.together.database.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "accounts")
public class Account extends AuditModel {

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


}
