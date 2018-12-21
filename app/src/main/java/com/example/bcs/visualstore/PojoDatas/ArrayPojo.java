package com.example.bcs.visualstore.PojoDatas;

public class ArrayPojo {


        private String status;
        private ProfileData data;
        private String message;


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ProfileData getData() {
            return data;
        }

        public void setData(ProfileData data) {
            this.data = data;
        }


        public class ProfileData {

            String lens_code;
            String name;
            String tint;

            public String getLens_code() {
                return lens_code;
            }

            public String getName() {
                return name;
            }

            public String getTint() {
                return tint;
            }

            public void setLens_code(String lens_code) {
                this.lens_code = lens_code;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setTint(String tint) {
                this.tint = tint;
            }





        }

    }

