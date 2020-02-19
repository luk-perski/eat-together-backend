package pl.perski.eat.together.database.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@Entity
@Table(name = "accounts")
public class Account extends AuditModel {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
