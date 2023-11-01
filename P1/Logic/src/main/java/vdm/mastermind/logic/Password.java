
package vdm.mastermind.logic;

import java.util.Random;

public class Password {
    private int [] password;
    private final int min;
    private final int max;
    public Password(int size,int min,int max){
        password= new int[size];
        this.min=min;
        this.max=max;
    }

    public void generateRandom(){
        Random rnd= new Random();
        for(int i=0; i<password.length;i++){
            password[i]=rnd.nextInt(min,max+1);
            System.out.println(password[i]);
        }
    }

    public void generateCopy(int[] password){
        for(int i=0;i< password.length;i++){
            this.password[i]=password[i];
        }
    }

    public int[] getPassword(){
        return this.password;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
