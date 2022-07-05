package entity;

import javax.persistence.*;
import java.util.Collection;

@NamedQuery(name = "TrainsEntity.ByName", query = "SELECT p FROM TrainsEntity p WHERE p.name=?1 ")
@NamedQuery(name = "TrainsEntity.All", query = "SELECT p FROM TrainsEntity p")
@Entity
@Table(name = "trains", schema = "projekcikjava", catalog = "")
public class TrainsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "train_ID")
    private int trainId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "max_weight")
    private int maxWeight;
    @OneToMany(mappedBy = "trainsByTrainId")
    private Collection<TimesEntity> timesByTrainId;

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainsEntity that = (TrainsEntity) o;

        if (trainId != that.trainId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (maxWeight != that.maxWeight) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trainId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + maxWeight;
        return result;
    }

    public Collection<TimesEntity> getTimesByTrainId() {
        return timesByTrainId;
    }

    public void setTimesByTrainId(Collection<TimesEntity> timesByTrainId) {
        this.timesByTrainId = timesByTrainId;
    }


    @Override
    public String toString() {
        return "Train: " + this.getName() + "\t " + this.getType() + "\t " + this.getMaxWeight()+"\n";
    }
}
