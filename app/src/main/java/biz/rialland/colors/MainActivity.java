package biz.rialland.colors;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Locale;

public class MainActivity extends Activity {
    private final static int COLS = 2;

    private ShapeColor currentColor = null;
    private TextToSpeech tts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout grid = (TableLayout) findViewById(R.id.grid);
        TableRow tr = null;
        TableLayout.LayoutParams rowParams = new TableLayout.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1f);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f);

        for (int id=0; id < ColorChoice.nbItems(); id++) {
            if (id % COLS == 0) {
                tr = new TableRow(this);
                tr.setLayoutParams(rowParams);
                grid.addView(tr);
            }
            Button bt = new Button(this);
            bt.setLayoutParams(params);
            bt.setId(id);
            bt.setText(Integer.toString(id + 1));
            tr.addView(bt);

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ColorDrawable bg = (ColorDrawable) view.getBackground();
                    if (bg.getColor() == currentColor.getCode()) {
                        tts.speak(getString(R.string.msg_good), TextToSpeech.QUEUE_FLUSH, null);
                        initGame();
                    } else {
                        tts.speak(getString(R.string.msg_bad), TextToSpeech.QUEUE_FLUSH, null);
                        view.setActivated(false);
                    }
                }
            });
        }
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            tts.setLanguage(Locale.getDefault());
            initGame();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                initGame();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initGame() {
        TableLayout grid = (TableLayout) findViewById(R.id.grid);
        int id = 0;
        for (ShapeColor sh: ColorChoice.getColors()) {
            Button bt = (Button) grid.findViewById(id++);
            bt.setBackgroundColor(sh.getCode());
        }
        currentColor = ColorChoice.getRandomColor();
        String msg = getString(R.string.click_action, getTranslation(currentColor.getName()));
        tts.speak(msg, TextToSpeech.QUEUE_ADD, null);
    }

    private String getTranslation(String name) {
        return getString(getResources().getIdentifier(name, "string", getPackageName()));
    }
}
