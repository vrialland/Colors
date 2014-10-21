package biz.rialland.shapes;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridLayout grid = (GridLayout) findViewById(R.id.grid);
        int id = 0;
        for (byte i=0; i < grid.getColumnCount(); i++)
            for (byte j = 0; j < grid.getRowCount(); j++) {
                Button bt = new Button(this);
                int curId = ++id;
                bt.setId(curId);
                bt.setText(Integer.toString(curId));
                bt.setBackgroundColor(ColorChoice.getRandomColor());
                grid.addView(bt);
            }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
