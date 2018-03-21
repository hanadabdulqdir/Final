package anime.hanad.com.anime.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hanad on 14/03/2018.
 */

public class RelatedAnime {//extends RealmObject {

        @SerializedName("Prequel")
        @Expose
        private String prequel;
        @SerializedName("Sequel")
        @Expose
        private String sequel;
        @SerializedName("Other")
        @Expose
        private String other;
        @SerializedName("Alternative setting")
        @Expose
        private String alternativeSetting;

        public String getPrequel() {
            return prequel;
        }

        public void setPrequel(String prequel) {
            this.prequel = prequel;
        }

        public String getSequel() {
            return sequel;
        }

        public void setSequel(String sequel) {
            this.sequel = sequel;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public String getAlternativeSetting() {
            return alternativeSetting;
        }

        public void setAlternativeSetting(String alternativeSetting) {
            this.alternativeSetting = alternativeSetting;
        }
}
