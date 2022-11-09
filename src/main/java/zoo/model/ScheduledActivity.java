package zoo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ScheduledActivity {
    private Long id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
