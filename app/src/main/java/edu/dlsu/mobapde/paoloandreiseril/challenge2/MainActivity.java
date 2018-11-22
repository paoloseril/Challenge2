package edu.dlsu.mobapde.paoloandreiseril.challenge2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int[] images = new int[]{R.drawable.bird_twitter,
            R.drawable.dinosaur_rex, R.drawable.donkey, R.drawable.horse_head,
    R.drawable.hound,R.drawable.parrot_head, R.drawable.sea_serpent, R.drawable.squid};

    private ChatAdapter adapter;

    private BroadcastReceiver receiver = new ChatMockupReceiver();
    private static final String UI_UPDATE_TAG = "ph.mobapde.challenge2";
    AlarmManager alarmManager;
    private static int JOB_ID = 10000;

    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();

        RecyclerView recyclerView = findViewById(R.id.recycler_area);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        adapter = new ChatAdapter();
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        ((LinearLayoutManager) manager).setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(UI_UPDATE_TAG);
        registerReceiver(receiver, filter);

        performChat();
    }

    private void performChat() {
        int image = images[random.nextInt(8)];
        String preset = getImageName(image).concat(String.valueOf(random.nextInt(4) + 1));
        String message = getResourceFromString(preset);
        int time = 100;

        switch (image) {
            case R.drawable.bird_twitter:
                time = 2000;
                break;
            case R.drawable.dinosaur_rex:
                time = 8000;
                break;
            case R.drawable.donkey:
                time = 4000;
                break;
            case R.drawable.horse_head:
                time = 3000;
                break;
            case R.drawable.hound:
                time = 1000;
                break;
            case R.drawable.parrot_head:
                time = 5000;
                break;
            case R.drawable.sea_serpent:
                time = 7000;
                break;
            case R.drawable.squid:
                time = 7000;
                break;
        }

        Intent alarmIntent = new Intent(UI_UPDATE_TAG);
        alarmIntent.putExtra("Image", image);
        alarmIntent.putExtra("Message", message);

        JOB_ID++;

        //(4) The next is a PendingIntent which is an intent but used for pending tasks. Note that
        //    for every new PendingIntent to be sent, one must update the request code.
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                1000000+JOB_ID, alarmIntent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + time, pendingIntent);

    }

    private String getImageName(int id) {
        return getResources().getResourceEntryName(id);
    }

    private String getResourceFromString(String name) {
        int resId = getResources().getIdentifier(name, "string", getPackageName());
        return getResources().getString(resId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    public void sendMessage(View view) {
        EditText msg = findViewById(R.id.commentMessage);
        adapter.addItem(R.drawable.id_card, msg.getText().toString());
        msg.setText("");
    }

    class ChatMockupReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int image = intent.getIntExtra("Image", 0);
            String message = intent.getStringExtra("Message");

            adapter.addItem(image, message);
            performChat();
        }
    }

}

