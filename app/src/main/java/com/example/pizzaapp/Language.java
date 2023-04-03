package com.example.pizzaapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Language {
    HashMap<String,String> languageList = new HashMap<>();

    public Language(){
        setDictionary();
    }


    private void setDictionary(){
        languageList.put("Start Order","Lancer la commande");
        languageList.put("Order History","Historique des commandes");
        languageList.put("Size","Talle");
        languageList.put("Small","Petite");
        languageList.put("Medium","Moyen");
        languageList.put("Large","Grande");
        languageList.put("Toppings","Garnitures");
        languageList.put("Pepperoni","Pepperoni");
        languageList.put("Sausage","Saucisse");
        languageList.put("Mushroom","Champignon");
        languageList.put("Peppers","Poivrons");
        languageList.put("Complete Order","Complétez la commande");
    }

    public HashMap <String,String> getDictionary(){
        return languageList;
    }


}
