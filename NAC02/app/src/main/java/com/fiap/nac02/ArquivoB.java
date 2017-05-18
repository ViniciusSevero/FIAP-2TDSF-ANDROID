package com.fiap.nac02;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by logonrm on 18/05/2017.
 */

public class ArquivoB {
    private SharedPreferences pref;

    public void gravarChaves(Context context, String prefName, HashMap<String,String> map){
        pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        //pegar cada registro (entrada) do HashMap
        for(Map.Entry<String,String> item : map.entrySet()){
            e.putString(item.getKey(),item.getValue());
        }
        e.commit();
    }
    public String retornaValor(Context context, String prefName,String key){
        pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
        return pref.getString(key,null); // se não achar retona null
    }

    public boolean verificarChave(Context context, String prefName,String key){
        pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
        return pref.contains(key);
    }
}
