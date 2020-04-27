package integration.unity.akhil.gamedepot.models;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import integration.unity.akhil.gamedepot.R;

public class User {
    private String name;
    private String email;
    private String phone;
    private String photo;

    public User(String name, String email, String phone, String photo) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.photo = photo;
    }
    @BindingAdapter("dp")
    public static void LoadImage(ImageView imageView, String imageUrl) {
        Log.d("Game Depot : Photo", "" + imageUrl);
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(new RequestOptions().centerCrop()
                        .placeholder(R.drawable.ic_launcher_background))
                .into(imageView);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
