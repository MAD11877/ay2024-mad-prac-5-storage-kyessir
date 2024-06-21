package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);

        // Find the TextView by its ID
        TextView tvName = findViewById(R.id.name);
        TextView tvDescription = findViewById(R.id.desc);
        Button followButton = findViewById(R.id.button);
        Button msgButton = findViewById(R.id.button2);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String description = intent.getStringExtra("name");
        tvName.setText(name);
        tvDescription.setText(description);

        User user = dbHandler.getUser(name);

        if (user.getFollowed()){
            followButton.setText("Unfollow");
        }
        else {
            followButton.setText("Follow");
        }

        // Set an OnClickListener to handle the toggle logic
        followButton.setOnClickListener(v -> {
            // Get the current text of the button
            String currentText = followButton.getText().toString();

            // Toggle the text between "FOLLOW" and "UNFOLLOW"
            if (currentText.equals("FOLLOW")) {
                followButton.setText("UNFOLLOW");
            } else {
                followButton.setText("FOLLOW");
            }
        });

    }
}