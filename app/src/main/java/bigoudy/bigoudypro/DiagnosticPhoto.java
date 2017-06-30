
package bigoudy.bigoudypro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DiagnosticPhoto implements Serializable {

    @SerializedName("idImage")
    @Expose
    public String idImage;
    @SerializedName("linkImageHD")
    @Expose
    public String linkImageHD;
    @SerializedName("numPers")
    @Expose
    public String numPers;

    public String getIdImage() {
        return idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }

    public String getLinkImageHD() {
        return linkImageHD;
    }

    public void setLinkImageHD(String linkImageHD) {
        this.linkImageHD = linkImageHD;
    }

    public String getNumPers() {
        return numPers;
    }

    public void setNumPers(String numPers) {
        this.numPers = numPers;
    }
}