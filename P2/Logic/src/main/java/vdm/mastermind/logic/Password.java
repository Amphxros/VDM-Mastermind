
package vdm.mastermind.logic;

import java.util.Random;

/**
 * Class that represents the solution
 * in an array of ints where each index
 * represents a color
 */
public class Password {
    private int [] password;

    //range of the solution
    private final int min, max;
    boolean isRepeating;
    public Password(int size,int min,int max, boolean isRepeating){
        password= new int[size];
        this.min=min;
        this.max=max;
        this.isRepeating=isRepeating;
    }

    public void generateRandom(){
        Random random= new Random();
        if(isRepeating){
            for(int i = 0; i < password.length; ++i){
                password[i]=random.nextInt(min,max+1);
            }
        }
        else{
            boolean[] isThere= new boolean[password.length];
            for(int i = 0; i < password.length; ++i){
               isThere[i]=false;
            }

            for(int i=0;i<password.length;i++){
                int r;
                do {
                    r=random.nextInt(min,max+1);

                }while(isThere[r]);
                isThere[r]=true;
            }

        }
    }

    /**
     * @return The content of the password
     */
    public int[] getPassword(){
        return this.password;
    }

    /**
     * @param obj Other password to compare to
     * @return Whether is equal of not
     */
    @Override
    public boolean equals(Object obj) {
        Password p = (Password) obj;
        if(p==null) return false;
        int i=0;
        while(i<this.password.length &&p.password[i]==this.password[i]){
            i++;
        }

        return i==this.password.length - 1;
    }

    public int getIntPassword( int index ) {
        return password[index];
    }
}
