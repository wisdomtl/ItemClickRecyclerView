package taylor.com.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String[] DATA = {"item1", "item2", "item3", "item4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         MyAdapter myAdapter = new MyAdapter(Arrays.asList(DATA));
        BaseRecyclerView rv = (BaseRecyclerView) findViewById(R.id.rv);
        rv.setAdapter(myAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setOnItemClickListener(new BaseRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(View item, int adapterPosition, RecyclerView.Adapter adapter) {
                Toast.makeText(MainActivity.this, ((MyAdapter) adapter).getData().get(adapterPosition), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
