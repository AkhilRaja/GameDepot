
package integration.unity.akhil.gamedepot.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stores {

    @SerializedName("store")
    @Expose
    private Store store;
    public Store getStore() {
        return store;
    }
    public void setStore(Store store) {
        this.store = store;
    }

}
