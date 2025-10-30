package ntut.csie.s111590453.hw7;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.color.MaterialColors;

public class MainActivity extends AppCompatActivity {

    int sum =0;
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
        RadioGroup rg = findViewById(R.id.rgItem);
        RadioGroup rg2 = findViewById(R.id.rgCH);
        RadioGroup rg3 = findViewById(R.id.rgSG);
        EditText et = findViewById(R.id.etQu);
        TextView tv = findViewById(R.id.tvResult);
        tv.append("\n");

        markSelectRadioButtonRed(rg,rg2,rg3);

        Button btndrink = findViewById(R.id.btndrink);
        Button btnclac = findViewById(R.id.btncalc);
        Button btnclr = findViewById(R.id.btnclr);

        btndrink.setOnClickListener(v -> {
            addDrink(rg,rg2,rg3,et,tv);

        });
        btnclac.setOnClickListener(v -> {
            calcPrice(tv);
        });
        btnclr.setOnClickListener(v -> {
            clearSelect(rg,rg2,rg3,et,tv);
        });

    }

    public void markSelectRadioButtonRed(RadioGroup rg,RadioGroup rg2,RadioGroup rg3) {
        markSelectRadioButtonRedHelper(rg);
        markSelectRadioButtonRedHelper(rg2);
        markSelectRadioButtonRedHelper(rg3);
    }


    public void markSelectRadioButtonRedHelper(RadioGroup rg) {
        final int defaultColor = MaterialColors.getColor(MainActivity.this, com.google.android.material.R.attr.colorOnSurface, Color.BLACK);
        rg.setOnCheckedChangeListener((group, checkedId) -> {
            for (int i = 0; i < group.getChildCount(); i++) {
                RadioButton rb = (RadioButton) group.getChildAt(i);
                if (rb.getId() == checkedId) {
                    rb.setTextColor(Color.RED);
                } else {
                    rb.setTextColor(defaultColor);
                }
            }
        });
    }


    public void clearSelect(RadioGroup rg,RadioGroup rg2,RadioGroup rg3,EditText et,TextView tv) {
        clearSelectHelper(rg);
        clearSelectHelper(rg2);
        clearSelectHelper(rg3);

        final int defaultColor = MaterialColors.getColor(this, com.google.android.material.R.attr.colorOnSurface, Color.BLACK);
        resetRadioGroupTextColor(rg, defaultColor);
        resetRadioGroupTextColor(rg2, defaultColor);
        resetRadioGroupTextColor(rg3, defaultColor);

        et.setText("1");
        tv.setText("訂購飲料:\n");
        sum = 0;
    }
    public void clearSelectHelper(RadioGroup rg) {
        rg.clearCheck();
    }
    private void resetRadioGroupTextColor(RadioGroup group, int color) {
        for (int i = 0; i < group.getChildCount(); i++) {
            RadioButton rb = (RadioButton) group.getChildAt(i);
            rb.setTextColor(color);
        }
    }

    public Pair<String, Integer> getDrinkName(RadioGroup rg1) {
        int selectedItemId = rg1.getCheckedRadioButtonId();
        if (selectedItemId == R.id.rbItem1) {
            return new Pair<>("美式咖啡", 80);
        } else if (selectedItemId == R.id.rbItem3) {
            return new Pair<>("木瓜牛奶", 90);
        } else if (selectedItemId == R.id.rbItem2) {
            return new Pair<>("莊園拿鐵", 75);
        } else if (selectedItemId == R.id.rbItem4) {
            return new Pair<>("甘蔗汁", 60);
        }
        return new Pair<>("", 0); // Return a default pair if nothing is selected
    }

    public String getTemperature(RadioGroup rg2) {
        int temperatureId = rg2.getCheckedRadioButtonId();
        if (temperatureId == R.id.rbCH1) {
            return "冰";
        } else if (temperatureId == R.id.rbCH2) {
            return "熱";
        } else if (temperatureId == R.id.rbCH3) {
            return "少冰";
        } else if (temperatureId == R.id.rbCH4){
            return "去冰";
        }
        return ""; // Return an empty string if nothing is selected
    }

    public String getSugar(RadioGroup rg3) {
        int sugarId = rg3.getCheckedRadioButtonId();
        if(sugarId == R.id.rbSG1){
            return "全糖";
        }else if(sugarId == R.id.rbSG2){
            return "半糖";
        }else if(sugarId == R.id.rbSG3){
            return "無糖";
        }
        return "";
    }




    public void addDrink(RadioGroup rg1,RadioGroup rg2,RadioGroup rg3,EditText et,TextView tv){
        String quantityText = et.getText().toString();
        if (quantityText.isEmpty()) {
            Toast.makeText(this, "請輸入數量", Toast.LENGTH_SHORT).show();
            return;
        }
        int quantity = Integer.parseInt(quantityText);
        if (quantity <= 0) {
            Toast.makeText(this, "數量必須大於0", Toast.LENGTH_SHORT).show();
            return;
        }
        if (rg1.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "請選擇飲料", Toast.LENGTH_SHORT).show();
            return;
        }
        if (rg2.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "請選擇冰熱", Toast.LENGTH_SHORT).show();
            return;
        }
        if (rg3.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "請選擇糖度", Toast.LENGTH_SHORT).show();
            return;
        }
        String drinkName = "";
        int drinkPrice = 0;
        String temperature = "";
        String sugar = "";

        Pair<String, Integer> drinkInfo = getDrinkName(rg1);
        drinkName = drinkInfo.first;
        drinkPrice = drinkInfo.second;
        temperature= getTemperature(rg2);
        sugar= getSugar(rg3);

        int totalPrice = drinkPrice * quantity;
        String result = drinkName + "("+ temperature + "," + sugar + ")" + " x " + quantity + " =>" + totalPrice + "\n" ;
        tv.append(result);
        sum+=totalPrice;

    }

    public void calcPrice(TextView tv){
        tv.append("合計:" + sum +"元\n");
    }
}
