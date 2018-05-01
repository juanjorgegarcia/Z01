/**
 * Curso: Elementos de Sistemas
 * Arquivo: Code.java
 */

package assembler;

import java.math.BigInteger;

/**
 * Traduz mnemônicos da linguagem assembly para códigos binários da arquitetura Z0.
 */


public class Code {
	
	public String instruction = new String();
	
	
    /**
     * Retorna o código binário do(s) registrador(es) que vão receber o valor da instrução.
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 4 bits) com código em linguagem de máquina para a instrução.
     */
    public static String dest(String[] mnemnonic) {
    	
    	int length = mnemnonic.length - 1;
    	StringBuilder myName = new StringBuilder("0000");
    	
    	boolean bool = false; // Para os casos de incw, negw, etc. q tem apenas 2 de .length
    	if (length == 1){
    		bool = true;
    	}
    	while (length >= 2 | bool == true) {
    	if (mnemnonic[length] == "%A"){ 
    		myName.setCharAt(0, '1');
    	} else if (mnemnonic[length] == "%S"){
    		myName.setCharAt(1, '1');
    	} else if (mnemnonic[length] == "%D"){
    		myName.setCharAt(2, '1');
    	} else if (mnemnonic[length] == "(%A)"){
    		myName.setCharAt(3, '1');
    	}
    	bool = false;
    	if (mnemnonic[0] != "movw"){
    		length = 1;
    	}
    	length -= 1;
    	}
    	return myName.toString(); 
    }
    
    /**
     * Retorna o código binário do mnemônico para realizar uma operação de cálculo.
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 7 bits) com código em linguagem de máquina para a instrução.
     */
    public static String comp(String[] mnemnonic) {
    	StringBuilder binCalc = new StringBuilder("10000000");
 
    	
    	int length = mnemnonic.length - 1;
    	
    	// A e B
    	if (length == 1) {
    		if (mnemnonic[1] == "%A" | mnemnonic[1] == "%D"){
        		binCalc.setCharAt(0, '0');
        	} else if(mnemnonic[1] == "%S"){
        		binCalc.setCharAt(0, '0');
        		binCalc.setCharAt(1, '1');
        
        	}
    	} else if(length >= 2) {
    		if ((mnemnonic[1] == "%A" & mnemnonic[2] == "%D")|(mnemnonic[2] == "%A" & mnemnonic[1] == "%D")){
    			binCalc.setCharAt(0, '0');
    		} else if ((mnemnonic[1] == "%A" & mnemnonic[2] == "%S")|(mnemnonic[2] == "%A" & mnemnonic[1] == "%S")){
    			binCalc.setCharAt(0, '0');
    			binCalc.setCharAt(1, '1');
    		} else if ((mnemnonic[1] == "(%A)" & mnemnonic[2] == "%S")|(mnemnonic[2] == "(%A)" & mnemnonic[1] == "%S")){
    			binCalc.setCharAt(1, '1');
    		}
    	}
    	
    	
    	
    	
    	if ((mnemnonic[0] == "incw")){
    		binCalc.setCharAt(3, '1');
    		if (mnemnonic[1] == "%A" | mnemnonic[1] == "(%A)"){
    			binCalc.setCharAt(2, '1');
    			binCalc.setCharAt(4, '0');
    		} else{
    			binCalc.setCharAt(2, '0');
    			binCalc.setCharAt(4, '1');
    		}
    		binCalc.setCharAt(5, '1');
    		binCalc.setCharAt(6, '1');
    		binCalc.setCharAt(7, '1');
    	}
    	
    	if ((mnemnonic[0] == "decw")){
    		binCalc.setCharAt(6, '1');
    		if (mnemnonic[1] == "%A" | mnemnonic[1] == "(%A)"){
    			binCalc.setCharAt(2, '1');
    			binCalc.setCharAt(3, '1');
    		} else{
    			binCalc.setCharAt(4, '1');
    			binCalc.setCharAt(5, '1');
    		}
    	}
    	
    	if ((mnemnonic[0] == "notw")){
    		binCalc.setCharAt(7, '1');
    		if (mnemnonic[1] == "%A" | mnemnonic[1] == "(%A)"){
    			binCalc.setCharAt(2, '1');
    			binCalc.setCharAt(3, '1');
    		} else{
    			binCalc.setCharAt(4, '1');
    			binCalc.setCharAt(5, '1');
    		}
    	}
    	
    	if ((mnemnonic[0] == "negw")){
    		binCalc.setCharAt(7, '1');
    		binCalc.setCharAt(6, '1');
    		if (mnemnonic[1] == "%A" | mnemnonic[1] == "(%A)"){
    			binCalc.setCharAt(2, '1');
    			binCalc.setCharAt(3, '1');
    		} else{
    			binCalc.setCharAt(4, '1');
    			binCalc.setCharAt(5, '1');
    		}
    	}
    	
    	if ((mnemnonic[0] == "orw")){
    		binCalc.setCharAt(3, '1');
    		binCalc.setCharAt(5, '1');
    		binCalc.setCharAt(7, '1');
    	}
    	
    	
    	
    	
    	if ((mnemnonic[0] == "movw")){
    		binCalc.setCharAt(5, '0');
    		binCalc.setCharAt(6, '0');
    		if (mnemnonic[1] == "%A" | mnemnonic[1] == "(%A)"){
    			binCalc.setCharAt(2, '1');
    			binCalc.setCharAt(3, '1');
    		} else {
    			binCalc.setCharAt(4, '1');
    			binCalc.setCharAt(5, '1');
    		}
    	}
    	
    	if ((mnemnonic[0] == "subw")){
    		binCalc.setCharAt(6, '1');
			binCalc.setCharAt(7, '1');
			if (mnemnonic[1] == "%A"| mnemnonic[1] == "(%A)"){
				binCalc.setCharAt(5, '1');
			} else{
				binCalc.setCharAt(3, '1');
			}
    	}
    	
    	if ((mnemnonic[0] == "rsubw")){
    		binCalc.setCharAt(6, '1');
			binCalc.setCharAt(7, '1');
			if (mnemnonic[1] == "%S"| mnemnonic[1] == "%D"){
				binCalc.setCharAt(5, '1');
			} else{
				binCalc.setCharAt(3, '1');
			}
    	}
    	
    	if (mnemnonic[0] == "addw"){
    		binCalc.setCharAt(6, '1');
    	}
    	
    	//System.out.println(mnemnonic[0]);
    	//System.out.println(length);
    	//System.out.println(binCalc);
    	
    	return binCalc.toString();
    }

