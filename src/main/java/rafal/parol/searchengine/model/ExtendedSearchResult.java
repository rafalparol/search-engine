package rafal.parol.searchengine.model;

public class ExtendedSearchResult extends BasicSearchResult {
    private String deviceDescription;

    public ExtendedSearchResult() {
        super();
    }

    public ExtendedSearchResult(String testerFirstName, String testerLastName, String testerCountry, int experience, String deviceDescription) {
        super(testerFirstName, testerLastName, testerCountry, experience);
        this.deviceDescription = deviceDescription;
    }

    public String getDeviceDescription() {
        return deviceDescription;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        // if (!super.equals(o)) return false;

        ExtendedSearchResult that = (ExtendedSearchResult) o;

        return deviceDescription.equals(that.deviceDescription);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + deviceDescription.hashCode();
        return result;
    }
}
