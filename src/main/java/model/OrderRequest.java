package model;

public class OrderRequest {

    private float id;
    private float petId;
    private float quantity;
    private String shipDate;
    private String status;
    private boolean complete;

    public OrderRequest(float id, float petId, float quantity, String shipDate, String status, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }
}
