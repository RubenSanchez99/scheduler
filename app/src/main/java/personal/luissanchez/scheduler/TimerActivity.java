package personal.luissanchez.scheduler;

import personal.luissanchez.scheduler.MainActivity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimerActivity extends AppCompatActivity {
    private static final String tag = "MainActivity";
    private myCountDownTimer countDownTimer;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button btnStart;
    private TextView timerText;
    private TextView timeElapsedView;
    private TextView taskTitleView;

    private long startTime;
    private long interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        Intent intent = getIntent();
        String taskTitle = intent.getStringExtra("task");
        String taskTime = intent.getStringExtra("time");

        long taskTimeMillis = Long.valueOf(taskTime) * 60 * 1000;

        startTime = taskTimeMillis;
        interval = 1000;

        timerText = (TextView) this.findViewById(R.id.timer);
        timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);

        countDownTimer = new myCountDownTimer(startTime, interval);
        timerText.setText(timerText.getText() + String.valueOf(startTime));

        taskTitleView = (TextView) this.findViewById(R.id.textViewTask);
        taskTitleView.setText(taskTitle);

        if (!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
        }
        else {
            countDownTimer.cancel();
            timerHasStarted = false;
        }
    }

    public class myCountDownTimer extends CountDownTimer {
        public myCountDownTimer(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onFinish() {
            timerText.setText("Time's up!");
            timeElapsedView.setText("Time Elapsed: " + String.valueOf(getDateFromMillis(startTime)));
            // TODO: onFinish should mark task as complete


        }

        @Override
        public void onTick(long millisUntilFinished) {
            timerText.setText(getDateFromMillis(millisUntilFinished));
            timeElapsed = startTime - millisUntilFinished;
            timeElapsedView.setText(String.valueOf(getDateFromMillis(timeElapsed)));
        }
    }

    public static String getDateFromMillis(long millis) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        df.setTimeZone(tz);
        String time = df.format(new Date(millis));
        return time;
    }
}
