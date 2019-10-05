package rafal.parol.searchengine.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import rafal.parol.searchengine.batch.model.Bug;
import rafal.parol.searchengine.batch.model.Device;
import rafal.parol.searchengine.batch.model.Tester;
import rafal.parol.searchengine.batch.model.TesterDevice;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemsListingRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemsListingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tester> getAllTesters() {
        return jdbcTemplate.query("SELECT tester_id, first_name, last_name, country, last_login FROM testers",
                (rs, row) -> new Tester(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5))
        ).stream().collect(Collectors.toList());
    }

    public List<Device> getAllDevices() {
        return jdbcTemplate.query("SELECT device_id, description FROM devices",
                (rs, row) -> new Device(
                        rs.getLong(1),
                        rs.getString(2))
        ).stream().collect(Collectors.toList());
    }

    public List<TesterDevice> getAllTesterDeviceRelations() {
        return jdbcTemplate.query("SELECT tester_id, device_id FROM testers_devices",
                (rs, row) -> new TesterDevice(
                        rs.getLong(1),
                        rs.getLong(2))
        ).stream().collect(Collectors.toList());
    }

    public List<Bug> getAllBugs() {
        return jdbcTemplate.query("SELECT bug_id, device_id, tester_id FROM bugs",
                (rs, row) -> new Bug(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3))
        ).stream().collect(Collectors.toList());
    }
}
