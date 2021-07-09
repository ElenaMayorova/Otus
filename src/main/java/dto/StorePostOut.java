
package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@AllArgsConstructor
@Data
@Builder
@JsonSerialize
public class StorePostOut {

    private Boolean complete;
    private Long id;
    private Long petId;
    private Long quantity;
    private String shipDate;
    private String status;

}
