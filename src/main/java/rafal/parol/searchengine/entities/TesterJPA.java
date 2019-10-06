package rafal.parol.searchengine.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "testers")
public class TesterJPA {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @NaturalId
    @Column(name = "tester_id")
    private long testerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "country")
    private String country;
    @Column(name = "last_login")
    private String lastLogin;

    @JsonIgnore
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(
        name = "testers_devices",
        joinColumns = @JoinColumn(name = "tester_id"),
        inverseJoinColumns = @JoinColumn(name = "device_id")
    )
    private Set<DeviceJPA> devices = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "tester")
    private List<BugJPA> bugs = new ArrayList<>();

    public TesterJPA() {

    }

    public TesterJPA(long testerId, String firstName, String lastName, String country, String lastLogin, Set<DeviceJPA> devices, List<BugJPA> bugs) {
        this.testerId = testerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.lastLogin = lastLogin;
        this.devices = devices;
        this.bugs = bugs;
    }

    public TesterJPA(long testerId, String firstName, String lastName, String country, String lastLogin) {
        this.testerId = testerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.lastLogin = lastLogin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTesterId() {
        return testerId;
    }

    public void setTesterId(long testerId) {
        this.testerId = testerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<DeviceJPA> getDevices() {
        return devices;
    }

    public void setDevices(Set<DeviceJPA> devices) {
        this.devices = devices;
    }

    public List<BugJPA> getBugs() {
        return bugs;
    }

    public void setBugs(List<BugJPA> bugs) {
        this.bugs = bugs;
    }

    @Override
    public String toString() {
        return "TesterJPA{" +
                "id=" + id +
                ", testerId=" + testerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TesterJPA testerJPA = (TesterJPA) o;

        return testerId == testerJPA.testerId;
    }

    @Override
    public int hashCode() {
        return (int) (testerId ^ (testerId >>> 32));
    }
}
