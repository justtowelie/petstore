package pojos;

import lombok.Data;

@Data
public class PetStoreOrderResponsePojo {
    public Integer id;
    public Integer petId;
    public Integer quantity;
    public String shipDate;
    public String status;
    public boolean complete;
}
