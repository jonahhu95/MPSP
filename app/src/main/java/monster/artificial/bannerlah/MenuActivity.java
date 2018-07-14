package monster.artificial.bannerlah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zql.android.cardmenulib.CardMenu;
import com.zql.android.cardmenulib.CardMenuItem;

public class MenuActivity extends AppCompatActivity implements CardMenu.OnCardMenuSelecetedListener {

    private FrameLayout mMainContent;
    int[] names = new int[]{R.string.validate, R.string.location, R.string.report};
    int[] icons = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mMainContent = (FrameLayout) findViewById(R.id.main_content);

        CardMenu menu = (CardMenu) findViewById(R.id.cardMenu);

        menu.initMenus(names, icons, null);
        menu.setOnCardMenuSelectedListener(this);
    }

    @Override
    public void cardMenuSelected(CardMenuItem item) {
        switch (item.getIndex()) {
            case 0:
            case 1:
            case 2:
        }
    }
}
