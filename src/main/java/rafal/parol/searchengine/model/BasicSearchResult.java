package rafal.parol.searchengine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicSearchResult {
    private String testerFirstName;
    private String testerLastName;
    private String testerCountry;
    private int experience;
}
