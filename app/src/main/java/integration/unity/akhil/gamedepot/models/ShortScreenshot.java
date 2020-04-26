
package integration.unity.akhil.gamedepot.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import integration.unity.akhil.gamedepot.R;

public class ShortScreenshot implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @BindingAdapter("image")
    public static void LoadImage(ImageView imageView,String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().centerCrop()
                        .placeholder(R.drawable.ic_launcher_background))
                .into(imageView);
    }

    protected ShortScreenshot(Parcel in) {
        id = in.readByte() == 0x00 ? null : in.readInt();
        image = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeInt(id);
        }
        dest.writeString(image);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ShortScreenshot> CREATOR = new Parcelable.Creator<ShortScreenshot>() {
        @Override
        public ShortScreenshot createFromParcel(Parcel in) {
            return new ShortScreenshot(in);
        }

        @Override
        public ShortScreenshot[] newArray(int size) {
            return new ShortScreenshot[size];
        }
    };
}