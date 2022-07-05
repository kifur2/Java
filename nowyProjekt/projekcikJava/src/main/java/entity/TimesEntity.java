package entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

@NamedQuery(name = "TimesEntity.ByNames", query = "SELECT p FROM TimesEntity p, TrainsEntity t, StationsEntity s WHERE t.name=?1 and s.name=?2 and p.trainsByTrainId.trainId=t.trainId and p.stationsByStationId.stationId=s.stationId ORDER BY p.arrivalTime")
@NamedQuery(name = "TimesEntity.ByTrainName", query = "SELECT p FROM TimesEntity p, TrainsEntity t WHERE t.name=?1 and p.trainsByTrainId.trainId=t.trainId ORDER BY p.arrivalTime")
@NamedQuery(name = "TimesEntity.ByStationName", query = "SELECT p FROM TimesEntity p, StationsEntity s WHERE s.name=?1 and p.stationsByStationId.stationId=s.stationId ORDER BY p.arrivalTime")
@NamedQuery(name = "TimesEntity.All", query = "SELECT p FROM TimesEntity p")
@Entity
@Table(name = "times", schema = "projekcikjava", catalog = "")
public class TimesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "times_ID")
    private int timesId;
    /*@Basic
    @Column(name = "train_ID")
    private int trainId;
    @Basic
    @Column(name = "station_ID")
    private int stationId;*/
    @Basic
    @Column(name = "arrival_time")
    private Time arrivalTime;
    @Basic
    @Column(name = "departure_time")
    private Time departureTime;
    @Basic
    @Column(name = "platform_num")
    private int platformNum;
    @ManyToOne
    @JoinColumn(name = "train_ID", referencedColumnName = "train_ID", nullable = false)
    private TrainsEntity trainsByTrainId;
    @ManyToOne
    @JoinColumn(name = "station_ID", referencedColumnName = "station_ID", nullable = false)
    private StationsEntity stationsByStationId;

    public int getTimesId() {
        return timesId;
    }

    public void setTimesId(int timesId) {
        this.timesId = timesId;
    }

    /*public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }*/

    public Time getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }

    public int getPlatformNum() {
        return platformNum;
    }

    public void setPlatformNum(int platformNum) {
        this.platformNum = platformNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimesEntity that = (TimesEntity) o;

        if (timesId != that.timesId) return false;
        //if (trainId != that.trainId) return false;
        //if (stationId != that.stationId) return false;
        if (platformNum != that.platformNum) return false;
        if (arrivalTime != null ? !arrivalTime.equals(that.arrivalTime) : that.arrivalTime != null) return false;
        if (departureTime != null ? !departureTime.equals(that.departureTime) : that.departureTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = timesId;
        //result = 31 * result + trainId;
        //result = 31 * result + stationId;
        result = 31 * result + (arrivalTime != null ? arrivalTime.hashCode() : 0);
        result = 31 * result + (departureTime != null ? departureTime.hashCode() : 0);
        result = 31 * result + platformNum;
        return result;
    }

    public TrainsEntity getTrainsByTrainId() {
        return trainsByTrainId;
    }

    public void setTrainsByTrainId(TrainsEntity trainsByTrainId) {
        this.trainsByTrainId = trainsByTrainId;
    }

    public StationsEntity getStationsByStationId() {
        return stationsByStationId;
    }

    public void setStationsByStationId(StationsEntity stationsByStationId) {
        this.stationsByStationId = stationsByStationId;
    }
    @Override
    public String toString() {
        return  this.getStationsByStationId().getName() +"\t"+ this.getTrainsByTrainId().getName() +"\t"+ this.getArrivalTime() + "\t " + this.getDepartureTime() + "\n";
    }
}
