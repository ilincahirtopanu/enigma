public class Enigma{

    private String rotorInit[] = {"#GNUAHOVBIPWCJQXDKRYELSZFMT",
        "#EJOTYCHMRWAFKPUZDINSXBGLQV",
        "#BDFHJLNPRTVXZACEGIKMOQSUWY",
        "#NWDKHGXZVRIFJBLMAOPSCYUTQE",
        "#TGOWHLIFMCSZYRVXQABUPEJKND"};


    private Rotor rotors[];
        
    public Enigma(int id1, int id2, int id3, String start){

        rotors = new Rotor[3];
        rotors[0] = new Rotor(rotorInit[id1-1], start.charAt(0));
        rotors[1] = new Rotor(rotorInit[id2-1], start.charAt(1));
        rotors[2] = new Rotor(rotorInit[id3-1], start.charAt(2));
        
    }

    /* to decrypt a letter:
     *  find the index of the letter on the outside ring,
     *  find the letter on that index on the middle ring 
     *  search for the letter in the the outside ring;
     *  find the letter at that index on the inner ring
     *  return that letter, rotates
     * */
    public String decrypt(String message){      
        String newMessage = "";
        char current = 0;
        //goes through each letter in the string  
        for (int i = 0; i < message.length(); i++) {
            current = message.charAt(i);
            
            //finds the index of the current character of message in outer loop
            int r3Index = rotors[2].indexOf(current);//
            //finds the letter of that index in middle ring
            char r2Letter = rotors[1].charAt(r3Index);//
            //finds the index of that letter in the outside ring
            r3Index = rotors[2].indexOf(r2Letter);//
            //finds the letter at that index in the inner ring
            char r1Letter = rotors[0].charAt(r3Index);//

            rotate();
            //adds to the new message
            newMessage += r1Letter;
        }
        return newMessage;
    }


    /*to encrypt a letter:
      finds the letter on the INNER ring, 
      finds the letter at the same index but on the OUTER ring
      searches for that letter in the MIDDLE ring
      goes to that same index on the OUTSIDE ring
      returns that letter, rotates    */
    public String encrypt(String message){
        String newMessage = "";
        char current = 0;

        //goes through each letter in the string
        for (int i = 0; i < message.length(); i++) {
            current = message.charAt(i);
            //find the letter on the inner ring
            int r0Index = rotors[0].indexOf(current);
            //finds the letter at the same index but on the OUTER ring
            char r2Letter = rotors[2].charAt(r0Index);
            //searches for that letter in the MIDDLE ring
            int r1Index = rotors[1].indexOf(r2Letter);
            //goes to that same index on the OUTSIDE ring
            char r3Letter = rotors[2].charAt(r1Index);

            //adds found letter to new message
            newMessage += r3Letter;

            rotate();
        }
        return newMessage;

    }
    
    //
    private void rotate(){
        if(rotors[0].rotate()){
            if(rotors[1].rotate()){
                rotors[2].rotate();
            }
        }
    }
    
}
