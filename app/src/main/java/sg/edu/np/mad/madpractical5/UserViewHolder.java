package sg.edu.np.mad.madpractical5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        bigImgView.setVisibility(View.GONE);

    }
}