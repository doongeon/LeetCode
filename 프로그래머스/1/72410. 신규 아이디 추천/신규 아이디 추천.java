class Solution {
    public String solution(String new_id) {
        String answer = "";
        String myId = new_id.toLowerCase();
        String temp = "";
        for(int i = 0; i < myId.length(); i++) {
            char cur = myId.charAt(i);
            if(cur >= 'a' && cur <= 'z') {
                temp += myId.charAt(i);
            }
            
            if(cur >= '0' && cur <= '9') {
                temp += myId.charAt(i);
            }
            
            if(cur == '-' || cur == '_' || cur == '.') {
                temp += myId.charAt(i);
            }
        }
        myId = temp;
        
        
        while(myId.indexOf("..") > -1) {
            myId = myId.replace("..", ".");
        }
        
        if(myId.length() > 0 && myId.charAt(0) == '.') {
            if(myId.length() > 1) myId = myId.substring(1);
            else myId = "";
        } 
        
        if(myId.length() > 0 && myId.charAt(myId.length() - 1) == '.') {
            if(myId.length() - 1 > 0) myId = myId.substring(0, myId.length() - 1);
            else myId = "";
        }
        
        if(myId.length() == 0) myId = "a";
        
        if(myId.length() > 15) {
            myId = myId.substring(0, 15);
            if(myId.charAt(14) == '.') myId = myId.substring(0, 14);
        }
        
        while(myId.length() <= 2) {
            myId += myId.charAt(myId.length() - 1);
        }
        
        return myId;
    }
}