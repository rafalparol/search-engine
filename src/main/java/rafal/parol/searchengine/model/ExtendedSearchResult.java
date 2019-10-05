package rafal.parol.searchengine.model;

import lombok.Data;

@Data
public class ExtendedSearchResult extends BasicSearchResult {
    private String deviceDescription;

    public ExtendedSearchResult(String testerFirstName, String testerLastName, String testerCountry, int experience, String deviceDescription) {
        super(testerFirstName, testerLastName, testerCountry, experience);
        this.deviceDescription = deviceDescription;
    }
}
