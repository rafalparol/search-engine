package rafal.parol.searchengine.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "devices")
public class DeviceJPA {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @NaturalId
    @Column(name = "device_id")
    private long deviceId;
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "devices")
    private Set<TesterJPA> testers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "device")
    private List<BugJPA> bugs = new ArrayList<>();

    public DeviceJPA() {

    }

    public DeviceJPA(long deviceId, String description, Set<TesterJPA> testers, List<BugJPA> bugs) {
        this.deviceId = deviceId;
        this.description = description;
        this.testers = testers;
        this.bugs = bugs;
    }

    public DeviceJPA(long deviceId, String description) {
        this.deviceId = deviceId;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<TesterJPA> getTesters() {
        return testers;
    }

    public void setTesters(Set<TesterJPA> testers) {
        this.testers = testers;
    }

    public List<BugJPA> getBugs() {
        return bugs;
    }

    public void setBugs(List<BugJPA> bugs) {
        this.bugs = bugs;
    }

    @Override
    public String toString() {
        return "DeviceJPA{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceJPA deviceJPA = (DeviceJPA) o;

        return deviceId == deviceJPA.deviceId;
    }

    @Override
    public int hashCode() {
        return (int) (deviceId ^ (deviceId >>> 32));
    }
}
