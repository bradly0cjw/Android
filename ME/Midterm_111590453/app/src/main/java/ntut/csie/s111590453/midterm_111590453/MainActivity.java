package ntut.csie.s111590453.midterm_111590453;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.appcompat.app.AlertDialog;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

class Score {
    String name;
    int[] score;
}
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

        String rawScore =
                "成婉財 27 91 21 33 13\n" +
                        "翁雅婷 96 90 40 55 69\n" +
                        "袁維茹 38 85 72 13 34\n" +
                        "黃士哲 81 40 24 93 79\n" +
                        "郭珮珊 72 33 32 83 73\n" +
                        "陳儀琬 78 55 22 41 62\n" +
                        "李碧彥 30 48 13 93 70\n" +
                        "梁健玉 23 89 10 44 24\n" +
                        "許雅淑 90 11 33 27 67\n" +
                        "蕭宛新 29 64 64 90 43\n" +
                        "張竣崴 81 64 52 73 98";

        String[] lines = rawScore.split("\n");
        ArrayList<Score> scores = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.trim().split("\\s+");
            Score s = new Score();
            s.name = parts[0];
            s.score = new int[5];
            for (int i = 0; i < 5; i++) {
                s.score[i] = Integer.parseInt(parts[i + 1]);
            }
            scores.add(s);
        }

        AtomicInteger currentIndex = new AtomicInteger(-1);
        Button btnAll = findViewById(R.id.btnAll);
        btnAll.setOnClickListener(v -> {
            StringBuilder sb = new StringBuilder();
            for (Score s : scores) {
                sb.append(s.name).append(" ");
                for (int i = 0; i < 5; i++) {
                    sb.append(s.score[i]).append(" ");
                }
                sb.append("\n");
            }
            String result = sb.toString();
            TextView textView2 = findViewById(R.id.textView2);
            textView2.setText(result);
        });

        Button btnFirst = findViewById(R.id.btnFirst);
        //show first data
        btnFirst.setOnClickListener(v -> {
            var sumScore = 0;
            StringBuilder sb = new StringBuilder();
            currentIndex.set(0);
            sb.append(scores.get(0).name).append(" ");
            for (int i = 0; i < 5; i++) {
                sb.append(scores.get(0).score[i]).append(" ");
                sumScore += scores.get(0).score[i];
            }
            sb.append("=> ").append(sumScore).append("\n");
            String result = sb.toString();
            TextView textView2 = findViewById(R.id.textView2);
            textView2.setText(result);
        });

        Button btnLast = findViewById(R.id.btnLast);
        //show last data
        btnLast.setOnClickListener(v -> {
            var sumScore = 0;
            StringBuilder sb = new StringBuilder();
            currentIndex.set(scores.size() - 1);
            sb.append(scores.get(scores.size() - 1).name).append(" ");
            for (int i = 0; i < 5; i++) {
                sb.append(scores.get(scores.size() - 1).score[i]).append(" ");
                sumScore += scores.get(scores.size() - 1).score[i];
            }
            sb.append("=> ").append(sumScore).append("\n");
            String result = sb.toString();
            TextView textView2 = findViewById(R.id.textView2);
            textView2.setText(result);
        });

        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(v -> {
            var sumScore = 0;
            StringBuilder sb = new StringBuilder();
            if (currentIndex.get() < scores.size() - 1) {
                int nextIndex = currentIndex.incrementAndGet();
                Score s = scores.get(nextIndex);
                sb.append(s.name).append(" ");
                for (int i = 0; i < 5; i++) {
                    sb.append(s.score[i]).append(" ");
                    sumScore += s.score[i];
                }
                sb.append("=> ").append(sumScore).append("\n");
                currentIndex.set(nextIndex);
                String result = sb.toString();
                TextView textView2 = findViewById(R.id.textView2);
                textView2.setText(result);
            } else {
                Toast.makeText(this, "已到達最後一筆！", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnPrev = findViewById(R.id.btnPrev);
        btnPrev.setOnClickListener(v -> {
            var sumScore = 0;
            StringBuilder sb = new StringBuilder();
            if (currentIndex.get() > 0) {
                int prevIndex = currentIndex.decrementAndGet();
                Score s = scores.get(prevIndex);
                sb.append(s.name).append(" ");
                for (int i = 0; i < 5; i++) {
                    sb.append(s.score[i]).append(" ");
                    sumScore += s.score[i];
                }
                sb.append("=> ").append(sumScore).append("\n");
                currentIndex.set(prevIndex);
                String result = sb.toString();
                TextView textView2 = findViewById(R.id.textView2);
                textView2.setText(result);
            } else {
                Toast.makeText(this, "已到達第一筆！", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnOrderby = findViewById(R.id.btnOrderby);
        EditText etOrderby = findViewById(R.id.editTextText);
        CheckBox chbAll = findViewById(R.id.chbAll);
        btnOrderby.setOnClickListener(v -> {
            String input = etOrderby.getText().toString();
            if (input.isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("來自 張竣崴 的提醒！")
                        .setMessage("請先輸入科目！！")
                        .setPositiveButton("確定", null)
                        .show();
                return;

            }

            int subjectIndex;
            try {
                subjectIndex = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                new AlertDialog.Builder(this)
                        .setTitle("來自 張竣崴 的提醒！")
                        .setMessage("無此科目！")
                        .setPositiveButton("確定", null)
                        .show();
                return;
            }

            if (subjectIndex < 1 || subjectIndex > 5) {
                new AlertDialog.Builder(this)
                        .setTitle("來自 張竣崴 的提醒！")
                        .setMessage("無此科目！")
                        .setPositiveButton("確定", null)
                        .show();
                return;
            }

            ArrayList<Score> sortedScores = new ArrayList<>(scores);
            SortByIndex(subjectIndex - 1, sortedScores);

            StringBuilder sb = new StringBuilder();
            sb.append("第").append(subjectIndex).append("科的成績排序：\n");
            for (Score s : sortedScores) {
                sb.append(s.name).append(" ");
                if (chbAll.isChecked()) {
                    for (int i = 0; i < 5; i++) {
                        if (i == subjectIndex - 1) {
                            sb.append("(").append(s.score[i]).append(") ");
                        } else {
                            sb.append(s.score[i]).append(" ");
                        }
                    }
                } else {
                    sb.append(s.score[subjectIndex - 1]);
                }
                sb.append("\n");
            }

            TextView textView2 = findViewById(R.id.textView2);
            textView2.setText(sb.toString());
        });
    }
    protected void SortByIndex(int index, ArrayList<Score> scores) {
        scores.sort( new Comparator<Score>() {
            @Override
            public int compare(Score s1, Score s2) {
                return Integer.compare(s2.score[index], s1.score[index]);
            }
        });
    }
}