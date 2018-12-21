package com.example.bcs.visualstore.PojoDatas;

public class DataModel {

    private String name;
    private String id;
    private String code;
    private String type;

    public DataModel(String name){
        this.name=name;
    }

    public DataModel(String name,String id) {
        this.name = name;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public DataModel() {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataModel(String name,String code,String type){
        this.name=name;
        this.code=code;
        this.type=type;
    }
}





//    LensOrderingFragment fragment= new LensOrderingFragment();
//    android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                                                transaction.replace(R.id.fragmentContainer, fragment); // fragment container id in first parameter is the container(Main layout id) of Activity
//                                                        transaction.addToBackStack(null); // this will manage backstack
//                                                        transaction.commit();
//                                                        UrlConnection1 urlConnection1=new UrlConnection1();
////                                urlConnection1.execute();
//
//                                                        SharedPreferences pref = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
//                                                        SharedPreferences.Editor editor = pref.edit();
//                                                        editor.clear();
//                                                        editor.apply();