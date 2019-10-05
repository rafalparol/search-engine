package rafal.parol.searchengine.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tester {
    private long testerId;
    private String firstName;
    private String lastName;
    private String country;
    private String lastLogin;
}