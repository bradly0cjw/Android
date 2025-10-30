package ntut.csie.s111590453.hw5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        Button button = findViewById(R.id.button7);
        EditText et1 = findViewById(R.id.editTextText);
        EditText et2 = findViewById(R.id.editTextText2);
        
        // calc bmi
        button.setOnClickListener(v -> {
            //show toast if input is invalid
            if (et1.getText().toString().isEmpty() ) {
                Toast.makeText(this, "請輸入身高", Toast.LENGTH_SHORT).show();
                return;
            }
            if (et2.getText().toString().isEmpty() ) {
                Toast.makeText(this, "請輸入體重", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                double weight = Double.parseDouble(et2.getText().toString());
                double height = Double.parseDouble(et1.getText().toString());
                double bmi = weight / ((height / 100 )* (height / 100));
                // write to text view
                TextView tv = findViewById(R.id.textView4);
                // append text
                String text = tv.getText().toString();
                tv.setText(String.format(Locale.getDefault(), "身高 : %.2f cm, 體重 : %.2f kg, BMI: %.2f\n",height,weight, bmi));
                if (bmi< 18.5) {
                    tv.append("體重過輕");
                } else if (bmi < 25) {
                    tv.append("體重正常");
                } else if (bmi < 30) {
                    tv.append("體重過重");
                } else {
                    tv.append("體重肥胖");
                }
                tv.append("\n");
                tv.append(text);
//               tv.setText(String.format(Locale.getDefault(), "BMI: %.2f", bmi));
            } catch (NumberFormatException e) {
                // handle invalid input
                TextView tv = findViewById(R.id.textView4);
                tv.setText("Invalid input");
            }
        });
    }
}
