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

        User user = new User("John Doe", "MAD Developer", 1, false);
        user.setDescription("MAD Developer");
        user.setId(1);
        user.setFollowed(false);

        // Find the TextView by its ID
        TextView tvName = findViewById(R.id.name);
        TextView tvDescription = findViewById(R.id.desc);
        Button followButton = findViewById(R.id.button);
        Button msgButton = findViewById(R.id.button2);

        // Generate a random integer
        Random random = new Random();
        int min = 100000; // minimum value
        int max = 999999; // maximum value

        int randomNumber = random.nextInt((max - min) + 1) + min;

        Intent intent = getIntent();
        if (intent != null) {
            String name = getIntent().getStringExtra("keyName");
            String desc = getIntent().getStringExtra("keyDesc");
            Boolean foll = getIntent().getBooleanExtra("keyFoll", false);
            tvName.setText(name);
            tvDescription.setText(desc);
            if (foll){
                followButton.setText("Unfollow");
            }
        }
        else {
            tvName.setText(user.getName()+randomNumber);
            tvDescription.setText(user.getDescription());
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