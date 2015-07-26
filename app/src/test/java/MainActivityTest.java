import android.widget.TextView;

import com.self.smone.tipcalc.BuildConfig;
import com.self.smone.tipcalc.MainActivity;
import com.self.smone.tipcalc.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;



@Config(constants = BuildConfig.class, sdk = 21)
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull("", activity);
    }

    @Test
    public void validateTextViewContent() throws Exception {

        TextView tvNumberOfPeople = (TextView) activity.findViewById(R.id.text_people_view);
        assertNotNull("TextView could not be found", tvNumberOfPeople);
        assertTrue("TextView contains incorrect text",
                "Number of People".equals(tvNumberOfPeople.getText().toString()));
    }
}