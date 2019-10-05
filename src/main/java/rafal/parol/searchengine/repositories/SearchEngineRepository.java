package rafal.parol.searchengine.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import rafal.parol.searchengine.model.BasicSearchResult;
import rafal.parol.searchengine.model.ExtendedSearchResult;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SearchEngineRepository {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private final String sqlBasicString1 = "SELECT t.first_name, t.last_name, t.country, COUNT(*) as experience FROM bugs AS b, testers AS t, devices AS d, testers_devices as r WHERE b.tester_id = t.tester_id AND b.device_id = d.device_id AND r.tester_id = t.tester_id AND r.device_id = d.device_id";
    private final String sqlBasicString4 = " " + "GROUP BY b.tester_id ORDER BY experience DESC";

    private final String sqlExtendedString1 = "SELECT t.first_name, t.last_name, t.country, d.description, COUNT(*) as experience FROM bugs AS b, testers AS t, devices AS d, testers_devices as r WHERE b.tester_id = t.tester_id AND b.device_id = d.device_id AND r.tester_id = t.tester_id AND r.device_id = d.device_id";
    private final String sqlExtendedString4 = " " + "GROUP BY b.tester_id, b.device_id ORDER BY experience DESC";

    private final String sqlStandardString2 = " " + "AND t.country IN (:countries)";
    private final String sqlStandardString3 = " " + "AND d.description IN (:devices)";

    public List<BasicSearchResult> getSearchResults(List<String> countries, List<String> devices, String sqlString1, String sqlString4, RowMapper<BasicSearchResult> mapper) {
        String sqlString = "";
        MapSqlParameterSource parameters = new MapSqlParameterSource();

        sqlString += sqlString1;

        if (!countries.isEmpty()) {
            sqlString += sqlStandardString2;
            parameters.addValue("countries", countries);
        }

        if (!devices.isEmpty()) {
            sqlString += sqlStandardString3;
            parameters.addValue("devices", devices);
        }

        sqlString += sqlString4;

        return namedParameterJdbcTemplate.query(sqlString, parameters, mapper).stream().collect(Collectors.toList());
    }

    public List<BasicSearchResult> getSearchBasicResults(List<String> countries, List<String> devices) {
        return getSearchResults(countries, devices, sqlBasicString1, sqlBasicString4, (rs, row) -> new BasicSearchResult (
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(4)
        ));
    }

    public List<BasicSearchResult> getSearchExtendedResults(List<String> countries, List<String> devices) {
        return getSearchResults(countries, devices, sqlExtendedString1, sqlExtendedString4, (rs, row) -> new ExtendedSearchResult (
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getInt(5),
                rs.getString(4)
        ));
    }
}
