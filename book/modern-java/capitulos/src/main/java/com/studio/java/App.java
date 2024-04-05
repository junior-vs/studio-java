package com.studio.java;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        App a  = new App();
        System.out.println(a.teste(1));
    }

    public boolean teste(int a){
        return a==1 || a==2 || a==3;
    }
}
