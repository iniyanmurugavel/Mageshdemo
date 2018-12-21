package com.example.bcs.visualstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String>home=new ArrayList<String>();

        List<String>lensType=new ArrayList<String>();

        List<String> myOrder = new ArrayList<String>();
        myOrder.add("CARD");
        myOrder.add("SENT ORDER");

        List<String>setting=new ArrayList<String>();
        setting.add("EMPLOYEE MANAGEMENT");
        setting.add("CONTACT INFORMATION");

        List<String>myAccount=new ArrayList<String>();


        expandableListDetail.put("HOME", home);
        expandableListDetail.put("LENS ORDERING", lensType);
        expandableListDetail.put("MY ORDER", myOrder);
        expandableListDetail.put("SETTING", setting);
        expandableListDetail.put("MY ACCOUNT", myAccount);

        return expandableListDetail;
    }
}
