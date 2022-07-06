package com.personal.utility.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Helper {
    
    public String hex2Bin(String bitMessage) {
        String out = "";
    
        for(int i=0; i<bitMessage.length(); i++){
            switch (bitMessage.charAt(i)) {
            case '0':
            out += "0000";
            break;
          case '1':
            out += "0001";
            break;
          case '2':
            out += "0010";
            break;
          case '3':
            out += "0011";
            break;
          case '4':
            out += "0100";
            break;
          case '5':
            out += "0101";
            break;
          case '6':
            out += "0110";
            break;
          case '7':
            out += "0111";
            break;
          case '8':
            out += "1000";
            break;
          case '9':
            out += "1001";
            break;
          case 'A':
            out += "1010";
            break;
          case 'B':
            out += "1011";
            break;
          case 'C':
            out += "1100";
            break;
          case 'D':
            out += "1101";
            break;
          case 'E':
            out += "1110";
            break;
          case 'F':
            out += "1111";
            break;
          default:
            return "";
            }
        }
        return out;
        }
}
