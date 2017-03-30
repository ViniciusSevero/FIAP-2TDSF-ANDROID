package com.example.vinicius.aula03.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by logonrm on 30/03/2017.
 */

public class ArquivoDB {

    private SharedPreferences pref;

    public void gravarCaves(Context context, String prefName, HashMap<String,String> map){
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

    public boolean verificarCHave(Context context, String prefName,String key){
        pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
        return pref.contains(key);
    }

    //#### Tratamento de aquivos ####
    public void gravarArquivo(Context context, String name,String value) throws FileNotFoundException {
        FileOutputStream fos = context.openFileOutput(name, Context.MODE_PRIVATE);
        try{
            fos.write(value.getBytes());
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String lerArquivo(Context context, String name) throws FileNotFoundException {
        FileInputStream fis = context.openFileInput(name);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String txt = null;
        try{
            txt = br.readLine();
        }catch (Exception e){
            e.printStackTrace();;
        }
        return txt;
    }

    public boolean excluirArquivo(Context context, String name) throws FileNotFoundException {
        try{
            return context.deleteFile(name);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
