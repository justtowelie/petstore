package pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import enums.Status;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@JsonInclude (JsonInclude.Include.NON_NULL)
public class PetStoreOrderRequestPojo {
    private Integer id;
    private Integer petId;
    private Integer quantity;
    private String shipDate;
    private String status;
    private boolean complete;


    public PetStoreOrderRequestPojo() {
        this.id = 1;
        this.petId = 2;
        this.quantity = 1;
        this.shipDate = String.valueOf(LocalDateTime.now().plusDays(1));
        this.status = Status.PLACED.name();
        this.complete = true;
    }
// missing petId
    public PetStoreOrderRequestPojo(int value){
        this.id = value;
        this.quantity = 1;
        this.shipDate = String.valueOf(LocalDateTime.now().plusDays(1));
        this.status = Status.PLACED.name();
        this.complete = true;
    }

}
