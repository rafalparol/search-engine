package rafal.parol.searchengine.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import rafal.parol.searchengine.entities.DeviceJPA;

import java.util.List;
import java.util.Optional;

public interface DevicesJPARepository extends CrudRepository<DeviceJPA, Long> {
    Optional<DeviceJPA> findByDeviceId(long deviceId);

    @Query("SELECT d FROM DeviceJPA as d")
    List<DeviceJPA> find();
}
