package com.example.bcs.visualstore.PojoDatas;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.io.StringReader;

public class MyProfileData implements Serializable {


        @SerializedName("id")
        String id;
        @SerializedName("name")
        String name;
        @SerializedName("email")
        String email;
        @SerializedName("phone")
        String phone;
         @SerializedName("login_code")
        String login_code;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLogin_code() {
            return login_code;
        }

        public void setLogin_code(String login_code) {
            this.login_code = login_code;
        }

}