package eu.clwo.clwo.networkquery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NetworkQueryResult {
    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("Hit")
    @Expose
    private Boolean hit;

    @SerializedName("data")
    @Expose
    private List<Server> data = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public NetworkQueryResult withStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NetworkQueryResult withMessage(String message) {
        this.message = message;
        return this;
    }

    public Boolean getHit() {
        return hit;
    }

    public void setHit(Boolean hit) {
        this.hit = hit;
    }

    public NetworkQueryResult withHit(Boolean hit) {
        this.hit = hit;
        return this;
    }

    public List<Server> getData() {
        return data;
    }

    public void setData(List<Server> data) {
        this.data = data;
    }

    public NetworkQueryResult withData(List<Server> data) {
        this.data = data;
        return this;
    }

}
