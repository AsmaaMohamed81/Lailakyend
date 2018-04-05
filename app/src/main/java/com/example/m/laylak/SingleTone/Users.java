package com.example.m.laylak.SingleTone;

import com.example.m.laylak.Models.UserModel;

/**
 * Created by Emad on 2018-04-04.
 */

public class Users {
    private static Users instance;
    private UserModel userModel;
    private Users.onCompleteListener completeListener;
    private Users(){}

    public static synchronized Users getInstance()
    {
        if (instance==null)
        {
            instance = new Users();
        }
        return instance;
    }

    public interface onCompleteListener
    {
        void OnDataSuccess(UserModel userModel);
    }

    public void setUserData(UserModel userModel)
    {
        this.userModel = userModel;
        //completeListener.OnDataSuccess(userModel);


    }

    public void getData(Users.onCompleteListener listener)
    {
        completeListener = listener;
        completeListener.OnDataSuccess(userModel);
    }



}
