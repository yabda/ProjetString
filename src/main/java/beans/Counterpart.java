package beans;

import javax.persistence.*;

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

    @OneToOne
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
}
