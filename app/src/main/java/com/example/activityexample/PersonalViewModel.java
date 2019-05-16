package com.example.activityexample;

public class PersonalViewModel {

     private String Name;

     private  String Family;

     private int Telephone;

     private String Address;

     private  String ImageAddress;


   PersonalViewModel(String Name,String Family,int telephone,String Address,String ImageAddress)
     {
         this.Name=Name;
        this.Family=Family;
        this.Telephone=telephone;
        this.Address=Address;
        this.ImageAddress=ImageAddress;

     }
     public String GetName()
     {
         return this.Name;

     }


    public String GetFamily() {
       return this.Family;
    }

    public int GetTell() {
       return this.Telephone;
    }

    public String GetAddress() {
       return this.Address;
    }
}
