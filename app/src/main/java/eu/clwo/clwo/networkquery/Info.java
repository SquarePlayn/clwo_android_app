package eu.clwo.clwo.networkquery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("gameDir")
    @Expose
    private String gameDir;

    @SerializedName("numberOfPlayers")
    @Expose
    private String numberOfPlayers;


    public String getGameDir() {
        return gameDir;
    }

    public void setGameDir(String gameDir) {
        this.gameDir = gameDir;
    }

    public Info withGameDir(String gameDir) {
        this.gameDir = gameDir;
        return this;
    }

    public String getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setnumberOfPlayers(String numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Info withnumberOfPlayers(String numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        return this;
    }
}
