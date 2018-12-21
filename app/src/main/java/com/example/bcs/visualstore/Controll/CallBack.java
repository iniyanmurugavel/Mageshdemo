package com.example.bcs.visualstore.Controll;

import com.example.bcs.visualstore.PojoDatas.CallBackData;
import com.example.bcs.visualstore.PojoDatas.MyProfileData;

import java.util.List;

public interface CallBack<T>  {

    void next(List<CallBackData.Data> result);

    //void nextProfile(List<CallBackData.Data> profileData);
}
