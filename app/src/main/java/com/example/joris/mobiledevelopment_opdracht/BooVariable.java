package com.example.joris.mobiledevelopment_opdracht;

/**
 * Created by joris on 1/9/2018.
 */
public class BooVariable {
        private boolean boo = false;
        private ChangeListener listener;

        public boolean isBoo() {
            return boo;
        }

        public void setBoo(boolean boo) {
            this.boo = boo;
            if (listener != null) listener.onChange();
        }

        public ChangeListener getListener() {
            return listener;
        }

        public void setListener(ChangeListener listener) {
            this.listener = listener;
        }

        public interface ChangeListener {
            void onChange();
        }
}
//credits: stackoverflow User Tatarize
//url : https://stackoverflow.com/questions/14457711/android-listening-for-variable-changes

