package com.example.android.cms;

public class eventdata {

        public int  eId;
        public String eEventName;
        public String eDescription;
        public int geteId() {
            return eId;
        }

        public void seteId(int eId) {
            this.eId = eId;
        }

        public String geteEventName() {
            return eEventName;
        }

        public void seteEventName(String eEventName) {
            this.eEventName = eEventName;
        }

        public String geteDescription() {
            return eDescription;
        }

        public void seteDescription(String eDescription) {
            this.eDescription = eDescription;
        }



}

