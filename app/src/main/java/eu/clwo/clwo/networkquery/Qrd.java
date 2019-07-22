package eu.clwo.clwo.networkquery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Qrd {


    @SerializedName("info")
    @Expose
    private Info info;

    @SerializedName("ping")
    @Expose
    private Integer ping;


    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Qrd withInfo(Info info) {
        this.info = info;
        return this;
    }

    public Integer getPing() {
        return ping;
    }

    public void setPing(Integer ping) {
        this.ping = ping;
    }

    public Qrd withPing(Integer ping) {
        this.ping = ping;
        return this;
    }

}