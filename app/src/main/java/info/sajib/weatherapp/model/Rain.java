package info.sajib.weatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sajib on 25-07-2016.
 */
public class Rain  {
    public Float getProbability() {
        return probability;
    }

    public void setProbability(Float probability) {
        this.probability = probability;
    }

    @SerializedName("3h")
    Float probability;
}
