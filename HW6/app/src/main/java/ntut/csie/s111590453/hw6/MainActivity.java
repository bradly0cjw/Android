package ntut.csie.s111590453.hw6;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.color.MaterialColors;

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
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioGroup rg2 = findViewById(R.id.radioGroup2);
        EditText et = findViewById(R.id.editTextNumber);
        TextView tv = findViewById(R.id.textView2);

        // change selected rg button text color
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            final int defaultColor = MaterialColors.getColor(MainActivity.this, com.google.android.material.R.attr.colorOnSurface, Color.BLACK);
            for (int i = 0; i < group.getChildCount(); i++) {
                RadioButton rb = (RadioButton) group.getChildAt(i);
                if (rb.getId() == checkedId) {
                    rb.setTextColor(Color.RED);
                } else {
                    rb.setTextColor(defaultColor);
                }
            }
        });

        // make order
        findViewById(R.id.button).setOnClickListener(v -> {
            int selectedItemId = radioGroup.getCheckedRadioButtonId();
            int temperatureId = rg2.getCheckedRadioButtonId();
            String quantityText = et.getText().toString();

            if (selectedItemId == -1 || temperatureId == -1 || TextUtils.isEmpty(quantityText)) {
                Toast.makeText(this, "Please make a selection and enter a quantity", Toast.LENGTH_SHORT).show();
                return;
            }

            int quantity = Integer.parseInt(quantityText);

            String order = "";
            if (selectedItemId == R.id.radioButton) {
                order += "美式咖啡 ";
            } else if (selectedItemId == R.id.radioButton2) {
                order += "木瓜牛奶 ";
            } else if (selectedItemId == R.id.radioButton5) {
                order += "莊園拿鐵 ";
            }

            if (temperatureId == R.id.radioButton3) {
                order += "冰";
            } else if (temperatureId == R.id.radioButton4) {
                order += "少冰";
            } else if (temperatureId == R.id.radioButton6) {
                order += "熱";
            } else if (temperatureId == R.id.radioButton7) {
                order += "去冰";
            }

            order += " " + quantity + "杯";
            tv.append("\n" + order);


        });


    }

}
