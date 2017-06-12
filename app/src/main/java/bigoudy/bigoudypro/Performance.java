package bigoudy.bigoudypro;

/**
 * Created by M.C on 06/06/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Performance implements Serializable{

    @SerializedName("idPack")
    @Expose
    private String idPack;
    @SerializedName("idPerformance")
    @Expose
    private String idPerformance;
    @SerializedName("libPerformance")
    @Expose
    private String libPerformance;
    @SerializedName("libPerformanceParent")
    @Expose
    private String libPerformanceParent;
    @SerializedName("libPerformanceType")
    @Expose
    private String libPerformanceType;
    @SerializedName("numPers")
    @Expose
    private String numPers;
    @SerializedName("isATechnicalPerformance")
    @Expose
    private String isATechnicalPerformance;

    public String getIdPack() {
        return idPack;
    }

    public void setIdPack(String idPack) {
        this.idPack = idPack;
    }

    public String getIdPerformance() {
        return idPerformance;
    }

    public void setIdPerformance(String idPerformance) {
        this.idPerformance = idPerformance;
    }

    public String getLibPerformance() {
        return libPerformance;
    }

    public void setLibPerformance(String libPerformance) {
        this.libPerformance = libPerformance;
    }

    public String getLibPerformanceParent() {
        return libPerformanceParent;
    }

    public void setLibPerformanceParent(String libPerformanceParent) {
        this.libPerformanceParent = libPerformanceParent;
    }

    public String getLibPerformanceType() {
        return libPerformanceType;
    }

    public void setLibPerformanceType(String libPerformanceType) {
        this.libPerformanceType = libPerformanceType;
    }

    public String getNumPers() {
        return numPers;
    }

    public void setNumPers(String numPers) {
        this.numPers = numPers;
    }

    public String getIsATechnicalPerformance() {
        return isATechnicalPerformance;
    }

    public void setIsATechnicalPerformance(String isATechnicalPerformance) {
        this.isATechnicalPerformance = isATechnicalPerformance;
    }

}
