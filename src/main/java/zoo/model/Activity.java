package zoo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Activity {
    @Id
    private Long id;

    @Size(max = 64)
    public String title;

    public String description;

    public void setId(Long id) {
        this.id = id;
    }





    public Long getId() {
        return id;
    }
}
