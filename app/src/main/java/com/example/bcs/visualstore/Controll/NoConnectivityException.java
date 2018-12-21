package com.example.bcs.visualstore.Controll;

import java.io.IOException;

public class NoConnectivityException extends IOException{

    public String getMessage() {
        return "Network Connection exception";
    }
}
