/**
 * Curso: Elementos de Sistemas
 * Arquivo: Parser.java
 */

package assembler;


import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
/**
 * Encapsula o código de leitura. Carrega as instruções na linguagem assembly,
 * analisa, e oferece acesso as partes da instrução  (campos e símbolos).
 * Além disso, remove todos os espaços em branco e comentários.
 */
public class Parser {
    Scanner stdin;
    String currentCommand;

    /** Enumerator para os tipos de comandos do Assembler. */
    public enum CommandType {
        A_COMMAND,      // comandos LEA, que armazenam no registrador A
        C_COMMAND,      // comandos de calculos
        L_COMMAND       // comandos de Label (símbolos)
    }

    /**
     * Abre o arquivo de entrada NASM e se prepara para analisá-lo.
     * @param file arquivo NASM que será feito o parser.
     */
    public Parser(String file) {


        try{
            File data = new File(file);
            this.stdin = new Scanner(data);
        } catch (FileNotFoundException e){
            System.out.println("File not found - Error");
            e.printStackTrace();
        }

    }

    /**
     * Carrega uma instrução e avança seu apontador interno para o próxima
     * linha do arquivo de entrada. Caso não haja mais linhas no arquivo de
     * entrada o método retorna "Falso", senão retorna "Verdadeiro".
     * @return Verdadeiro se ainda há instruções, Falso se as instruções terminaram.
     */
    public Boolean advance() {
        while(stdin.hasNextLine()){
            String tmp = stdin.nextLine();

            tmp = tmp.trim();

            if (!tmp.isEmpty() && tmp.charAt(0) != ';'  ) {
                tmp.replace("\t", "");
                tmp = tmp.replaceAll(" +", " ");
                int ind = tmp.length();
                for (int i = 0; i <tmp.length() ; i++) {
                    if (tmp.charAt(i) == ';'){
                        ind = i-1;

                    }

                }
                this.currentCommand = tmp.substring(0,(ind));
                return true;
            }

        }
        return false;
    }


    /**
     * Retorna o comando "intrução" atual (sem o avanço)
     * @return a instrução atual para ser analilisada
     */
    public String command() {
    	return this.currentCommand;

    }

    /**
     * Retorna o tipo da instrução passada no argumento:
     *  A_COMMAND para leaw, por exemplo leaw $1,%A
     *  L_COMMAND para labels, por exemplo Xyz: , onde Xyz é um símbolo.
     *  C_COMMAND para todos os outros comandos
     * @param  command instrução a ser analisada.
     * @return o tipo da instrução.
     */
    public CommandType commandType(String command) {
        if(command.endsWith(":")){
            return CommandType.L_COMMAND;
        }
        else if (command.startsWith("leaw")){
            return CommandType.A_COMMAND;

        }
        else {
            return CommandType.C_COMMAND;
        }

    }

    /**
     * Retorna o símbolo ou valor numérico da instrução passada no argumento.
     * Deve ser chamado somente quando commandType() é A_COMMAND.
     * @param  command instrução a ser analisada.
     * @return somente o símbolo ou o valor número da instrução.
     */
    public String symbol(String command) {
        String simb = "";
        Integer inde = 0;
        if(commandType(command) == CommandType.A_COMMAND){

            for (int i = 0; i <command.length() ; i++) {
                if(command.charAt(i)=='$'){
                    inde = i+1;
                }
            }
            String newcom= command.substring(inde,command.length());
            for (int i = 0; i <newcom.length() ; i++) {

                if(newcom.charAt(i)==' ' || newcom.charAt(i) == ','){
                    break;
                }
                simb+=newcom.charAt(i);

            }
        }
        return simb;
    }

    /**
     * Retorna o símbolo da instrução passada no argumento.
     * Deve ser chamado somente quando commandType() é L_COMMAND.
     * @param  command instrução a ser analisada.
     * @return o símbolo da instrução (sem os dois pontos).
     */
    public String label(String command) {
        String simb = "";
        if (commandType(command) == CommandType.L_COMMAND) {
            simb = command.substring(0,command.length()-1);
        }
        return simb;
    }

    /**
     * Separa os mnemônicos da instrução fornecida em tokens em um vetor de Strings.
     * Deve ser chamado somente quando CommandType () é C_COMMAND.
     * @param  command instrução a ser analisada.
     * @return um vetor de string contento os tokens da instrução (as partes do comando).
     */
    public String[] instruction(String command) {
        String[] instructions = new String[3];
        command = command.replace(',',' ');
        instructions=command.split(" ");
        return instructions;
    }

}