    /**
     * Retorna o código binário do mnemônico para realizar uma operação de jump (salto).
     * @param  mnemnonic vetor de mnemônicos "instrução" a ser analisada.
     * @return Opcode (String de 3 bits) com código em linguagem de máquina para a instrução.
     */
    public static String jump(String[] mnemnonic) {
    	int length = mnemnonic.length;
    	for (int i = 0; i < length ; i++){
    		   if (mnemnonic[i] == "jmp"){
    			   return "111";
    		   } else if(mnemnonic[i] == "je") {
    			   return "010";
    		   } else if(mnemnonic[i] == "jne") {
    			   return "101";
    		   } else if(mnemnonic[i] == "jg") {
    			   return "001";
    		   } else if(mnemnonic[i] == "jge") {
    			   return "011";
    		   } else if(mnemnonic[i] == "jl") {
    			   return "100";
    		   } else if(mnemnonic[i] == "jle") {
    			   return "110";
    		   }
    	
    		}
    	return "000";
    }

    /**
     * Retorna o código binário de um valor decimal armazenado numa String.
     * @param  symbol valor numérico decimal armazenado em uma String.
     * @return Valor em binário (String de 15 bits) representado com 0s e 1s.
     */
    public static String toBinary(String symbol) {
    	return String.format("%15s", Integer.toBinaryString(Integer.parseInt(symbol))).replace(' ', '0');
    }
    

    
    
    public static String encoder(String string){
    	//String 
    	
    	return null;
    }
}
