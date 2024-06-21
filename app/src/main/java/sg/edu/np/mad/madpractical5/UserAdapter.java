package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private List<User> userList;

    public UserAdapter(List<User> userList, ListActivity activity) {
        this.userList = userList;
    }

    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_activity_list, parent, false);
        UserViewHolder holder = new UserViewHolder(view);
        return new UserViewHolder(view);
    }

    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.nameView.setText(user.name);
        holder.descriptionView.setText(user.description);

        // Set a placeholder image for the profile.
        holder.smallImgView.setImageResource(R.drawable.ic_launcher_foreground);
        holder.bigImgView.setImageResource(R.drawable.ic_launcher_foreground);

    }

    public int getItemCount() {
        return userList.size();
    }

}
