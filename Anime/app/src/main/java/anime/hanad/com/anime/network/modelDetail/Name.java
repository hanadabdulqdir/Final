package anime.hanad.com.anime.network.modelDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hanad on 22/03/2018.
 */
public class Name {

    @SerializedName("default")
    @Expose
    private String _default;
    @SerializedName("english")
    @Expose
    private String english;

    public String getDefault() {
        return _default;
    }

    public void setDefault(String _default) {
        this._default = _default;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

}