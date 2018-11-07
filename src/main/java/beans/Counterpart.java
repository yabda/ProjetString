package beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Counterpart {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "price")
    private int price;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private Project belongProject;

    public Counterpart() {
    }

    public Counterpart(int price, String name, String description) {
        this.price = price;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getBelongProject() {
        return belongProject;
    }

    public void setBelongProjet(Project belongProjet) {
        this.belongProject = belongProjet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Counterpart that = (Counterpart) o;
        return id == that.id &&
                price == that.price &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(belongProject, that.belongProject);
    }

}
