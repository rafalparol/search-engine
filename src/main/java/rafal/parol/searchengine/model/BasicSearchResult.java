package rafal.parol.searchengine.model;

public class BasicSearchResult {
    private String testerFirstName;
    private String testerLastName;
    private String testerCountry;
    private int experience;

    public BasicSearchResult() {

    }

    public BasicSearchResult(String testerFirstName, String testerLastName, String testerCountry, int experience) {
        this.testerFirstName = testerFirstName;
        this.testerLastName = testerLastName;
        this.testerCountry = testerCountry;
        this.experience = experience;
    }

    public String getTesterFirstName() {
        return testerFirstName;
    }

    public void setTesterFirstName(String testerFirstName) {
        this.testerFirstName = testerFirstName;
    }

    public String getTesterLastName() {
        return testerLastName;
    }

    public void setTesterLastName(String testerLastName) {
        this.testerLastName = testerLastName;
    }

    public String getTesterCountry() {
        return testerCountry;
    }

    public void setTesterCountry(String testerCountry) {
        this.testerCountry = testerCountry;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        // if (!super.equals(o)) return false;

        BasicSearchResult that = (BasicSearchResult) o;

        if (experience != that.experience) return false;
        if (!testerFirstName.equals(that.testerFirstName)) return false;
        if (!testerLastName.equals(that.testerLastName)) return false;
        return testerCountry.equals(that.testerCountry);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + testerFirstName.hashCode();
        result = 31 * result + testerLastName.hashCode();
        result = 31 * result + testerCountry.hashCode();
        result = 31 * result + experience;
        return result;
    }
}
