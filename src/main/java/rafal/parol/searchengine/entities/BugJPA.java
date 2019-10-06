package rafal.parol.searchengine.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "bugs")
public class BugJPA {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @NaturalId
    @Column(name = "bug_id")
    private long bugId;

    @JsonIgnore
    @ManyToOne
    // @MapsId("tester_id")
    @JoinColumn(name = "tester_id")
    private TesterJPA tester;

    @JsonIgnore
    @ManyToOne
    // @MapsId("device_id")
    @JoinColumn(name = "device_id")
    private DeviceJPA device;

    public BugJPA() {

    }

    public BugJPA(long bugId, TesterJPA tester, DeviceJPA device) {
        this.bugId = bugId;
        this.tester = tester;
        this.device = device;
    }

    public BugJPA(long bugId) {
        this.bugId = bugId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBugId() {
        return bugId;
    }

    public void setBugId(long bugId) {
        this.bugId = bugId;
    }

    public TesterJPA getTester() {
        return tester;
    }

    public void setTester(TesterJPA tester) {
        this.tester = tester;
    }

    public DeviceJPA getDevice() {
        return device;
    }

    public void setDevice(DeviceJPA device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "BugJPA{" +
                "id=" + id +
                ", bugId=" + bugId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BugJPA bugJPA = (BugJPA) o;

        return bugId == bugJPA.bugId;
    }

    @Override
    public int hashCode() {
        return (int) (bugId ^ (bugId >>> 32));
    }
}
