package VM;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class machineCodeGenerator {
private String id;
//public int initValueForInt;
private String initValueForString;
private String relativeAddr;
private String type;
private boolean isTacFileEnd;
public ArrayList<String> obj;
public ArrayList<ArrayList> arrayOfObj;

public int relativeAddressForSymbolTable;

public machineCodeGenerator(){
    id=  null;
    //initValueForInt = -1;
    initValueForString = null;
    relativeAddr = null;
    type = null;
    relativeAddressForSymbolTable =0;
    isTacFileEnd = false;
    obj = new ArrayList<>();
    arrayOfObj = new ArrayList<>();
}

    public String getId() {
        return id;
    }

    public String getInitValueForString(String variable) throws FileNotFoundException{
        File file = new File("symbolTableForTranslator.txt");
        Scanner reader = new Scanner(file);

        while(reader.hasNext())
        {
            String temp = reader.nextLine();
            String[] str = temp.split("\t");
            if(str[0].contentEquals(variable))
            {
                initValueForString = str[1];  // 4th index will have relativeAddr
                break;
            }
        }

        reader.close();
         return initValueForString;
    }

    public String getType() {
        return type;
    }

    public String getRelativeAddr(String variable) throws FileNotFoundException{
    File file = new File("symbolTableForTranslator.txt");
    Scanner reader = new Scanner(file);

    while(reader.hasNext())
    {
        String temp = reader.nextLine();
        String[] str = temp.split("\t");
        if(str[0].contentEquals(variable))
        {
            relativeAddr = str[3];  // 4th index will have relativeAddr
            break;
        }
    }

    reader.close();
    return relativeAddr;
}

    public void setId(String id) {
        this.id = id;
    }

    public void setInitValueForString(String initValueForString) {
        this.initValueForString = initValueForString;
    }

    public void setRelativeAddr(String relativeAddr) {
        this.relativeAddr = relativeAddr;
    }

    public void setType(String type) {
        this.type = type;
    }

    //public void writeToTranslatorSymbolTable(String s, String type, String initVal) throws IOException {
    public void writeToTranslatorSymbolTable() throws IOException {
         FileWriter writer = new FileWriter("symbolTableForTranslator.txt",true);
//        if (!isTacFileEnd) {
//
//            obj.add(id );
//            obj.add(initValueForString);
//            obj.add(type );
//            obj.add(String.valueOf(relativeAddressForSymbolTable));
//
//            arrayOfObj.add(obj);

//        if(initValueForString.length() > 0)
//        {
               writer.write(id+"\t"+initValueForString+"\t"+type+"\t"+relativeAddressForSymbolTable+"\t"+"\n");
               writer.flush();
            //}
//        else
//        {
//            writer.append(id+"\t"+type+"\t"+relativeAddressForSymbolTable+"\n");
//        }
            // writer.close();

            //update relative address according to the type of variable
            if (type.equals("int")) {
                relativeAddressForSymbolTable += 4;
            } else if (type.equals("char")) {
                relativeAddressForSymbolTable += 1;
            } else if (type.equals("str")) {
                relativeAddressForSymbolTable += 2;
            }


//        }
//        else if (isTacFileEnd) {
//            FileWriter writer = new FileWriter("symbolTableForTranslator.txt");
//            int len = arrayOfObj.size();
//            int i = 0; System.out.println(len);
//            ArrayList<ArrayList> extract = new ArrayList<>();
//            while (i != len) {
//                extract = arrayOfObj.get(i);
//                setId(String.valueOf(extract.get(0)));
//                setInitValueForString(String.valueOf(extract.get(1)));
//                setType(String.valueOf(extract.get(2)));
//                setRelativeAddr(String.valueOf(extract.get(3)));
//                writer.append(id + "\t" + initValueForString + "\t" + type + "\t" + relativeAddressForSymbolTable + "\t" + "\n");
//                writer.flush();
//                i++;
//            }
            writer.close();


        //}
    }

    public boolean isNumericLiteral(String s)
    {
        if(s.charAt(0) >= '0' && s.charAt(0) <= '9')
            return true;
        return false;
    }

    public boolean isCharacterLiteral(String s)
    {
        if(s.startsWith("'") && s.endsWith("'"))
            return true;
        return false;
    }

    public boolean isString(String s)
    {
        if(s.startsWith("\"") && s.endsWith("\""))
            return true;
        return false;
    }

    public String getOperandAddr(String var, String operand) throws IOException { // operand is str[0] i.e. destiantion variable address, which is used to setID()
        String address = "";

        if(isNumericLiteral(var))
        {
            setType("int");
            setInitValueForString(var);
            setId(operand);
            writeToTranslatorSymbolTable();
            address = getRelativeAddr(operand);
        }
        else if(isCharacterLiteral(var))
        {
            setType("char");
            setInitValueForString(var);
            setId(operand);
            writeToTranslatorSymbolTable();
            address = getRelativeAddr(operand);
        }
        else if(isString(var))
        {
            setType("str");
            setInitValueForString(var);
            setId(operand);
            writeToTranslatorSymbolTable();
            address = getRelativeAddr(operand);
        }
        else
            address = getRelativeAddr(var);

        return address;
    }


    public String getOpCodeForArithmeticOperator(String s)
    {
        if(s.equals("+"))
            return "3";
        else if(s.equals("-"))
            return "4";
        else if(s.equals("*"))
            return "5";
        else if(s.equals("/"))
            return "6";
        else if(s.equals("%"))
            return "7";
        else
            return "";
    }

    public String getOpCodeForRelationalOperator(String s)
    {
        if(s.equals("LT"))
            return "15";
        else if(s.equals("LE"))
            return "16";
        else if(s.equals("GT"))
            return "17";
        else if(s.equals("GE"))
            return "18";
        else if(s.equals("EQ"))
            return "19";
        else if(s.equals("NE"))
            return "20";
        else
            return "";
    }



    public void generateCodeByReadingTacFile (String filename) throws IOException {
    ArrayList<String> trackOfFirstOccuranceOfVar = new ArrayList<>();
    File file = new File(filename);
    Scanner reader = new Scanner(file);
    FileWriter mcWriter = new FileWriter("mc_machine_code.txt");
//    ArrayList <String> machineCode = new ArrayList<>();
//    String mc = null;

    int counter = 1;    //to track line numbers of tac
    while(reader.hasNext())
    {
        String opCode = "-1", op1 = "-1", op2 = "-1", op3 = "-1";   //default values for machine code parameters

        String temp = reader.nextLine();
        temp = temp.substring(Integer.toString(counter).length(), temp.length());
        temp = temp.trim();

        String[] str = temp.split(" ");  // split str based on whitespace


        //variable declaration statements
        if(str[0].equals("int") || str[0].equals("char") )
        {
            if(str[0].equals("int"))
                opCode = "1";
            else
                opCode = "2";

            op1 = getRelativeAddr(str[1]);
            mcWriter.write(opCode+" "+op1+" "+op2+" "+op3+"\n");
//            mc= opCode+op1+op2+op3;
//            machineCode.add(mc);
        }
        //goto statements
        else if(str[0].equals("goto"))
        {
            opCode = "11";
            op1 = str[1];
            mcWriter.write(opCode+" "+op1+" "+op2+" "+op3+"\n");
  //          mc= opCode+op1+op2+op3;
//            machineCode.add(mc);
        }
        //input statements
        else if(str[0].equals("in"))
        {
            opCode = "8";
            op1 = getRelativeAddr(str[1]);
            mcWriter.write(opCode+" "+op1+" "+op2+" "+op3+"\n");
        }
        //output statements
        else if(str[0].equals("out"))
        {
            opCode = "9";
//            if(isNumericLiteral(str[1]) || isCharacterLiteral(str[1]))
//                op1 = getOperandAddr(str[1],str[0]);
//            else
                op1 = getRelativeAddr(str[1]);
            mcWriter.write(opCode+" "+op1+" "+op2+" "+op3+"\n");
        }

        // for return statement
        else if (str[0].equals("return")){
            opCode = "14";
            op1 = getRelativeAddr(str[1]); // statement is written like "return t" (where t<-0)
            mcWriter.write(opCode+" "+op1+" "+op2+" "+op3+"\n");
        }

        //if statements
        else if(str[0].equals("if"))
        {
            opCode = getOpCodeForRelationalOperator(str[2]);
//            op1 = getOperandAddr(str[1]);
//            op2 = getOperandAddr(str[3]);
            op1 = getRelativeAddr(str[1]);
            op2 = getRelativeAddr(str[3]);
            op3 = str[str.length - 1];      // "if x LT y goto linenumber" // it is used to extract "linenumber" from this string

            mcWriter.write(opCode+" "+op1+" "+op2+" "+op3+"\n");
        }
        //Assignment statements and arithmetic operations
        else if(str.length >= 3)
        {
            if(str[1].equals("<-"))  // Assignment statement for string or char
            {

                if(str[2].startsWith("\"") || str[2].startsWith("'"))  // if it is a string or char
                {  // whenever this statement comes in tac, it follows the format with temp variable
                    // e.g  t<-"str" OR t<-'char'
                    opCode = "10";
                   // op1 = getRelativeAddr(str[0]);
                    op1 = getOperandAddr(str[2],str[0]);

                }
                else
                {
                    if(str.length > 3)  //Assignment statement for AO
                    {
                        if(str[3].equals("+") || str[3].equals("-") || str[3].equals("*") || str[3].equals("/") || str[3].equals("%"))
                        {
                            opCode = getOpCodeForArithmeticOperator(str[3]);
//                            op1 = getOperandAddr(str[2]);
//                            op2 = getOperandAddr(str[4]);
                            op1 = getRelativeAddr(str[2]);
                            op2 = getRelativeAddr(str[4]);
                            op3 = getRelativeAddr(str[0]);
                        }
                    }
                    else  // Assignment statement for statements like  t<-2, x<-t
                    {
                        opCode = "10";

//                        op1 = getOperandAddress(str[2]);
//                        op2 = getRelativeAddress(str[0]);

                        if (isNumericLiteral(str[2])) {// dealing with this case i.e. t<-2
                            op1 = getOperandAddr(str[2],str[0]);
                        }
                        else{// dealing with this case i.e. x<-t

                            op1 = getOperandAddr(str[2], str[0]); // address of t
                            if (trackOfFirstOccuranceOfVar.isEmpty() || !(trackOfFirstOccuranceOfVar.contains(str[0])))
                            {
                                /// if arrayList is empty or if var is already not added inlist, then add
                                   // variable in arraylist as well as in symboltable

                                setId(str[0]);
                                initValueForString = getInitValueForString(str[2]);
                                if (initValueForString.startsWith("\"")) // if init value is str
                                    type = "str";
                                else if (initValueForString.startsWith("\'")) // if init value is char
                                    type = "char";
                                else
                                    type = "int";
                                setType(type);
                                setInitValueForString(str[2]);
                                relativeAddr = getRelativeAddr(str[0]);    // address of destination i.e. x in this case
                                setRelativeAddr(relativeAddr);
                                writeToTranslatorSymbolTable();

                                trackOfFirstOccuranceOfVar.add(str[0]);
                            }
                            op2 = getRelativeAddr(str[0]);  ;           // address of destination i.e. x in this case

                        }
                    }
                }

                mcWriter.write(opCode+" "+op1+" "+op2+" "+op3+"\n");
            }
        }
        counter++;
    }
    mcWriter.close();
    isTacFileEnd = true;
    // now write all codes to symbol table
        System.out.println("enddddddddddddddddd");
        writeToTranslatorSymbolTable();
        System.out.println("finish...........");

    }


}