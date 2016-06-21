package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.phamduyen.bongda.AddPitch;
import com.example.phamduyen.bongda.ManageMatch;
import com.example.phamduyen.bongda.Profile;

/**
 * Created by Pham Duyen on 06/05/2016.
 */

    public class PageAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public PageAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Profile tab1 = new Profile();
                    return tab1;
                case 1:
                    ManageMatch tab2 = new ManageMatch();
                    return tab2;
                case 2:
                    AddPitch tab3 = new AddPitch();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }

