package eu.clwo.clwo.networkquery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Server {


    @SerializedName("ServerID")
    @Expose
    private String serverID;

    @SerializedName("SI")
    @Expose
    private String sI;

    @SerializedName("SP")
    @Expose
    private String sP;

    @SerializedName("Hostname")
    @Expose
    private String hostname;

    @SerializedName("ShortName")
    @Expose
    private String shortName;

    @SerializedName("Prefix")
    @Expose
    private String prefix;

    @SerializedName("Port")
    @Expose
    private String port;

    @SerializedName("MaxPlayers")
    @Expose
    private String maxPlayers;

    @SerializedName("ClientCount")
    @Expose
    private String clientCount;

    @SerializedName("AdvertiseOthers")
    @Expose
    private String advertiseOthers;

    @SerializedName("AdvertiseMyself")
    @Expose
    private String advertiseMyself;

    @SerializedName("MapName")
    @Expose
    private String mapName;

    @SerializedName("MapDisplayName")
    @Expose
    private String mapDisplayName;

    @SerializedName("Validated")
    @Expose
    private String validated;

    @SerializedName("LastUpdate")
    @Expose
    private String lastUpdate;

    @SerializedName("LastUpdateStamp")
    @Expose
    private String lastUpdateStamp;

    @SerializedName("LastSeen")
    @Expose
    private String lastSeen;

    @SerializedName("qrd")
    @Expose
    private Qrd qrd;

    public String getServerID() {
        return serverID;
    }

    public void setServerID(String serverID) {
        this.serverID = serverID;
    }

    public Server withServerID(String serverID) {
        this.serverID = serverID;
        return this;
    }

    public String getSI() {
        return sI;
    }

    public void setSI(String sI) {
        this.sI = sI;
    }

    public Server withSI(String sI) {
        this.sI = sI;
        return this;
    }

    public String getSP() {
        return sP;
    }

    public void setSP(String sP) {
        this.sP = sP;
    }

    public Server withSP(String sP) {
        this.sP = sP;
        return this;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Server withHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Server withShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Server withPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Server withPort(String port) {
        this.port = port;
        return this;
    }

    public String getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(String maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Server withMaxPlayers(String maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }

    public String getClientCount() {
        return clientCount;
    }

    public void setClientCount(String clientCount) {
        this.clientCount = clientCount;
    }

    public Server withClientCount(String clientCount) {
        this.clientCount = clientCount;
        return this;
    }

    public String getAdvertiseOthers() {
        return advertiseOthers;
    }

    public void setAdvertiseOthers(String advertiseOthers) {
        this.advertiseOthers = advertiseOthers;
    }

    public Server withAdvertiseOthers(String advertiseOthers) {
        this.advertiseOthers = advertiseOthers;
        return this;
    }

    public String getAdvertiseMyself() {
        return advertiseMyself;
    }

    public void setAdvertiseMyself(String advertiseMyself) {
        this.advertiseMyself = advertiseMyself;
    }

    public Server withAdvertiseMyself(String advertiseMyself) {
        this.advertiseMyself = advertiseMyself;
        return this;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public Server withMapName(String mapName) {
        this.mapName = mapName;
        return this;
    }

    public String getMapDisplayName() {
        return mapDisplayName;
    }

    public void setMapDisplayName(String mapDisplayName) {
        this.mapDisplayName = mapDisplayName;
    }

    public Server withMapDisplayName(String mapDisplayName) {
        this.mapDisplayName = mapDisplayName;
        return this;
    }

    public String getValidated() {
        return validated;
    }

    public void setValidated(String validated) {
        this.validated = validated;
    }

    public Server withValidated(String validated) {
        this.validated = validated;
        return this;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Server withLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public String getLastUpdateStamp() {
        return lastUpdateStamp;
    }

    public void setLastUpdateStamp(String lastUpdateStamp) {
        this.lastUpdateStamp = lastUpdateStamp;
    }

    public Server withLastUpdateStamp(String lastUpdateStamp) {
        this.lastUpdateStamp = lastUpdateStamp;
        return this;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Server withLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
        return this;
    }

    public Qrd getQrd() {
        return qrd;
    }

    public void setQrd(Qrd qrd) {
        this.qrd = qrd;
    }

    public Server withQrd(Qrd qrd) {
        this.qrd = qrd;
        return this;
    }

}