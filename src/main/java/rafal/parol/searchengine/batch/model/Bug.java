package rafal.parol.searchengine.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bug {
    private long bugId;
    private long deviceId;
    private long testerId;
}
