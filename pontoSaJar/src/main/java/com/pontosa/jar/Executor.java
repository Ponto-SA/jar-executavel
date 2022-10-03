package com.pontosa.jar;

import com.github.britooo.looca.api.core.Looca;
import com.pontosa.jar.database.Conexao;


public class Executor {
    public static void main(String[] args) throws InterruptedException {
        int loop = 0;
        
        Looca looca = new Looca();
        Conexao conexao = new Conexao();
        conexao.getConnection();
        
        System.out.println(looca.getProcessador().getUso());

        Double memoriaUso = Double.longBitsToDouble(looca.getMemoria().getEmUso());
        Double memoriaTotal = Double.longBitsToDouble(looca.getMemoria().getTotal());
       Double memoriaUsoPorc = (memoriaUso / memoriaTotal) * 100;
       System.out.println(String.format("%.0f", memoriaUsoPorc));

        while (loop != 0){
            conexao.setRegistro(loop, loop, memoriaUsoPorc);
            conexao.setRegistro(loop, loop, looca.getProcessador().getUso());
            
            Thread.sleep(5000);
            
        }
        
    }
}
