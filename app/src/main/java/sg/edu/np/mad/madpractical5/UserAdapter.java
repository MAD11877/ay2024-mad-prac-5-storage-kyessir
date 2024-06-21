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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private ListActivity activity;

    public UserAdapter(List<User> userList, ListActivity activity) {
        this.userList = userList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_activity_list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.nameView.setText(user.name);
        holder.descriptionView.setText(user.description);

        // Set a placeholder image for the profile.
        holder.smallImgView.setImageResource(R.drawable.ic_launcher_foreground);
        holder.bigImgView.setImageResource(R.drawable.ic_launcher_foreground);

        // Follow button logic can be added here if needed
        holder.smallImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);

                // Set the dialog title and message
                builder.setTitle("Profile");
                builder.setMessage(user.getName());

                // Set the positive button and its click listener
                builder.setPositiveButton("View", (dialog, which) -> {
                    Intent i = new Intent(activity, MainActivity.class);
                    i.putExtra("keyName", user.getName());
                    i.putExtra("keyDesc", user.getDescription());
                    i.putExtra("keyFoll", user.getFollowed());
                    activity.startActivity(i);

                });

                // Set the negative button and its click listener
                builder.setNegativeButton("Close", (dialog, which) -> {
                    // Do something when the negative button is clicked
                    dialog.dismiss();
                });

                // Create and show the AlertDialog
                AlertDialog dialog = builder.create();
            }
        });


        char num = user.name.charAt(user.name.length() - 1);
        Log.d("num", String.valueOf(num));
        if (num == '7') {
            holder.bigImgView.setVisibility(View.VISIBLE);
        }
        else {
            holder.bigImgView.setVisibility(View.GONE);
        }

        /* private void showAlertDialog() {
            // Create an AlertDialog.Builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Set the dialog title and message
            builder.setTitle("Profile");
            builder.setMessage("MADness");

            // Set the positive button and its click listener
            builder.setPositiveButton("View", (dialog, which) -> {
                // Create an Intent to start the MainActivity
                Intent intent = new Intent(this, MainActivity.class);
                // Start the MainActivity
                this.startActivity(intent);
                // Do something when the positive button is clicked
                dialog.dismiss();
            });

            // Set the negative button and its click listener
            builder.setNegativeButton("Close", (dialog, which) -> {
                // Do something when the negative button is clicked
                dialog.dismiss();
            });

            // Create and show the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
        } */
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameView, descriptionView;
        ImageView smallImgView, bigImgView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
            descriptionView = itemView.findViewById(R.id.description);
            smallImgView = itemView.findViewById(R.id.smallImg);
            bigImgView = itemView.findViewById(R.id.bigImg);
        }
    }
}
