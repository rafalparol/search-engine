package rafal.parol.searchengine;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import rafal.parol.searchengine.model.BasicSearchResult;
import rafal.parol.searchengine.model.ExtendedSearchResult;
import rafal.parol.searchengine.services.LoadDataService;
import rafal.parol.searchengine.services.SearchEngineService;

import static org.junit.Assert.assertArrayEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchEngineApplicationTests {

    @Autowired
    private SearchEngineService searchEngineService;

    @Autowired
    private LoadDataService loadDataService;

    @Before
    public void loadData() throws Exception {
        loadDataService.loadData();
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void searchEngineServiceCalculateCountriesToSendUnitTests() {
        String oneCountry[] = new String[]{"GB"};
        String oneCountryString = "GB";

        assertArrayEquals(searchEngineService.calculateCountriesToSend(oneCountryString).toArray(), oneCountry);

        String manyCountries[] = new String[]{"GB", "UK"};
        String manyCountriesString = "GB,UK";

        assertArrayEquals(searchEngineService.calculateCountriesToSend(manyCountriesString).toArray(), manyCountries);

        String manyCountriesWithAll[] = new String[]{};
        String manyCountriesWithAllString = "GB,UK,ALL";

        assertArrayEquals(searchEngineService.calculateCountriesToSend(manyCountriesWithAllString).toArray(), manyCountriesWithAll);

        String allCountries[] = new String[]{};
        String allCountriesString = "ALL";

        assertArrayEquals(searchEngineService.calculateCountriesToSend(allCountriesString).toArray(), allCountries);
    }

    @Test
    public void searchEngineServiceCalculateDevicesToSendUnitTests() {
        String oneDevice[] = new String[]{"iPhone 5"};
        String oneDeviceString = "iPhone 5";

        assertArrayEquals(searchEngineService.calculateDevicesToSend(oneDeviceString).toArray(), oneDevice);

        String manyDevices[] = new String[]{"iPhone 5", "Galaxy S3"};
        String manyDevicesString = "iPhone 5,Galaxy S3";

        assertArrayEquals(searchEngineService.calculateDevicesToSend(manyDevicesString).toArray(), manyDevices);

        String manyDevicesWithAll[] = new String[]{};
        String manyDevicesWithAllString = "iPhone 5,Galaxy S3,ALL";

        assertArrayEquals(searchEngineService.calculateDevicesToSend(manyDevicesWithAllString).toArray(), manyDevicesWithAll);

        String allDevices[] = new String[]{};
        String allDevicesString = "ALL";

        assertArrayEquals(searchEngineService.calculateDevicesToSend(allDevicesString).toArray(), allDevices);
    }

    @Test
    public void searchEngineServiceBasicSearchResultsAllCountriesAllDevicesIntegrationTest() {
        BasicSearchResult basic1 = new BasicSearchResult("Taybin", "Rutkin", "US", 125);
        BasicSearchResult basic2 = new BasicSearchResult("Lucas", "Lowry", "JP", 117);
        BasicSearchResult basic3 = new BasicSearchResult("Sean", "Wellington", "JP", 116);
        BasicSearchResult basic4 = new BasicSearchResult("Miguel", "Bautista", "US", 114);
        BasicSearchResult basic5 = new BasicSearchResult("Stanley", "Chen", "GB", 110);
        BasicSearchResult basic6 = new BasicSearchResult("Mingquan", "Zheng", "JP", 109);
        BasicSearchResult basic7 = new BasicSearchResult("Leonard", "Sutton", "GB", 106);
        BasicSearchResult basic8 = new BasicSearchResult("Darshini", "Thiagarajan", "GB", 104);
        BasicSearchResult basic9 = new BasicSearchResult("Michael", "Lubavin", "US", 99);
        BasicSearchResult[] basicResults = new BasicSearchResult[] {basic1, basic2, basic3, basic4, basic5, basic6, basic7, basic8, basic9};

        assertArrayEquals(searchEngineService.getBasicSearchResults("ALL", "ALL").toArray(), basicResults);
    }

    @Test
    public void searchEngineServiceExtendedSearchResultsAllCountriesAllDevicesIntegrationTest() {
        ExtendedSearchResult extended1 = new ExtendedSearchResult("Stanley", "Chen", "GB", 110, "iPhone 5");
        ExtendedSearchResult extended2 = new ExtendedSearchResult("Taybin", "Rutkin", "US", 66, "iPhone 4");
        ExtendedSearchResult extended3 = new ExtendedSearchResult("Taybin", "Rutkin", "US", 59, "iPhone 4S");
        ExtendedSearchResult extended4 = new ExtendedSearchResult("Mingquan", "Zheng", "JP", 36, "Droid Razor");
        ExtendedSearchResult extended5 = new ExtendedSearchResult("Miguel", "Bautista", "US", 35, "iPhone 3");
        ExtendedSearchResult extended6 = new ExtendedSearchResult("Leonard", "Sutton", "GB", 32, "iPhone 5");
        ExtendedSearchResult extended7 = new ExtendedSearchResult("Miguel", "Bautista", "US", 30, "iPhone 5");
        ExtendedSearchResult extended8 = new ExtendedSearchResult("Sean", "Wellington", "JP", 30, "iPhone 5");
        ExtendedSearchResult extended9 = new ExtendedSearchResult("Darshini", "Thiagarajan", "GB", 30, "HTC One");
        ExtendedSearchResult extended10 = new ExtendedSearchResult("Leonard", "Sutton", "GB", 28, "Galaxy S3");
        ExtendedSearchResult extended11 = new ExtendedSearchResult("Lucas", "Lowry", "JP", 28, "Galaxy S3");
        ExtendedSearchResult extended12 = new ExtendedSearchResult("Sean", "Wellington", "JP", 28, "iPhone 4");
        ExtendedSearchResult extended13 = new ExtendedSearchResult("Darshini", "Thiagarajan", "GB", 28, "Nexus 4");
        ExtendedSearchResult extended14 = new ExtendedSearchResult("Leonard", "Sutton", "GB", 27, "Nexus 4");
        ExtendedSearchResult extended15 = new ExtendedSearchResult("Miguel", "Bautista", "US", 26, "iPhone 4S");
        ExtendedSearchResult extended16 = new ExtendedSearchResult("Lucas", "Lowry", "JP", 25, "Nexus 4");
        ExtendedSearchResult extended17 = new ExtendedSearchResult("Darshini", "Thiagarajan", "GB", 25, "Droid DNA");
        ExtendedSearchResult extended18 = new ExtendedSearchResult("Michael", "Lubavin", "US", 24, "Galaxy S3");
        ExtendedSearchResult extended19 = new ExtendedSearchResult("Miguel", "Bautista", "US", 23, "iPhone 4");
        ExtendedSearchResult extended20 = new ExtendedSearchResult("Sean", "Wellington", "JP", 23, "Nexus 4");
        ExtendedSearchResult extended21 = new ExtendedSearchResult("Lucas", "Lowry", "JP", 22, "Galaxy S4");
        ExtendedSearchResult extended22 = new ExtendedSearchResult("Mingquan", "Zheng", "JP", 21, "iPhone 4");
        ExtendedSearchResult extended23 = new ExtendedSearchResult("Lucas", "Lowry", "JP", 21, "Droid Razor");
        ExtendedSearchResult extended24 = new ExtendedSearchResult("Lucas", "Lowry", "JP", 21, "Droid DNA");
        ExtendedSearchResult extended25 = new ExtendedSearchResult("Darshini", "Thiagarajan", "GB", 21, "Galaxy S4");
        ExtendedSearchResult extended26 = new ExtendedSearchResult("Mingquan", "Zheng", "JP", 20, "Galaxy S4");
        ExtendedSearchResult extended27 = new ExtendedSearchResult("Michael", "Lubavin", "US", 19, "Galaxy S4");
        ExtendedSearchResult extended28 = new ExtendedSearchResult("Leonard", "Sutton", "GB", 19, "Galaxy S4");
        ExtendedSearchResult extended29 = new ExtendedSearchResult("Mingquan", "Zheng", "JP", 19, "iPhone 3");
        ExtendedSearchResult extended30 = new ExtendedSearchResult("Sean", "Wellington", "JP", 18, "iPhone 3");
        ExtendedSearchResult extended31 = new ExtendedSearchResult("Michael", "Lubavin", "US", 17, "HTC One");
        ExtendedSearchResult extended32 = new ExtendedSearchResult("Sean", "Wellington", "JP", 17, "HTC One");
        ExtendedSearchResult extended33 = new ExtendedSearchResult("Michael", "Lubavin", "US", 16, "Droid Razor");
        ExtendedSearchResult extended34 = new ExtendedSearchResult("Mingquan", "Zheng", "JP", 13, "Nexus 4");
        ExtendedSearchResult extended35 = new ExtendedSearchResult("Michael", "Lubavin", "US", 12, "Droid DNA");
        ExtendedSearchResult extended36 = new ExtendedSearchResult("Michael", "Lubavin", "US", 11, "Nexus 4");
        ExtendedSearchResult[] extendedResults = new ExtendedSearchResult[] {extended1, extended2, extended3, extended4, extended5, extended6, extended7, extended8, extended9, extended10,
                                                                             extended11, extended12, extended13, extended14, extended15, extended16, extended17, extended18, extended19, extended20,
                                                                             extended21, extended22, extended23, extended24, extended25, extended26, extended27, extended28, extended29, extended30,
                                                                             extended31, extended32, extended33, extended34, extended35, extended36};

        assertArrayEquals(searchEngineService.getExtendedSearchResults("ALL", "ALL").toArray(), extendedResults);
    }

    @Test
    public void searchEngineServiceBasicSearchResultsOneCountryOneDeviceIntegrationTest() {
        BasicSearchResult basic1 = new BasicSearchResult("Stanley", "Chen", "GB", 110);
        BasicSearchResult basic2 = new BasicSearchResult("Leonard", "Sutton", "GB", 32);
        BasicSearchResult[] basicResults = new BasicSearchResult[] {basic1, basic2};

        assertArrayEquals(searchEngineService.getBasicSearchResults("GB", "iPhone 5").toArray(), basicResults);
    }

    @Test
    public void searchEngineServiceExtendedSearchResultsOneCountryOneDeviceIntegrationTest() {
        ExtendedSearchResult extended1 = new ExtendedSearchResult("Stanley", "Chen", "GB", 110, "iPhone 5");
        ExtendedSearchResult extended2 = new ExtendedSearchResult("Leonard", "Sutton", "GB", 32, "iPhone 5");
        ExtendedSearchResult[] extendedResults = new ExtendedSearchResult[] {extended1, extended2};

        assertArrayEquals(searchEngineService.getExtendedSearchResults("GB", "iPhone 5").toArray(), extendedResults);
    }

    @Test
    public void searchEngineServiceBasicSearchResultsManyCountriesManyDevicesIntegrationTest() {
        BasicSearchResult basic1 = new BasicSearchResult("Stanley", "Chen", "GB", 110);
        BasicSearchResult basic2 = new BasicSearchResult("Sean", "Wellington", "JP", 58);
        BasicSearchResult basic3 = new BasicSearchResult("Leonard", "Sutton", "GB", 32);
        BasicSearchResult basic4 = new BasicSearchResult("Mingquan", "Zheng", "JP", 21);
        BasicSearchResult[] basicResults = new BasicSearchResult[] {basic1, basic2, basic3, basic4};

        assertArrayEquals(searchEngineService.getBasicSearchResults("GB,JP", "iPhone 5,iPhone 4").toArray(), basicResults);
    }

    @Test
    public void searchEngineServiceExtendedSearchResultsManyCountriesManyDevicesIntegrationTest() {
        ExtendedSearchResult extended1 = new ExtendedSearchResult("Stanley", "Chen", "GB", 110, "iPhone 5");
        ExtendedSearchResult extended2 = new ExtendedSearchResult("Leonard", "Sutton", "GB", 32, "iPhone 5");
        ExtendedSearchResult extended3 = new ExtendedSearchResult("Sean", "Wellington", "JP", 30, "iPhone 5");
        ExtendedSearchResult extended4 = new ExtendedSearchResult("Sean", "Wellington", "JP", 28, "iPhone 4");
        ExtendedSearchResult extended5 = new ExtendedSearchResult("Mingquan", "Zheng", "JP", 21, "iPhone 4");
        ExtendedSearchResult[] extendedResults = new ExtendedSearchResult[] {extended1, extended2, extended3, extended4, extended5};

        assertArrayEquals(searchEngineService.getExtendedSearchResults("GB,JP", "iPhone 5,iPhone 4").toArray(), extendedResults);
    }
}