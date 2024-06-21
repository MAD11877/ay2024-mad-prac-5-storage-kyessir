package sg.edu.np.mad.madpractical5;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {

    TextView nameView, descriptionView;
    ImageView smallImgView, bigImgView;
    public UserViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        descriptionView = itemView.findViewById(R.id.description);
        smallImgView = itemView.findViewById(R.id.smallImg);
        bigImgView = itemView.findViewById(R.id.bigImg);

        smallImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                // Set the message show for the Alert time
                builder.setMessage(nameView.getText());

                // Set Alert Title
                builder.setTitle("Profile");

                // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setPositiveButton("VIEW", (DialogInterface.OnClickListener) (dialog, which) -> {
                    Bundle usercredentials = new Bundle();
                    usercredentials.putString("name", nameView.getText().toString());
                    usercredentials.putString("description", descriptionView.getText().toString());
                    Intent MainActivity = new Intent(itemView.getContext(), sg.edu.np.mad.madpractical5.MainActivity.class);
                    MainActivity.putExtras(usercredentials);
                    startActivity(itemView.getContext(), MainActivity, null);


                });

                // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
                builder.setNegativeButton("CLOSE", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // If user click no then dialog box is canceled.
                    dialog.cancel();
                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
            }
        });


    }
}