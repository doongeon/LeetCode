class Solution {
    public String solution(String new_id) {
        String str = new_id;
        
        str = str.toLowerCase();
        String tempStr = "";
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
                tempStr += str.charAt(i);
            }
            
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                tempStr += str.charAt(i);
            }
            
            if(str.charAt(i) == '-' ||
               str.charAt(i) == '_' ||
               str.charAt(i) == '.') {
                tempStr += str.charAt(i);
            }
        }
        
        str = tempStr;
        
        while(str.indexOf("..") > -1) {
            str = str.replace("..", ".");
        }
        
        if(str.charAt(0) == '.') {
            str = str.substring(1);
        }
        if(str.length() >= 1 && str.charAt(str.length() - 1) == '.') {
            str = str.substring(0, str.length() - 1);
        }
        
        if(str.equals("")) {
            str += "a";
        }
        
        if(str.length() >= 16) {
            str = str.substring(0, 15);
            if(str.charAt(str.length() - 1) == '.') str = str.substring(0, 14);
        }
        
        while(str.length() < 3) {
            str = str + str.substring(str.length() - 1);
        }
        
        return str;
    }
}