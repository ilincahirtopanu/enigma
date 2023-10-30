import javax.swing.plaf.InputMapUIResource;

public class Rotor {

    
    private String rotorValues;
    private char startChar;
        
    public Rotor(String v, char c){
        this.rotorValues = new String(v);
        this.startChar = c;
        
        while(!this.rotate());
            
    }
    
    //returns true if the rotor has rotated fully around
    public boolean rotate(){

        //brings the last letter to the front of the list
        rotorValues = rotorValues.substring(26) + rotorValues.substring(0, 26);
        
        //if the value that was just moved to the front was the start value
        if (startChar == rotorValues.charAt(0)) {
            return true;
        }
        //hasn't completed a circle yet
        return false;
    }
    
    //searches for a letter and returns the index of a letter
    public int indexOf(char c){
        return rotorValues.indexOf(c);
        // for (int i = 0; i < rotorValues.length(); i++) {
        //     //if the character at the index matches the letter being searched for 
        //     if (c == charAt(i)) {
        //         return c;
        //     }
        // }
        // return -1;
    }

    //returns the index of the character the object is on
    public char charAt(int idx){
        return rotorValues.charAt(idx);
    }
}
    
