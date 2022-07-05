package entity;

import javax.persistence.*;
import java.util.Collection;


@NamedQuery(name = "StationsEntity.ByName", query = "SELECT p FROM StationsEntity p WHERE p.name=?1 ")
@NamedQuery(name = "StationsEntity.All", query = "SELECT p FROM StationsEntity p")
@Entity
@Table(name = "stations", schema = "projekcikjava", catalog = "")
public class StationsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "station_ID")
    private int stationId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "platforms_number")
    private int platformsNumber;
    @OneToMany(mappedBy = "stationsByStationId")
    private Collection<TimesEntity> timesByStationId;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlatformsNumber() {
        return platformsNumber;
    }

    public void setPlatformsNumber(int platformsNumber) {
        this.platformsNumber = platformsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StationsEntity that = (StationsEntity) o;

        if (stationId != that.stationId) return false;
        if (platformsNumber != that.platformsNumber) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stationId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + platformsNumber;
        return result;
    }

    public Collection<TimesEntity> getTimesByStationId() {
        return timesByStationId;
    }

    public void setTimesByStationId(Collection<TimesEntity> timesByStationId) {
        this.timesByStationId = timesByStationId;
    }

    @Override
    public String toString() {
        return "Station: " + this.getName() + "\t " + this.getPlatformsNumber() + "\n";
    }
}
