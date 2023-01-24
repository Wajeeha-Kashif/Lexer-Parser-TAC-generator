package Parser;
////
////import javax.lang.model.type.ArrayType;
////import java.io.File;
////import java.io.FileNotFoundException;
////import java.util.ArrayList;
////import java.util.Scanner;
////
////import static java.lang.System.exit;
////import static java.lang.System.out;
////
////public class ParsingFunctions {
////   public String[] look;
////    ArrayList<String> fileArray;
////    int parser_index; // index needed in fileArray to iterate over it
////   public boolean isReadNextToken; // needed to extract one token from fileArray at a time when nextToken() is called
////    int lengthOfArray;   // length of fileArray
////    int parsetreeDepth ;      //used to track depth when printing parse tree
////    String tokenToBeCompared;
////    boolean isMatch;  // tells us if token,lexeme pair matches or not
////    boolean isCurrentToken; // tells us when do we need to call isMatch()
////
////    public ParsingFunctions(){
////         look = new String[]{};
////         fileArray = new ArrayList<String>();
////         parser_index = 0;
////         isReadNextToken = false;
////        lengthOfArray = 0;
////        parsetreeDepth = 0;
////        tokenToBeCompared = "";
////        isMatch = false;
////        isCurrentToken =  false;
////    }
////
////
////    public void Start()
////    {
////        parsetreeDepth++;
////
////        //tokenToBeCompared="COMMENT";
////        //isMatch = match(tokenToBeCompared);
////        comments();
//////        if (isCurrentToken)
//////            isMatch = match(tokenToBeCompared);
//////        isCurrentToken = false;
////        userDefinedFunction();
////        if (look[0].equals("func"))
////        {
////            outputParser(" func");
////            isMatch = match(tokenToBeCompared);
////            dataType();
////            isMatch = match(tokenToBeCompared);
////            if (look[0].equals("ID"))
////            {
////                outputParser("main");
////                id();
////
////                if (look[0].equals(":"))
////                {
////                    outputParser(":");
////                    tokenToBeCompared = "";
////                    isMatch = match(tokenToBeCompared);
////
////                    if (look[0].equals("BEGIN"))
////                    {
////                        outputParser("begin");
////                        tokenToBeCompared = "";
////                        isMatch = match(tokenToBeCompared);
////                        Statements();
////                        //return 0;
////                        tokenToBeCompared = "";
////                        isMatch = match(tokenToBeCompared);
////                        if (look[0].equals("END"))
////                        {
////                            outputParser("end");
////                            tokenToBeCompared = "";
////                            isMatch = match(tokenToBeCompared);                        }
////                        else{
////                            syntaxError();
////                        }
////
////                    }
////                    else{
////                        syntaxError();
////                    }
////
////                }
////                else{
////                    syntaxError();
////                }
////            }
////        }
////
////        Statements();
////
////
////        parsetreeDepth--;
////    }
////
////    public void Statements()
////    {
////        parsetreeDepth++;        out.println(look[0]);
////
////        if(look[0].equals("if") || look[0].equals("for") || look[0].equals("print")
////           || look[0].equals("println") || look[0].equals("in") || look[0].equals("ID")
////           || look[0].equals("call") || look[0].equals("COMMENT")) {
////
////               outputParser(" Statement");
////               Statement();
////               outputParser(" Statements");
////               Statements();
////   }
////    else
////        ;
////
////        parsetreeDepth--;
////    }
////
////    public void Statement()
////    {
////        parsetreeDepth++;
////
////        if (look[0].equals("if"))
////        {
////            outputParser("if");
////
////            tokenToBeCompared = "";
////            isMatch = match(tokenToBeCompared);
////            condition();
////
////            if (look[0].equals(":"))
////            {
////                outputParser(":");
////                tokenToBeCompared = "";
////                isMatch = match(tokenToBeCompared);
////                if (look[0].equals("BEGIN"))
////                {
////                    outputParser("begin");
////                    tokenToBeCompared = "";
////                    isMatch = match(tokenToBeCompared);
////                    Statements();
//////                    tokenToBeCompared = "";
//////                    isMatch = match(tokenToBeCompared);
////                       out.println(look[0]);
////                    if (look[0].equals("END"))
////                    {
////                        outputParser("end");
////                        tokenToBeCompared = "";
////                        isMatch = match(tokenToBeCompared);
////                        elifOrElse();
////                    }
////                    else{
////                        syntaxError();
////                    }
////                }
////                else{
////                    syntaxError();
////                }
////            }
////            else{
////                syntaxError();
////            }
////
////        }
////
////        //for print and println
////        else if(look[0].equals("print") || look[0].equals("println"))
////        {
////            outputParser(look[0]);
////
////           // outputParser(" printData");
////
////            tokenToBeCompared = "";
////            isMatch = match(tokenToBeCompared);
////            outputParser(look[0]);
////
////            tokenToBeCompared = "";
////            isMatch = match(tokenToBeCompared);
////
////            if(look[0].equals(";"))
////            {
////                outputParser(";");
////            }
////            else
////            {
////                syntaxError();
////            }
////            tokenToBeCompared = ""; // read next token
////            isMatch = match(tokenToBeCompared);
////        }
////        else if (look[0].equals("in") )
////               variableInput();
////
////        else if (look[0].equals("ID") ) {
////
////            if (look[1].trim().equals("return")){
////                 outputParser(look[1]);
////
////                tokenToBeCompared = ""; // read next token
////                isMatch = match(tokenToBeCompared);
////                value();
////
////                outputParser(look[0]); // for ;
////
////                tokenToBeCompared = ""; // read next token
////                isMatch = match(tokenToBeCompared);
////            }
////            else {
////                variable();
////                if (!isCurrentToken) // if read token is not a variable, then it's assignmentStatement
////                    assignmentStatement();
////            }
////        }
////        else if (look[0].equals("call") )
////            call();
////        else if (look[0].equals("COMMENT") )
////        comments();
////        else if (look[0].equals("for")){
////            parsetreeDepth++;
////
////            outputParser(look[0]);    // print for
////            tokenToBeCompared = ""; // read next token
////            isMatch = match(tokenToBeCompared);
////            assignmentStatement(); //done with i<-0,
////
////            tokenToBeCompared = ""; // read next token
////            isMatch = match(tokenToBeCompared);
////            condition();
////
////            if (look[0].equals("COMMA"))
////            outputParser(",");
////
////            parsetreeDepth--;
////        }
////
////        isCurrentToken=true;
////        parsetreeDepth--;
////
////    }
////
////    public void syntaxError()
////    {
////        System.out.println("SYNTAX ERROR");
////        exit(1);
////    }
////    public void Expression(){
////        term();
//////        tokenToBeCompared = "";
//////        isMatch = match(tokenToBeCompared);
////        eDash();
//////        tokenToBeCompared = "";
//////        isMatch = match(tokenToBeCompared);
////    }
////
////    public void eDash(){
////        parsetreeDepth++;
////
////       if (look[1].equals("+") ){
////           isMatch = match("+");
////           outputParser("+");
////           term();
////           eDash();
////       }
////       else if (look[1].equals("-") ){
////           isMatch=match("-");
////           outputParser("-");
////           term();
////           eDash();
////       }
////       else
////           ;
////
//////        tokenToBeCompared = "";
//////        isMatch = match(tokenToBeCompared);
////
////       parsetreeDepth--;
////
////    }
////
////    public void term(){
////        factor();
////        tDash();
//////        tokenToBeCompared = "";
//////        isMatch = match(tokenToBeCompared);
////
////    }
////
////    public void tDash(){
////        if (look[1].equals("*")){
////            isMatch=match("*");
////            outputParser("*");
////            factor();
////            tDash();
////        }
////        else if (look [1].equals("/")){
////            isMatch=match("/");
////            outputParser("/");
////            factor();
////            tDash();
////        }
////        else if (look[1].equals(" % ")){
////            isMatch=match(" % ");
////            outputParser("%");
////            factor();
////            tDash();
////        }
////        else
////            ;
////
//////        tokenToBeCompared = "";
//////        isMatch = match(tokenToBeCompared);
////    }
////
////    public void factor(){
////          if (look[0].equals("ID"))
////              id();
////          else if (look[0].equals("INT")) // INT stands for integer value/number
////              num();
////          else if (look[0].equals("(")){
////             isMatch= match("(");
////             Expression();
////              isMatch= match(")");
////          }
////
////    }
////
////    public void condition(){
////        parsetreeDepth++;
////        Expression();
////
////        outputParser(look[0]); // look[0] =RO
////        relationalOperator();
////
////        Expression();
////
////        parsetreeDepth--;
////
//////        tokenToBeCompared = "";
//////        isMatch = match(tokenToBeCompared);
////    }
////
////    public void elifOrElse(){
////        parsetreeDepth++;
////
////           if (look[0].equals("elif")){
////               outputParser("elif");
////               condition();
////               tokenToBeCompared ="";
////               isMatch = match(tokenToBeCompared);
////               outputParser(look[0]);               // for colon
////               isMatch = match(tokenToBeCompared);
////               outputParser(look[0]);                 // for begin
////               Statements();
////               isMatch = match(tokenToBeCompared);
////               outputParser(look[0]);               // for end
////               isMatch = match(tokenToBeCompared);
////               outputParser(look[0]);                 // for eliforelse
////               elifOrElse();
////           }
////           else if (look[0].equals("else")){
////               else_();
////           }
////        parsetreeDepth--;
////
////    }
////
////    public void else_ (){
////        parsetreeDepth++;
////           if (look[0].equals("else")){
////               tokenToBeCompared ="";
////               isMatch = match(tokenToBeCompared);
////               outputParser(look[0]);            // for begin
////               Statements();
////               tokenToBeCompared ="";
////               isMatch = match(tokenToBeCompared);
////               outputParser(look[0]);           // for end
////           }
////           else
////               ;
////           parsetreeDepth--;
////    }
////
////    public void printData(){
////        if (look[0].equals("ID"))
////            id();
////        else if (look[0].equals("INT")) // INT stands for integer value/number
////            num();
////        else if (look[0].equals("LIT"))
////            literal();
////        else{
////            Expression();
////        }
////    }
////
////    public void variableInput(){
////         parsetreeDepth++;
////       if (look[0].equals("in")){
////           outputParser("in");
////           tokenToBeCompared = "";
////           isMatch = match(tokenToBeCompared);
////           id();
////          // isMatch = match(tokenToBeCompared);
////           inputDelimiter();
////           tokenToBeCompared = "";
////           isMatch = match(tokenToBeCompared);
////       }
////       parsetreeDepth--;
////    }
////
////    public void inputDelimiter(){
////        parsetreeDepth++;
////        if (look[0].equals(";"))
////            outputParser(";");
////        else if (look[0].equals(",")) {
////            outputParser(",");
////            tokenToBeCompared="";
////            isMatch = match(tokenToBeCompared);
////            nextInput();
////        }
////        parsetreeDepth--;
////    }
////
////    public void nextInput(){
////         id();
//////        tokenToBeCompared="";
//////        isMatch = match(tokenToBeCompared);
////         inputDelimiter();
////    }
////
////    public void variable(){
////         id();
//////        tokenToBeCompared="";
//////        isMatch = match(tokenToBeCompared);
////
////         optionAssign();
////
//////        tokenToBeCompared="";
//////        isMatch = match(tokenToBeCompared);
////         variableDelimiter();
////      if(isCurrentToken)  // tells if it's a variable, else it's an expression
////      {
////          tokenToBeCompared = "";
////          isMatch = match(tokenToBeCompared);
////      }
////    }
////
////    public void variableDelimiter(){
////        parsetreeDepth++;
////        if (!look[0].equals("ID"))// tells us it's not an expression, if it's not an ID
////        {
////            dataType();
////            tokenToBeCompared = "";
////            isMatch = match(tokenToBeCompared);
////            if (look[0].equals(";")) {
////                outputParser(";");
//////            tokenToBeCompared="";
//////            isMatch = match(tokenToBeCompared);
////                isCurrentToken = true; // tells current token is variable
////            } else if (look[0].equals(",")) {
////
////                outputParser(",");
////                tokenToBeCompared = "";
////                isMatch = match(tokenToBeCompared);
////                nextVariable();
////                isCurrentToken = true; // tells current token is variable
////            }
////        }
////        else { // if it is not a variable, then it is an expressiion
////            // used in statement() where we are calling variable(), assignmentStatement()
////            isCurrentToken=false;
////
////        }
////       parsetreeDepth--;
////    }
////
////    public void nextVariable(){
////        id();
////        optionAssign();
////        tokenToBeCompared="";
////        isMatch = match(tokenToBeCompared);
////        variableDelimiter();
////    }
////
////    public void assignmentStatement(){
////        if(isCurrentToken) {
////            id();
////            assignmentOperator();
////            tokenToBeCompared = "";
////            isMatch = match(tokenToBeCompared);
////        }
////        value();
////        outputParser(look[0]); // use for ; or ,
////    }
////
////    public void userDefinedFunction(){
////        parsetreeDepth++;
////        if (look[0].equals("func")) {
////            outputParser("func");
////
////            tokenToBeCompared = "";
////            isMatch = match(tokenToBeCompared);
////            dataType();
////
////            tokenToBeCompared = "";
////            isMatch = match(tokenToBeCompared);
////            id();
////
////            parameter();
////
//////            tokenToBeCompared = "";
//////            isMatch = match(tokenToBeCompared);
//////            outputParser(":");
////
////            if(!isCurrentToken)// if there are no parameters, there will be :
////                outputParser(look[0]); // : stored in look[0]
////
////            tokenToBeCompared = "";
////            isMatch = match(tokenToBeCompared);
////            outputParser(look[0]); // for begin
////
////            tokenToBeCompared = "";
////            isMatch = match(tokenToBeCompared);
////            Statements();
////
////            tokenToBeCompared = "ID";
////            isMatch = match(tokenToBeCompared);
////            outputParser("return"); // its pair is (ID, return)
////
////            tokenToBeCompared = "";
////            isMatch = match(tokenToBeCompared);
////            value();
////
////            tokenToBeCompared = "";
////            isMatch = match(tokenToBeCompared);
////            outputParser(";");
////
////            tokenToBeCompared = "";
////            isMatch = match(tokenToBeCompared);
////            outputParser(look[0]);   // for end
////        }
////        else
////            ;
////
////        parsetreeDepth--;
////    }
////
////    public void parameter(){
////        parsetreeDepth++;
////        if(look[0].equals("ID")){
////            id();
////            dataType();
////            tokenToBeCompared="";
////            isMatch = match(tokenToBeCompared);
////            if(look[0].equals(":")){   // in case of 1 parameter
////                 outputParser(":");
////                 isCurrentToken = true; // tells us there are parameters present
////            }
////            else
////                nextParameter();    // in case of multiple parameters
////        }
////        else      // in case of no parameter
////            isCurrentToken=false; // tells us there are no parameters present
////
////        parsetreeDepth--;
////    }
////
////    public void nextParameter(){
////        parsetreeDepth++;
////         if(look[0].equals(",")) {
////             outputParser(",");
////
////             tokenToBeCompared = "";
////             isMatch = match(tokenToBeCompared);
////             parameter();
////         }
////
////         parsetreeDepth--;
////    }
////
////    public void call(){
////        parsetreeDepth++;
////        if(look[0].equals("call")){
////            outputParser("call");
////            id();
////            id();
////            if (look[0].equals(";"))
////            {
////                outputParser(";");
////                tokenToBeCompared="";
////                isMatch = match(tokenToBeCompared);
////            }
////        }
////        parsetreeDepth--;
////    }
////
////    public void comments(){
////        parsetreeDepth++;
////        do {
////            if (look[0].equals("COMMENT")) {
////                outputParser("COMMENT");
////
////                tokenToBeCompared = "";
////                isMatch = match(tokenToBeCompared);
////                  }
////        }
////         while(look[0].equals("COMMENT"));
////
////         isCurrentToken = false; // tells us the current token is not of the comments
////
////        parsetreeDepth--;
////    }
////
////    public void optionAssign(){
////        if (look[0].equals("AO")){
////            assignmentOperator();
////            isMatch = match("AO");
////            value();
////        }
////        else
////            ;
////    }
////
////    public void value(){
////          if(look[0].equals("STR"))
////              string();
////          else
////              Expression();
////    }
////
////    public void relationalOperator(){
////        parsetreeDepth++;
////         if(look[0].equals("RO")){
////             if(look[1].trim().equals("LT")){
////                 outputParser("LT");
////                 tokenToBeCompared = "LT";
////                 isMatch = match(tokenToBeCompared);
////             }
////            else if(look[1].trim().equals("EQ")){
////                 outputParser("EQ");
////                 tokenToBeCompared = "EQ";
////                 isMatch = match(tokenToBeCompared);
////             }
////             else if(look[1].trim().equals("LE")){
////                 outputParser("LE");
////                 tokenToBeCompared = "LE";
////                 isMatch = match(tokenToBeCompared);
////             }
////             else if(look[1].trim().equals("GT")){
////                 outputParser("GT");
////                 tokenToBeCompared = "GT";
////                 isMatch = match(tokenToBeCompared);
////             }
////             else if(look[1].trim().equals("GE")){
////                 outputParser("GE");
////                 tokenToBeCompared = "GE";
////                 isMatch = match(tokenToBeCompared);
////             }
////             else if(look[1].trim().equals("NE")){
////                 outputParser("NE");
////                 tokenToBeCompared = "NE";
////                 isMatch = match(tokenToBeCompared);
////             }
////         }
////         parsetreeDepth--;
////    }
////
////    public void assignmentOperator(){
////        parsetreeDepth++;
////
////        if (look[1].trim().equals("EQ"))
////        {
////            outputParser(look[1]);
////
////        }
////
////        parsetreeDepth--;
////
////    }
////
////    public void dataType(){
////        boolean flag=false;
////
////        outputParser("DT");
////        parsetreeDepth++;
////
////       // String [] token = new String[2];
////        //token = nextToken();
////
////       // tokenToBeCompared = "int";
////        //isMatch = match(tokenToBeCompared);
////
//////       if (!isMatch){
//////          // token = nextToken();
//////
//////           tokenToBeCompared = "char";
//////            isMatch = match(tokenToBeCompared);
//////            outputParser("char");
//////            flag= true;
//////       }
//////
//////        if (flag==true)
//////            outputParser("int");
////
////        if (look[0].equals("int")){
////            outputParser("int");
////
////        }
////        else if(look[0].equals("char")){
////            outputParser("char");
////        }
////
////
////        parsetreeDepth--;
////    }
////
////
////    public void id(){
////        parsetreeDepth++;
////
////        String character = look[1].trim(); // reading token // trim is used to remove leading white space
////        char firstCharacter = character.charAt(0);
////        int ascii = firstCharacter; // conversion from str to int to get ASCII value
////         if ((ascii >= 97 && ascii <= 122) || ascii >= 65 && ascii <= 90 )
////         {
////             outputParser(character );
////             tokenToBeCompared = character;
////             match(tokenToBeCompared);
////         }
////         parsetreeDepth--;
////    }
////
////
////    public void num(){
////        parsetreeDepth++;
////        String number =  look[1].trim(); // trim is used to remove leading white space
////        int length = number.length(); // length of number
////        int i=0; // iterator
////        char digit ;
////
////        while (i < length){
////            digit = number.charAt(i);
////            int dig = Character.getNumericValue(digit); // conversion from char to int
////            //out.println(dig);
////            if (dig >= 0  && dig <= 9 ){
////                i++;
////            }
////            else
////            {
////                syntaxError();
////                break;
////            }
////        }
////
////       if(i == length)
////       {
////           outputParser(number);
////           tokenToBeCompared = "NUM";
////           match(tokenToBeCompared);
////       }
////       parsetreeDepth--;
////    }
////
////
////
////
////    public void literal(){
////        parsetreeDepth++;
////
////        if(look[0].equals("LIT"))
////            outputParser("LIT");
////
////        parsetreeDepth--;
////    }
////
////    public void string(){
////        parsetreeDepth++;
////
////        if(look[0].equals("STR"))
////            outputParser("STR");
////
////        parsetreeDepth--;
////    }
////
////    public void readFile(String filename){
////        Scanner sc = null;
////        try {
////            File file = new File(filename); // java.io.File
////            sc = new Scanner (file);     // java.util.Scanner
////            String line;
////            ArrayList<String> cars = new ArrayList<String>();
////
////            while (sc.hasNextLine()) { // read lexer file line by line
////                line = sc.nextLine();
////                // process the line
////                String token [] = line.split(",");  // splits line of a file based on comma (i.e. (token extracted) ) and stores it in array
////                String arrayToStringConverter = token[0].toString();  // convert array to string for using substring()
////
////                int length = arrayToStringConverter.length();  // for lexeme of each token
////                // System.out.println("token array:  "+ arrayToStringConverter + "  length: " + length + "\n");
////
////                // index start with 2 i.e. excluding start bracket and space
////                fileArray.add(arrayToStringConverter.substring(2));
////                fileArray.add(line.substring(length+1,line.length()-1));
////            }
////
////            //System.out.println("token in filearray array:  "+ fileArray.get(0)+ "  "+ "\n");
////            //System.out.println("lexeme in filearray array:  "+ fileArray.get(1)+ "  "+ "\n");
////
////            lengthOfArray = fileArray.size();
////            lengthOfArray += 1;
////        }
////        catch(FileNotFoundException e)
////        {
////            e.printStackTrace();
////        }
////        finally {
////            if (sc != null)
////                sc.close();
////        }
////    }
////
//////    public void calll(){
//////        String [] tok = new String[2];
//////        tok = nextToken();
//////        System.out.println("token in filearray array:  "+ tok[0]+ "  "+ "\n");
//////        System.out.println("lexeme in filearray array:  "+ tok[1]+ "  "+ "\n");
//////        isReadNextToken = false;
//////
//////        tok = nextToken();
//////        System.out.println("NEXTtoken calling....");
//////        System.out.println("token in filearray array:  "+ tok[0]+ "  "+ "\n");
//////        System.out.println("lexeme in filearray array:  "+ tok[1]+ "  "+ "\n");
//////        isReadNextToken = false;
//////        tok = nextToken();
//////        System.out.println("token in filearray array:  "+ tok[0]+ "  "+ "\n");
//////        System.out.println("lexeme in filearray array:  "+ tok[1]+ "  "+ "\n");
//////
//////
//////    }
////
////    public String [] nextToken(){
////       String [] token = new String[2];
////        while(parser_index <= lengthOfArray  && isReadNextToken!=true){
////
////            token[0] = fileArray.get(parser_index); // store token
////            token[1] = fileArray.get(parser_index +1); // store lexeme
////            isReadNextToken = true;
////            parser_index+=2;
////           // System.out.println("token in filearray array NEXTTOKEN():  "+ token[0]+ "  "+ "\n");
////            //System.out.println("lexeme in filearray array NEXTTOKEN():  "+ token[1]+ "  "+ "\n");
////
////        }
////
////       return token;
////    }
////
////    public boolean match (String token) {
////        // in case of ID, RO, AO we're comparing lexemes
////        //if (look[0].equals("ID") || look[0].equals("AO") || look[0].equals("RO") || look[0].equals("INT")) {
////
//////            if (token.equals("ID") || token.equals("AO") || token.equals("RO") || token.equals("INT")) {
//////            if (look[1] == token) {
//////                look = nextToken();
//////                isReadNextToken = false; // only 1 token, lexeme pair will read at a time
//////            } else
//////            {
//////                System.out.println("Error... Bad Token... !!");
//////                return false;
//////            }
//////        }
////
////
////
////        // else compare tokens
////        //else {
////           // if (look[0] == token) {
////                look = nextToken();
////                isReadNextToken = false; // only 1 token, lexeme pair will read at a time
////          //  }
////            //else
////             // {
////               //    System.out.println("Error... Bad Token... !!");
////                 // return false;
////              // }
////            return true;
////       // }
////        //return true;
////    }
////
////
////
////
////    public void outputParser( String str)
////    {
////        String outputDepthPattern = "";
////      //  for(int i=0; i< parsetreeDepth;i++)
////            for(int i=0; i< 2;i++)
////
////            {
////            outputDepthPattern+="==>";
////        }
////        System.out.println(outputDepthPattern + str + "\n");
////       // writer.write(outputDepthPattern+str+'\n');
////    }
////
////
////
////}
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
////
//
//
//
//
//
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
////package parser;
//
///**
// *
// * @author Wajeeha Kashsif
// */
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.regex.Pattern;
//
//public class ParsingFunctions {
//    static boolean forflag;
//    static boolean forflag2;
//    static boolean forflag3;
//    static boolean forflag4;
//    static boolean expressionflag;
//    static boolean startFlag=true;
//    static int n = 1;                //used to track line number for three address code
//    static int t = 1;                //used to track number for temporary variable generation
//
//
//    static String generateTempVar()
//    {
//        String tempVar = "t"+t;
//        t++;
//        return tempVar;
//    }
//
//    static void writeToSymbolTable(String s) throws IOException
//    {
//        FileWriter writer = new FileWriter("parser-symboltable.txt",true);
//        writer.append(s+"\n");
//        writer.close();
//    }
//
//    static String[] nextToken(Scanner reader) throws FileNotFoundException
//    {
//        String[] token = new String[2];
//        String data = "";
//
//        if (reader.hasNext()) {
//            data = reader.nextLine(); //reading (token,lexeme) from file
//        } else {
//            SyntaxError();
//        }
//
//        String tempToken = data.substring(1, data.length() - 1); //extracting inner part
//        if (tempToken.startsWith(",")) {
//            token[0] = ",";
//            token[1] = "^";
//        } else {
//            String[] tokenList = tempToken.split(",");
//            token[0] = tokenList[0];
//            token[1] = tokenList[1];
//        }
//        return  token;
//    }
//
//    static void SyntaxError()
//    {
//        System.out.println("Syntax Error !!!!!!");
//        System.exit(0);
//    }
//
//    static void outputTac(String str) throws IOException
//    {
//        FileWriter writer = new FileWriter("tac.txt", true);
//        writer.append(n+"\t"+str+"\n");
//        n++;
//        writer.close();
//    }
//
//    static void backPatch(int line, int patch) throws IOException
//    {
//        Path path = Paths.get("tac.txt");
//        List<String> data = Files.readAllLines(path);
//        String str = data.get(line - 1) + patch;
//        data.set(line - 1, str);
//        Files.write(path, data);
//    }
//
//    static void Start(String[] token, Scanner reader) throws IOException
//    {
//        System.out.println(startFlag);
//       if(token[0].equals("func") && startFlag==true){
//            while(!token[0].equals("func"))
//            {
//                token=nextToken(reader);
//            }
//        }
//        if(token[0].equals("func") && startFlag==true)
//        {
//            token=nextToken(reader);
//            if(token[0].equals("int") )
//            {
//                token=nextToken(reader);
//                if(token[1].equals("main"))
//                {
//                    token=nextToken(reader);
//                    if(token[0].equals(":"))
//                    {
//                        token=nextToken(reader);
//                        if(token[0].equals("BEGIN"))
//                        {
//                            token=nextToken(reader);
//                            //startFlag=false;
//                        }
//                    }
//                }
//            }
//        }
//        if(startFlag==true){
//            if(token[0].equals("if") || token[0].equals("for") || token[0].equals("print")
//                    || token[0].equals("println") || token[0].equals("in") || token[0].equals("ID")
//                    || token[0].equals("call") || token[0].equals("COMMENT"))
//            {
//                System.out.println(token[0]);
//                Statements(token, reader);
//                token=nextToken(reader);
//                System.out.println(token[0] + " before start");
//                Start(token,reader);
//            }
//            else
//            {
//                SyntaxError();
//            }
//        }
//    }
//
//
//    static void Statements(String[] token, Scanner reader) throws IOException
//    {
//        Statement(token, reader);
//
//        if(reader.hasNext(Pattern.quote("(if,^)")) || reader.hasNext(Pattern.quote("(for,^)"))
//                || reader.hasNext("\\(print.*") ||
//                reader.hasNext(Pattern.quote("(println,^)")) || reader.hasNext(Pattern.quote("(in,^)"))
//                || reader.hasNext(Pattern.quote("(INT,^)")) || reader.hasNext(Pattern.quote("(CHAR,^)"))
//                || reader.hasNext("\\(ID.*") || reader.hasNext(Pattern.quote("(call,^)")) || reader.hasNext(Pattern.quote("(COMMENT,^)")))
//        {
//            token = nextToken(reader);
//            System.out.println(token[0] + " statementssss");
//            Statements(token, reader);
//        }
//        else
//        {
//        }
//    }
//
//    static void Statement(String[] token, Scanner reader) throws IOException
//    {
//        String tacStr;
//        System.out.println(token[0] + " statement");
//        //IF case
//        if(token[0].equals("if"))
//        {
//            boolean ifFlag = false;
//            if(token[0].equals("if"))
//            {
//                ifFlag = true;
//            }
//
//            token = nextToken(reader);
//
//            tacStr = "if " + Condition(token, reader) + " goto ";
//            int trackLine1 = n;
//            outputTac(tacStr);
//            tacStr = "goto ";
//            int trackLine2 = n;
//            outputTac(tacStr);
//
//            token = nextToken(reader);
//
//            if(token[0].contains(":"))
//            {
//            }
//            else
//            {
//                SyntaxError();
//            }
//
//            backPatch(trackLine1, n);
//
//            token = nextToken(reader);
//            if(token[0].contains("BEGIN"))
//            {
//                writeToSymbolTable("SCOPE START");
//            }
//            else
//            {
//                SyntaxError();
//            }
//
//            token = nextToken(reader);
//            Statements(token, reader);
//
//            tacStr = "goto ";
//            int trackLine3 = n;
//            outputTac(tacStr);
//
//            token = nextToken(reader);
//            if(token[0].contains("END"))
//            {
//                writeToSymbolTable("SCOPE END");
//            }
//            else
//            {
//                SyntaxError();
//            }
//
//            backPatch(trackLine2, n);
//
//            if(ifFlag)
//            {
//                if(reader.hasNext(Pattern.quote("(else,^)")))
//                {
//                    token = nextToken(reader);
//                }
//                ElifOrElse(token, reader);
//                backPatch(trackLine3, n);
//            }
//            else
//            {
//                backPatch(trackLine3, trackLine1);
//            }
//        }
//        //PRINT and PRINTLN case
//        else if(token[0].equals("print") || token[0].equals("println"))
//        {
//            boolean lnFlag = false;
//            if(token[0].equals("println"))
//            {
//                lnFlag = true;
//            }
//            String option = printData(token, reader);
//
//            if(option.length() == 0)
//            {
//                tacStr = "out ";
//                outputTac(tacStr);
//            }
//            else
//            {
//                tacStr = "out "+option;
//                outputTac(tacStr);
//            }
//
//            token = nextToken(reader);
//            if(token[0].contains(";"))
//            {
//            }
//            else
//            {
//                SyntaxError();
//            }
//
//            if(lnFlag)
//            {
//                tacStr = "out ";
//                outputTac(tacStr);
//            }
//        }
//
//        //FOR case
//        else if(token[0].equals("for"))
//        {
//            forflag=true;
//            token = nextToken(reader);
//
//            if(token[0].equals("ID"))
//            {
//                Variable(token, reader);
//            }
//
//            tacStr = "if " + Condition(token, reader) + " goto ";
//            int trackLine1 = n;
//            outputTac(tacStr);
//            tacStr = "goto ";
//            int trackLine2 = n;
//            outputTac(tacStr);
//
//            token = nextToken(reader);
//
//            if(token[0].equals("ID"))
//            {
//                forflag4=true;
//                AssignmentStatement(token,reader);
//            }
//
//            token=nextToken(reader);
//
//            backPatch(trackLine1, n);
//
//            if(token[0].contains("BEGIN"))
//            {
//                writeToSymbolTable("SCOPE START");
//            }
//            else
//            {
//                SyntaxError();
//            }
//
//            token = nextToken(reader);
//            Statements(token, reader);
//
//            Path fileName = Path.of("temp.txt");
//            List<String> str=Files.readAllLines(fileName);
//            for(String line: str) {
//                outputTac(line);
//            }
//
//
//            tacStr = "goto "+trackLine1;
//            int trackLine3 = n;
//            outputTac(tacStr);
//
//            token = nextToken(reader);
//            if(token[0].contains("END"))
//            {
//                writeToSymbolTable("SCOPE END");
//            }
//            else
//            {
//                SyntaxError();
//            }
//            backPatch(trackLine2, n);
//        }
//
//        //IN case
//        else if(token[0].equals("in"))
//        {
//            if(token[0].equals("in"))
//            {
//            }
//            else
//            {
//                SyntaxError();
//            }
//
//            token = nextToken(reader);
//            if(token[0].equals("ID"))
//            {
//                tacStr = "in "+token[1];
//                outputTac(tacStr);
//            }
//            else
//            {
//                SyntaxError();
//            }
//
//            token = nextToken(reader);
//            inputDelimiter(token, reader);
//        }
//
//        //Variable case
//        else if(token[0].equals("ID"))
//        {
//            Variable(token, reader);
//            System.out.println(token[0] + "ffffffff");
//        }
//        //Comments case
//        else if(token[0].equals("COMMENT"))
//        {
//        }
//        else
//        {
//            SyntaxError();
//        }
//    }
//
//    static void ElifOrElse(String[] token, Scanner reader) throws IOException
//    {
//        String tacStr;
//
//        if(token[0].equals("elif"))
//        {
//
//            token = nextToken(reader);
//
//            tacStr = "if " + Condition(token, reader) + " goto ";
//            int trackLine1 = n;
//            outputTac(tacStr);
//            tacStr = "goto ";
//            int trackLine2 = n;
//            outputTac(tacStr);
//
//            token = nextToken(reader);
//
//            if(token[0].contains(":"))
//            {
//            }
//            else
//            {
//                SyntaxError();
//            }
//
//            backPatch(trackLine1, n);
//
//            token = nextToken(reader);
//            if(token[0].contains("BEGIN"))
//            {
//                writeToSymbolTable("SCOPE START");
//            }
//            else
//            {
//                SyntaxError();
//            }
//
//            token = nextToken(reader);
//            Statements(token, reader);
//
//            tacStr = "goto ";
//            int trackLine3 = n;
//            outputTac(tacStr);
//
//            token = nextToken(reader);
//            if(token[0].contains("END"))
//            {
//                writeToSymbolTable("SCOPE END");
//            }
//            else
//            {
//                SyntaxError();
//            }
//
//            backPatch(trackLine2, n);
//
//            if(reader.hasNext(Pattern.quote("(elif,^)")) || reader.hasNext(Pattern.quote("(else,^)")))
//            {
//                token = nextToken(reader);
//            }
//            ElifOrElse(token, reader);
//
//            backPatch(trackLine3, n);
//        }
//        else
//        {
//            Else(token, reader);
//        }
//    }
//
//    static void Else(String[] token, Scanner reader) throws IOException
//    {
//        if(token[0].equals("else"))
//        {
//            token = nextToken(reader);
//            if(token[0].contains("BEGIN"))
//            {
//                writeToSymbolTable("SCOPE START");
//            }
//            else
//            {
//                SyntaxError();
//            }
//
//            token = nextToken(reader);
//            Statements(token, reader);
//
//            token = nextToken(reader);
//            if(token[0].contains("END"))
//            {
//                writeToSymbolTable("SCOPE END");
//            }
//            else
//            {
//                SyntaxError();
//            }
//        }
//        else
//        {
//        }
//    }
//
//    static String Expression(String[] token, Scanner reader) throws IOException
//    {
//        String getValue;
//        String temp = Term(token, reader);
//
//        if(reader.hasNext(Pattern.quote("(+,^)")) || reader.hasNext(Pattern.quote("(-,^)")))
//        {
//            token = nextToken(reader);
//            getValue = E_(token, reader, temp);
//        }
//        else
//        {
//            getValue = temp;
//        }
//        return getValue;
//    }
//
//    static String Term(String[] token, Scanner reader) throws IOException
//    {
//        String getValue;
//
//        String temp = Factor(token, reader);
//
//        if(reader.hasNext(Pattern.quote("(*,^)")) || reader.hasNext(Pattern.quote("(/,^)"))
//                || reader.hasNext(Pattern.quote("(%,^)")))
//        {
//            token = nextToken(reader);
//            getValue = T_(token, reader, temp);
//        }
//        else
//        {
//            getValue = temp;
//        }
//        return getValue;
//    }
//
//    static String E_(String[] token, Scanner reader, String tacVar) throws IOException
//    {
//        String getValue = "";
//        String tacStr;
//
//        String tempTac = generateTempVar();
//        getValue = tempTac;
//        tacStr = tempTac + " = " + tacVar;
//
//        if(token[0].contains("+"))
//        {
//            expressionflag=true;
//            tacStr = tacStr + " + ";
//        }
//        else if(token[0].contains("-"))
//        {
//            tacStr = tacStr + " - ";
//        }
//        else
//        {
//            SyntaxError();
//        }
//
//        token = nextToken(reader);
//
//        String temp = Term(token, reader);
//        tacStr = tacStr + temp;
//        if(forflag4==false){
//            outputTac(tacStr);
//        }
//        else if(forflag4==true){
//            FileWriter fw=new FileWriter("temp.txt");
//            fw.write(tacStr);
//            fw.close();
//            forflag4=false;
//        }
//
//        if(reader.hasNext(Pattern.quote("(+,^)")) || reader.hasNext(Pattern.quote("(-,^)")))
//        {
//            token = nextToken(reader);
//            getValue = E_(token, reader, tempTac);
//        }
//        else
//        {
//        }
//        return getValue;
//    }
//
//    static String Factor(String[] token, Scanner reader) throws IOException
//    {
//        String getValue = "";
//
//        if(token[0].equals("ID"))
//        {
//            getValue = token[1];
//        }
//        else if(token[0].equals("INT"))
//        {
//            getValue = token[1];
//        }
//        else if(token[0].equals("'('"))
//        {
//            token = nextToken(reader);
//            getValue = Expression(token, reader);
//
//            token = nextToken(reader);
//            if(token[0].equals("')'"))
//            {
//            }
//            else
//            {
//                SyntaxError();
//            }
//        }
//        else
//        {
//            SyntaxError();
//        }
//        return getValue;
//    }
//
//    static String T_(String[] token, Scanner reader, String tacVar) throws IOException
//    {
//        String getValue = "";
//        String tacStr;
//
//        String tempTac = generateTempVar();
//        getValue = tempTac;
//        tacStr = tempTac + " = " + tacVar;
//
//        if(token[0].contains("*"))
//        {
//            tacStr = tacStr + " * ";
//        }
//        else if(token[0].contains("/"))
//        {
//            tacStr = tacStr + " / ";
//        }
//        else if(token[0].contains("%"))
//        {
//            tacStr = tacStr + " % ";
//        }
//        else
//        {
//            SyntaxError();
//        }
//        token = nextToken(reader);
//
//        String temp = Factor(token, reader);
//        tacStr = tacStr + temp;
//        outputTac(tacStr);
//
//        if(reader.hasNext(Pattern.quote("(*,^)")) || reader.hasNext(Pattern.quote("(/,^)"))
//                || reader.hasNext(Pattern.quote("(%,^)")))
//        {
//            token = nextToken(reader);
//            getValue = E_(token, reader, tempTac);
//        }
//        else
//        {
//        }
//        return getValue;
//    }
//
//    static String printData(String[] token, Scanner reader) throws IOException
//    {
//        String getValue = "";
//
//        token = nextToken(reader);
//
//        if(token[0].equals("STR"))
//        {
//            getValue = token[1];
//        }
//        else if(token[0].equals("LIT"))
//        {
//            getValue = token[1];
//        }
//        else if(token[0].equals("ID"))
//        {
//            if (reader.hasNext(Pattern.quote("(+,^)")) || reader.hasNext(Pattern.quote("(-,^)"))
//                    || reader.hasNext(Pattern.quote("(*,^)")) || reader.hasNext(Pattern.quote("(/,^)")))
//            {
//                getValue = Expression(token, reader);
//
//            }
//            else
//            {
//                getValue = token[1];
//            }
//        }
//        else if(token[0].equals("INT"))
//        {
//            if (reader.hasNext(Pattern.quote("(+,^)")) || reader.hasNext(Pattern.quote("(-,^)"))
//                    || reader.hasNext(Pattern.quote("(*,^)")) || reader.hasNext(Pattern.quote("(/,^)")))
//            {
//                getValue = Expression(token, reader);
//            }
//            else
//            {
//                getValue = token[1];
//            }
//        }
//        else
//        {
//        }
//        return getValue;
//    }
//
//    static String Condition(String[] token, Scanner reader) throws IOException
//    {
//        String getValue = "";
//        String getString;
//
//        if(token[0].equals("ID") || token[0].equals("INT"))
//        {
//            getValue = Expression(token, reader);
//        }
//        else
//        {
//            SyntaxError();
//        }
//
//        getString = getValue;
//        token = nextToken(reader);
//
//        if(token[0].equals("RO"))
//        {
//            getString = getString+" "+token[1];
//
//            token = nextToken(reader);
//            if(token[0].equals("ID") || token[0].equals("INT"))
//            {
//                getValue = Expression(token, reader);
//                getString = getString+" "+getValue;
//            }
//            else
//            {
//                SyntaxError();
//            }
//        }
//        else
//        {
//            SyntaxError();
//        }
//        return getString;
//    }
//
//    static void inputDelimiter(String[] token, Scanner reader) throws IOException
//    {
//        if(token[0].equals(";"))
//        {
//        }
//        else if(token[0].equals(","))
//        {
//            token = nextToken(reader);
//            nextInput(token, reader);
//        }
//        else
//        {
//            SyntaxError();
//        }
//    }
//
//    static void nextInput(String[] token, Scanner reader) throws IOException
//    {
//        String tacStr;
//
//        if(token[0].equals("ID"))
//        {
//            tacStr = "in "+token[1];
//            outputTac(tacStr);
//        }
//        else
//        {
//            SyntaxError();
//        }
//
//        token = nextToken(reader);
//        inputDelimiter(token, reader);
//    }
//
//    static void AssignmentStatement(String[] token, Scanner reader) throws IOException
//    {
//        String tacStr;
//
//        if(token[0].equals("ID"))
//        {
//            tacStr = token[1];
//
//            token = nextToken(reader);
//
//            if(token[0].equals("<-"))
//            {
//                token = nextToken(reader);
//                String val = Value(token, reader);
//
//                token = nextToken(reader);
//                if(token[0].equals(";"))
//                {
//                    tacStr = tacStr + " = " + val;
//                    outputTac(tacStr);
//                }
//                else if(forflag3==true && token[0].equals(":"))
//                {
//                    Path fileName = Path.of("temp.txt");
//                    String str = Files.readString(fileName);
//                    str = str +'\n';
//                    FileWriter fw=new FileWriter("temp.txt");
//                    tacStr = str + tacStr + " = " + val;
//                    fw.write(tacStr);
//                    fw.close();
//                }
//                else
//                {
//                    SyntaxError();
//                }
//            }
//            else
//            {
//                SyntaxError();
//            }
//        }
//    }
//
//    static String Value(String[] token, Scanner reader) throws IOException
//    {
//        String getValue = "";
//
//        if(reader.hasNext(Pattern.quote("(+,^)")) || reader.hasNext(Pattern.quote("(-,^)"))
//                || reader.hasNext(Pattern.quote("(*,^)")) || reader.hasNext(Pattern.quote("(/,^)"))
//                || reader.hasNext(Pattern.quote("(%,^)")) || forflag2==true)
//        {
//            forflag2=false;
//            forflag3=true;
//            getValue = Expression(token, reader);
//        }
//        else if(token[0].equals("ID"))
//        {
//            getValue = token[1];
//        }
//        else if(token[0].equals("INT"))
//        {
//            getValue = token[1];
//        }
//        else if(token[0].equals("LIT"))
//        {
//            getValue = token[1];
//        }
//        else
//        {
//            SyntaxError();
//        }
//        return getValue;
//    }
//
//    static void DT(String[] token, Scanner reader) throws IOException
//    {
//        if(token[0].equals("INT"))
//        {
//        }
//        else if(token[0].equals("CHAR"))
//        {
//        }
//        else
//        {
//            SyntaxError();
//        }
//    }
//
//    static void optionAssign(String[] token, Scanner reader) throws IOException
//    {
//        if(token[0].equals("ID"))
//        {
//            String tacStr = token[1];
//
//            System.out.println(token[0]);
//
//            token = nextToken(reader);
//            if(token[0].equals("<-"))
//            {
//                token = nextToken(reader);
//                String temp = Value(token, reader);
//
//                if(temp.length()>0)
//                {
//                    tacStr = tacStr + " <- " + temp;
//                    outputTac(tacStr);
//                }
//            }
//            else
//            {
//                SyntaxError();
//            }
//        }
//    }
//
//    static void Variable(String[] token, Scanner reader) throws IOException
//    {
//        String tacStr;
//        String id="";
//
//        if(token[0].equals("ID"))
//        {
//            id=token[1];
//        }
//        else
//        {
//            SyntaxError();
//        }
//
//        String tok = token[1];
//         if(reader.hasNext(Pattern.quote("(<-,^)")))
//       {
//            optionAssign(token, reader);
//        }
//
//        forflag2=false;
//        token = nextToken(reader);
//        writeToSymbolTable(tok + "  " + token[0]);
//        token = nextToken(reader);
//        VariableDelimiter(token, reader, id);
//    }
//
//    static void VariableDelimiter(String[] token, Scanner reader, String id) throws IOException
//    {
//        System.out.println(token[0] +  " var del");
//        boolean isDelimeter=false;
//
//        if (token[0].equals(";"))
//            isDelimeter = true;
//
//        String tacStr;
//        String type="";
//
//        if(forflag2==true)
//        {
//            tacStr = "if " + Condition(token, reader) + " goto ";
//            int trackLine1 = n;
//            outputTac(tacStr);
//            tacStr = "goto ";
//            int trackLine2 = n;
//            outputTac(tacStr);
//            forflag2=false;
//
//        }
//        if(forflag==true)
//        {
//            writeToSymbolTable(id+"\t");
//            tacStr = " " + id;
//            //outputTac(tacStr);
//            forflag=false;
//            forflag2=true;
//        }
//
//        else if(token[0].equals(","))
//        {
//            token = nextToken(reader);
//            nextVariable(token, reader);
//        }
//
//        else{
//            if(expressionflag==false && isDelimeter==false){
//                DT(token, reader);
//                type = token[0];
//
//                token = nextToken(reader);
//            }
//
//            if(token[0].equals(";"))
//            {
//                writeToSymbolTable(id+"\t"+type);
//                token = nextToken(reader);
//                System.out.println(token[0]);
//            }
//        }
//    }
//
//    static void nextVariable(String[] token, Scanner reader) throws IOException
//    {
//        String tacStr;
//        String type=" type not known";
//
//        if(token[0].equals("ID"))
//        {
//            writeToSymbolTable(token[1]+"\t"+type);
//            tacStr = type.toLowerCase() + " " + token[1];
//            outputTac(tacStr);
//
//            if(reader.hasNext(Pattern.quote("(<-,^)")))
//            {
//                optionAssign(token, reader);
//            }
//
//            token = nextToken(reader);
//            VariableDelimiter(token, reader, type);
//        }
//        else
//        {
//            SyntaxError();
//        }
//    }
//
//    public void startParsingAndTranslating() throws IOException
//    {
//        File tokenFile = new File("tokens.txt"); //file for input from lexer
//
//        //creating files, writers and readers
//        Scanner tokenReader = new Scanner(tokenFile);
//        FileWriter writer = new FileWriter("parser-symboltable.txt");
//        writer.close();
//        writer = new FileWriter("tac.txt");
//        writer.close();
//
//        String[] token; //Token variable
//
//        token = nextToken(tokenReader);
//
//        writeToSymbolTable("SCOPE START");
//
//        Start(token,tokenReader);
//
//        writeToSymbolTable("SCOPE END");
//
//        //closing readers and writers
//        tokenReader.close();
//
//        System.out.println("\nParser execution successful! Check parser-symboltable.txt file.");
//        System.out.println("\nThree Address Code successfully generated! Check tac.txt file.");
//    }
//}
//





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package parser;

/**
 *
 * @author Wajeeha Kashsif
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ParsingFunctions {

    public String[] look;
    static ArrayList<String> fileArray;
    static int parser_index; // index needed in fileArray to iterate over it
    static public boolean isReadNextToken; // needed to extract one token from fileArray at a time when nextToken() is called
    static int lengthOfArray;   // length of fileArray
    static String tokenToBeCompared;
    static boolean isMatch;  // tells us if token,lexeme pair matches or not
    static boolean isCurrentToken; // tells us when do we need to call isMatch()
    static boolean forflag;
    static boolean forflag2;
    static boolean forflag3;
    static boolean expressionflag;

    static int line = 0;                //used to track line number when parsing
    static int tokenCounter = 0;        //used to update 'line'
    static int parsetreeDepth = 0;      //used to track depth when printing parse tree
    static String filename;             //name/path of code file given by user
    static int n = 1;                   //used to track line number for three address code
    static int t = 1;                   //used to track number for temporary variable generation
    static int relativeAddress = 0;     //used to track relative address for translator's symbol table

    public void setFilename(String file)
    {
        filename = file;
    }

    public int getTempVarCount()
    {
        return t;
    }

    static String generateTempVar()
    {
        String tempVar = "t"+t;
        t++;
        return tempVar;
    }

    static void writeToSymbolTable(String s) throws IOException
    {
        FileWriter writer = new FileWriter("parser-symboltable.txt",true);
        writer.append(s+"\n");
        writer.close();
    }

    static String[] nextToken(Scanner reader) throws FileNotFoundException
    {
        String[] token = new String[2];
        String data = "";

        if (reader.hasNext()) {
            data = reader.nextLine(); //reading (token,lexeme) from file
        } else {
            SyntaxError();
        }


        tokenCounter++;

        String tempToken = data.substring(1, data.length() - 1); //extracting inner part
        if (tempToken.startsWith("','")) {
            token[0] = "','";
            token[1] = "^";
        } else {
            String[] tokenList = tempToken.split(",");
            token[0] = tokenList[0];
            token[1] = tokenList[1];
        }
        //System.out.println(token[0]+"   "+token[1]);

        line=30;

        return  token;
    }

    static void SyntaxError() throws FileNotFoundException
    {
        File tempF = new File("error.txt");
        Scanner tempR = new Scanner(tempF);

        String tempStr="";
        for(int i=0;i<=line;i++)
        {
            tempStr = tempR.nextLine();
        }
        tempStr=tempStr.trim();
        System.out.println("Syntax error at line "+(line+1));
        System.out.println((line+1)+"\t"+tempStr);
        System.out.println("Review your code and try again");
        tempR.close();
        System.exit(0);
        // System.out.println("Syntax Error!!!!!!");
    }

    static void outputParser(FileWriter writer, String str) throws IOException
    {
        String outputDepthPattern = "";
        for(int i=0; i<parsetreeDepth;i++)
        {
            outputDepthPattern+="==>";
        }
        writer.write(outputDepthPattern+str+"\n");
    }

    static void outputTac(String str) throws IOException
    {
        FileWriter writer = new FileWriter("tac.txt", true);
        writer.append(n+"\t"+str+"\n");
        n++;
        writer.close();
    }

    static void backPatch(int line, int patch) throws IOException
    {
        Path path = Paths.get("tac.txt");
        List<String> data = Files.readAllLines(path);
        String str = data.get(line - 1) + patch;
        data.set(line - 1, str);
        Files.write(path, data);
    }

    static void Start(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr;
        System.out.println(token[0]);
        if(token[0].equals("if") || token[0].equals("for") || token[0].equals("print")
                || token[0].equals("println") || token[0].equals("in") || token[0].equals("ID")
                || token[0].equals("call") || token[0].equals("COMMENT"))
        {
            System.out.println(token[0]);
            attachStr = " Statements";
            outputParser(writer, attachStr);
            System.out.println(token[0]);
            Statements(token, writer, reader);

        }
        else
        {
            SyntaxError();
        }

        parsetreeDepth--;
    }

    static void Statements(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        System.out.println("Statements call ho gia hai");

        String attachStr = " Statement";
        outputParser(writer, attachStr);
        Statement(token, writer, reader);

        attachStr = " Statements";
        outputParser(writer, attachStr);

        System.out.println(token[0]);
        System.out.println(token[1]);
        System.out.println("Wapis Stements main aata hai?");
        //if(token[0].equals("print") && token[1].equals("^")){
        //System.out.println(reader.next());
        //}


        if(reader.hasNext(Pattern.quote("(if,^)")) || reader.hasNext(Pattern.quote("(for,^)"))
                || reader.hasNext(Pattern.quote("(print,^)")) ||
                reader.hasNext(Pattern.quote("(println,^)")) || reader.hasNext(Pattern.quote("(in,^)"))
                || reader.hasNext(Pattern.quote("(INT,^)")) || reader.hasNext(Pattern.quote("(CHAR,^)"))
                || reader.hasNext("\\(ID.*") || reader.hasNext("(call,^)") || reader.hasNext("(COMMENT,^)"))
        {
            System.out.println("########################");
            token = nextToken(reader);
            System.out.println(token[0]+"  "+token[1]);
            Statements(token, writer, reader);
        }
        else
        {
            parsetreeDepth++;
            attachStr = " ^";
            outputParser(writer, attachStr);
            parsetreeDepth--;
        }

        parsetreeDepth--;
    }

    static void Statement(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr, tacStr;

        System.out.println("Statement call ho gia hai");
        System.out.println(token[0]);

        //IF case
        if(token[0].equals("if"))
        {
            boolean ifFlag = false;
            if(token[0].equals("if"))
            {
                ifFlag = true;
            }
            attachStr = " "+token[0];
            outputParser(writer, attachStr);

            //tacStr = token[0].toLowerCase();

            token = nextToken(reader);

            attachStr = " Condition";
            outputParser(writer, attachStr);
            tacStr = "if " + Condition(token, writer, reader) + " goto ";
            int trackLine1 = n;
            outputTac(tacStr);
            tacStr = "goto ";
            int trackLine2 = n;
            outputTac(tacStr);

            token = nextToken(reader);

            if(token[0].contains(":"))
            {
                attachStr = " :";
                outputParser(writer, attachStr);
            }
            else
            {
                SyntaxError();
            }

            backPatch(trackLine1, n);

            token = nextToken(reader);
            if(token[0].contains("BEGIN"))
            {
                attachStr = " BEGIN";
                outputParser(writer, attachStr);
                writeToSymbolTable("SCOPE START");
            }
            else
            {
                SyntaxError();
            }

            token = nextToken(reader);
            attachStr = " Statements";
            outputParser(writer, attachStr);
            Statements(token, writer, reader);

            tacStr = "goto ";
            int trackLine3 = n;
            outputTac(tacStr);

            token = nextToken(reader);
            if(token[0].contains("END"))
            {
                attachStr = " END";
                outputParser(writer, attachStr);
                writeToSymbolTable("SCOPE END");
            }
            else
            {
                SyntaxError();
            }

            backPatch(trackLine2, n);

            if(ifFlag)
            {
                if(reader.hasNext(Pattern.quote("(else,^)")))
                {
                    token = nextToken(reader);
                }
                attachStr = " ElifOrElse";
                outputParser(writer, attachStr);
                ElifOrElse(token, writer, reader);
                backPatch(trackLine3, n);
            }
            else
            {
                backPatch(trackLine3, trackLine1);
            }
        }
        //PRINT and PRINTLN case
        else if(token[0].equals("print") || token[0].equals("println"))
        {
            boolean lnFlag = false;
            if(token[0].equals("println"))
            {
                lnFlag = true;
            }
            attachStr = " "+token[0];
            outputParser(writer, attachStr);

            attachStr = " printData";
            outputParser(writer, attachStr);
            String option = printData(token, writer, reader);

            if(option.length() == 0)
            {
                String tempVar = generateTempVar();
                tacStr = tempVar + " = \"\\n\"";
                outputTac(tacStr);
                tacStr = "out "+tempVar;
                outputTac(tacStr);

                //writeToTranslatorSymbolTable(tempVar, "STR", "\"\\n\"");
            }
            else
            {
                System.out.println("Statemant main if print case main");
                if (option.startsWith("\""))
                    tacStr = "out t"+(t-1);
                else
                    tacStr = "out "+option;
                outputTac(tacStr);
            }


            token = nextToken(reader);
            System.out.println(token[0]);
            if(token[0].contains(";"))
            {
                System.out.println("if true");
                attachStr = " ;";
                outputParser(writer, attachStr);

            }
            else
            {
                SyntaxError();
            }

            if(lnFlag)
            {
                String tempVar = generateTempVar();
                tacStr = tempVar + " = \"\\n\"";
                outputTac(tacStr);
                tacStr = "out "+tempVar;
                outputTac(tacStr);

                //writeToTranslatorSymbolTable(tempVar, "STR", "\"\\n\"");
            }
        }

        //FOR case
        else if(token[0].equals("for"))
        {
            forflag=true;
            System.out.println("for case main aaya");
            attachStr = " "+token[0];
            outputParser(writer, attachStr);

            //tacStr = token[0].toLowerCase();

            token = nextToken(reader);

            if(token[0].equals("ID"))
            {
                attachStr = " Variable";
                outputParser(writer, attachStr);
                Variable(token, writer, reader);
            }

            //token=nextToken(reader);
            System.out.println("yahan tk ho gia/?????????????");

            attachStr = " Condition";
            outputParser(writer, attachStr);
            tacStr = "if " + Condition(token, writer, reader) + " goto ";
            int trackLine1 = n;
            outputTac(tacStr);
            tacStr = "goto ";
            int trackLine2 = n;
            outputTac(tacStr);

            token = nextToken(reader);

            if(token[0].equals("ID"))
            {
                // forflag=true;
                System.out.println("!!!!!!!!!!!!!!!!!");
                attachStr = " AssignmentStatement";
                System.out.println(token[0]);
                System.out.println(token[1]);
                outputParser(writer, attachStr);
                AssignmentStatement(token, writer,reader);
                //  Variable(token, writer, reader);

            }

            token=nextToken(reader);

            System.out.println("IDHER HAI K NHIII");
            System.out.println(token[0]);
            System.out.println(token[1]);

           /* attachStr = " Condition";
            outputParser(writer, attachStr);
            tacStr = "if " + Condition(token, writer, reader) + " goto ";
            int trackLine4 = n;
            outputTac(tacStr);
            tacStr = "goto ";
            int trackLine5 = n;
            outputTac(tacStr);

            token = nextToken(reader);

            System.out.println("yahan : aana chahiye||||||");
            System.out.println(token[0]);
         System.out.println(token[1]);*/

            /*if(token[0].contains(":"))
            {
                attachStr = " :";
                outputParser(writer, attachStr);
            }
            else
            {
                SyntaxError();
            }*/

            backPatch(trackLine1, n);

            //token = nextToken(reader);
            if(token[0].contains("BEGIN"))
            {
                attachStr = " BEGIN";
                outputParser(writer, attachStr);
                writeToSymbolTable("SCOPE START");
            }
            else
            {
                SyntaxError();
            }

            token = nextToken(reader);
            attachStr = " Statements";
            outputParser(writer, attachStr);
            Statements(token, writer, reader);

            tacStr = "goto ";
            int trackLine3 = n;
            outputTac(tacStr);

            token = nextToken(reader);
            if(token[0].contains("END"))
            {
                attachStr = " END";
                outputParser(writer, attachStr);
                writeToSymbolTable("SCOPE END");
            }
            else
            {
                SyntaxError();
            }

            backPatch(trackLine2, n);
        }

        //IN case
        else if(token[0].equals("in"))
        {
            System.out.println("IN case in Statement");
            attachStr = " "+token[0];
            outputParser(writer, attachStr);
            //token = nextToken(reader);
            System.out.println(token[0]);
            if(token[0].equals("in"))
            {
                attachStr = " in";
                outputParser(writer, attachStr);
                in(token, writer, reader);
                System.out.println("returned");
                // token = nextToken(reader);
            }
            else
            {
                SyntaxError();
            }

            token = nextToken(reader);
            System.out.println(token[0]);
            if(token[0].equals("ID"))
            {
                //checkID(token[1]);
                attachStr = " ID("+token[1]+")";
                outputParser(writer, attachStr);

                tacStr = "in "+token[1];
                outputTac(tacStr);
            }
            else
            {
                SyntaxError();
            }

            token = nextToken(reader);
            attachStr = " inputDelimiter";
            outputParser(writer, attachStr);
            inputDelimiter(token, writer, reader);
        }

        //Variable case
        else if(token[0].equals("ID"))
        {
            System.out.println("if main aa gia hai");
            attachStr = " Variable";
            outputParser(writer, attachStr);
            Variable(token, writer, reader);
            System.out.println("Wapis Statement mian aaya hai??");
        }
        //Comments case
        else if(token[0].equals("COMMENT"))
        {
            attachStr = " "+token[0];
            outputParser(writer, attachStr);
        }
        else
        {
            SyntaxError();
        }

        parsetreeDepth--;
    }

    static void ElifOrElse(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr, tacStr;

        if(token[0].equals("elif"))
        {
            attachStr = " elif";
            outputParser(writer, attachStr);

            token = nextToken(reader);

            attachStr = " Condition";
            outputParser(writer, attachStr);
            tacStr = "if " + Condition(token, writer, reader) + " goto ";
            int trackLine1 = n;
            outputTac(tacStr);
            tacStr = "goto ";
            int trackLine2 = n;
            outputTac(tacStr);

            token = nextToken(reader);

            if(token[0].contains(":"))
            {
                attachStr = " :";
                outputParser(writer, attachStr);
            }
            else
            {
                SyntaxError();
            }

            backPatch(trackLine1, n);

            token = nextToken(reader);
            if(token[0].contains("BEGIN"))
            {
                attachStr = " BEGIN";
                outputParser(writer, attachStr);
                writeToSymbolTable("SCOPE START");
            }
            else
            {
                SyntaxError();
            }

            token = nextToken(reader);
            attachStr = " Statements";
            outputParser(writer, attachStr);
            Statements(token, writer, reader);

            tacStr = "goto ";
            int trackLine3 = n;
            outputTac(tacStr);

            token = nextToken(reader);
            if(token[0].contains("END"))
            {
                attachStr = " END";
                outputParser(writer, attachStr);
                writeToSymbolTable("SCOPE END");
            }
            else
            {
                SyntaxError();
            }

            backPatch(trackLine2, n);

            if(reader.hasNext(Pattern.quote("(elif,^)")) || reader.hasNext(Pattern.quote("(else,^)")))
            {
                token = nextToken(reader);
            }
            attachStr = " ElifOrElse";
            outputParser(writer, attachStr);
            ElifOrElse(token, writer, reader);

            backPatch(trackLine3, n);
        }
        else
        {
            attachStr = " Else";
            outputParser(writer, attachStr);
            Else(token, writer, reader);
        }

        parsetreeDepth--;
    }

    static void Else(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr;

        if(token[0].equals("else"))
        {
            attachStr = " else";
            outputParser(writer, attachStr);

            token = nextToken(reader);
            if(token[0].contains("BEGIN"))
            {
                attachStr = " BEGIN";
                outputParser(writer, attachStr);
                writeToSymbolTable("SCOPE START");
            }
            else
            {
                SyntaxError();
            }

            token = nextToken(reader);
            attachStr = " Statements";
            outputParser(writer, attachStr);
            Statements(token, writer, reader);

            token = nextToken(reader);
            if(token[0].contains("END"))
            {
                attachStr = " END";
                outputParser(writer, attachStr);
                writeToSymbolTable("SCOPE END");
            }
            else
            {
                SyntaxError();
            }
        }
        else
        {
            attachStr = " ^";
            outputParser(writer, attachStr);
        }

        parsetreeDepth--;
    }

    static String Expression(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String getValue;

        System.out.println("Expression called");
        System.out.println(token[0]);
        System.out.println(token[1]);

        String attachStr = " Term";
        outputParser(writer, attachStr);

        String temp = Term(token, writer, reader);

        attachStr = " E_";
        outputParser(writer, attachStr);

        if(reader.hasNext(Pattern.quote("(+,^)")) || reader.hasNext(Pattern.quote("(-,^)")))
        {
            token = nextToken(reader);
            getValue = E_(token, writer, reader, temp);
        }
        else
        {
            parsetreeDepth++;
            attachStr = " ^";
            outputParser(writer, attachStr);
            parsetreeDepth--;
            getValue = temp;
        }

        parsetreeDepth--;
        System.out.println("Returning from Expression");
        return getValue;
    }

    static String Term(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String getValue;

        String attachStr = " Factor";
        outputParser(writer, attachStr);

        String temp = Factor(token, writer, reader);

        attachStr = " R_";
        outputParser(writer, attachStr);

        if(reader.hasNext(Pattern.quote("(*,^)")) || reader.hasNext(Pattern.quote("(/,^)"))
                || reader.hasNext(Pattern.quote("(%,^)")))
        {
            token = nextToken(reader);
            getValue = T_(token, writer, reader, temp);
        }
        else
        {
            parsetreeDepth++;
            attachStr = " ^";
            outputParser(writer, attachStr);
            parsetreeDepth--;
            getValue = temp;
        }

        parsetreeDepth--;
        return getValue;
    }

    static String E_(String[] token, FileWriter writer, Scanner reader, String tacVar) throws IOException
    {
        System.out.println("E_ CALLLLEDDDDD");
        parsetreeDepth++;
        String getValue = "";
        String attachStr, tacStr;

        String tempTac = generateTempVar();
       /* String type = getTempVarDataType();
        if(type.length() > 1)
        {
            //writeToTranslatorSymbolTable(tempTac, type, "");
        }
        else
        {
            writeToTranslatorSymbolTable(tempTac, checkTempVarDataType(tacVar), "");
        }*/
        getValue = tempTac;
        tacStr = tempTac + " = " + tacVar;

        if(token[0].contains("+"))
        {
            System.out.println("YAHHHOOOOOOOOOOOOOOOOO");
            expressionflag=true;
            attachStr = " +";
            outputParser(writer, attachStr);
            tacStr = tacStr + " + ";
        }
        else if(token[0].contains("-"))
        {
            attachStr = " -";
            outputParser(writer, attachStr);
            tacStr = tacStr + " - ";
        }
        else
        {
            SyntaxError();
        }

        attachStr = " Term";
        outputParser(writer, attachStr);
        token = nextToken(reader);

        String temp = Term(token, writer, reader);
        tacStr = tacStr + temp;
        outputTac(tacStr);

        attachStr = " E_";
        outputParser(writer, attachStr);

        if(reader.hasNext(Pattern.quote("(+,^)")) || reader.hasNext(Pattern.quote("(-,^)")))
        {
            token = nextToken(reader);
            getValue = E_(token, writer, reader, tempTac);
        }
        else
        {
            parsetreeDepth++;
            attachStr = " ^";
            outputParser(writer, attachStr);
            parsetreeDepth--;
        }

        parsetreeDepth--;
        return getValue;
    }

    static String Factor(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr;
        String getValue = "";

        if(token[0].equals("ID"))
        {
            System.out.println("factor main ID main aa gia hai");
            //checkID(token[1]);
            attachStr = " ID("+token[1]+")";
            outputParser(writer, attachStr);
            getValue = token[1];
        }
        else if(token[0].equals("INT"))
        {
            attachStr = " INT("+token[1]+")";
            outputParser(writer, attachStr);
            getValue = token[1];
        }
        else if(token[0].equals("'('"))
        {
            attachStr = " (";
            outputParser(writer, attachStr);

            token = nextToken(reader);
            attachStr = " Expression";
            outputParser(writer, attachStr);
            getValue = Expression(token, writer, reader);

            token = nextToken(reader);
            if(token[0].equals("')'"))
            {
                attachStr = " )";
                outputParser(writer, attachStr);
            }
            else
            {
                SyntaxError();
            }
        }
        else
        {
            SyntaxError();
        }

        parsetreeDepth--;
        return getValue;
    }

    static String T_(String[] token, FileWriter writer, Scanner reader, String tacVar) throws IOException
    {
        parsetreeDepth++;
        String getValue = "";
        String attachStr, tacStr;

        String tempTac = generateTempVar();
        /*String type = getTempVarDataType();
        if(type.length() > 1)
        {
            writeToTranslatorSymbolTable(tempTac, type, "");
        }
        else
        {
            writeToTranslatorSymbolTable(tempTac, checkTempVarDataType(tacVar), "");
        }*/
        getValue = tempTac;
        tacStr = tempTac + " = " + tacVar;

        if(token[0].contains("*"))
        {
            attachStr = " *";
            outputParser(writer, attachStr);
            tacStr = tacStr + " * ";
        }
        else if(token[0].contains("/"))
        {
            attachStr = " /";
            outputParser(writer, attachStr);
            tacStr = tacStr + " / ";
        }
        else if(token[0].contains("%"))
        {
            attachStr = " %";
            outputParser(writer, attachStr);
            tacStr = tacStr + " % ";
        }
        else
        {
            SyntaxError();
        }

        attachStr = " Factor";
        outputParser(writer, attachStr);
        token = nextToken(reader);

        String temp = Factor(token, writer, reader);
        tacStr = tacStr + temp;
        outputTac(tacStr);

        attachStr = " T_";
        outputParser(writer, attachStr);

        if(reader.hasNext(Pattern.quote("(*,^)")) || reader.hasNext(Pattern.quote("(/,^)"))
                || reader.hasNext(Pattern.quote("(%,^)")))
        {
            token = nextToken(reader);
            getValue = E_(token, writer, reader, tempTac);
        }
        else
        {
            parsetreeDepth++;
            attachStr = " ^";
            outputParser(writer, attachStr);
            parsetreeDepth--;
        }

        parsetreeDepth--;
        return getValue;
    }

    static String printData(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr;
        String getValue = "";

        token = nextToken(reader);


        System.out.println("printData function");
        System.out.println(token[0]);
        System.out.println(token[1]);
        if(token[0].equals("STR"))
        {
            attachStr = " STR("+token[1]+")";
            outputParser(writer, attachStr);
            getValue = token[1];

            String temp = generateTempVar();
            String tacStr = temp + " = " + token[1];
            outputTac(tacStr);
            // writeToTranslatorSymbolTable(temp, "STR", token[1]);
        }
        else if(token[0].equals("LIT"))
        {
            attachStr = " LIT("+token[1]+")";
            outputParser(writer, attachStr);
            getValue = token[1];
        }
        else if(token[0].equals("ID"))
        {
            if (reader.hasNext(Pattern.quote("(+,^)")) || reader.hasNext(Pattern.quote("(-,^)"))
                    || reader.hasNext(Pattern.quote("(*,^)")) || reader.hasNext(Pattern.quote("(/,^)")))
            {
                attachStr = " Expression";
                outputParser(writer, attachStr);
                getValue = Expression(token, writer, reader);

            }
            else
            {
                // checkID(token[1]);
                attachStr = " ID(" + token[1] + ")";
                outputParser(writer, attachStr);
                getValue = token[1];
            }
        }
        else if(token[0].equals("INT"))
        {
            if (reader.hasNext(Pattern.quote("(+,^)")) || reader.hasNext(Pattern.quote("(-,^)"))
                    || reader.hasNext(Pattern.quote("(*,^)")) || reader.hasNext(Pattern.quote("(/,^)")))
            {
                attachStr = " Expression";
                outputParser(writer, attachStr);
                getValue = Expression(token, writer, reader);

            }
            else
            {
                attachStr = " INT(" + token[1] + ")";
                outputParser(writer, attachStr);
                getValue = token[1];
            }
        }
        else
        {
            attachStr = " ^";
            outputParser(writer, attachStr);
        }

        parsetreeDepth--;
        return getValue;
    }

    static void relOp(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr = "RO("+token[1]+")";
        outputParser(writer, attachStr);
        parsetreeDepth--;
    }

    static String Condition(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr;
        String getValue = "";
        String getString;

        System.out.println("Condition function");
        System.out.println(token[0]);
        System.out.println(token[1]);
        if(token[0].equals("ID") || token[0].equals("INT"))
        {
            attachStr = " Expression";
            outputParser(writer, attachStr);
            getValue = Expression(token, writer, reader);
        }
        else
        {
            SyntaxError();
        }

        getString = getValue;
        token = nextToken(reader);

        if(token[0].equals("RO"))
        {
            attachStr = " RO";
            outputParser(writer, attachStr);
            relOp(token, writer, reader);
            getString = getString+" "+token[1];

            token = nextToken(reader);
            if(token[0].equals("ID") || token[0].equals("INT"))
            {
                attachStr = " Expression";
                outputParser(writer, attachStr);
                getValue = Expression(token, writer, reader);
                getString = getString+" "+getValue;
            }
            else
            {
                SyntaxError();
            }
        }
        else
        {
            SyntaxError();
        }

        parsetreeDepth--;
        return getString;
    }

    static void in(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        System.out.println("in function called");
        parsetreeDepth++;
        String attachStr = " ->";
        outputParser(writer, attachStr);
        parsetreeDepth--;
    }

    static void inputDelimiter(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr;

        System.out.println("input delimeter called");
        if(token[0].equals(";"))
        {
            attachStr = " ;";
            outputParser(writer, attachStr);
        }
        else if(token[0].equals("','"))
        {
            attachStr = " ,";
            outputParser(writer, attachStr);

            token = nextToken(reader);
            attachStr = " nextInput";
            outputParser(writer, attachStr);
            nextInput(token, writer, reader);
        }
        else
        {
            SyntaxError();
        }

        parsetreeDepth--;
    }

    static void nextInput(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr, tacStr;

        if(token[0].equals("ID"))
        {
            //checkID(token[1]);
            attachStr = " ID("+token[1]+")";
            outputParser(writer, attachStr);
            tacStr = "in "+token[1];
            outputTac(tacStr);
        }
        else
        {
            SyntaxError();
        }

        token = nextToken(reader);
        attachStr = " inputDelimiter";
        outputParser(writer, attachStr);
        inputDelimiter(token, writer, reader);

        parsetreeDepth--;
    }

    static void AssignmentStatement(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr, tacStr;

        if(token[0].equals("ID"))
        {
            //checkID(token[1]);
            attachStr = " ID("+token[1]+")";
            outputParser(writer, attachStr);
            tacStr = token[1];

            token = nextToken(reader);

            System.out.println("Assignmnet statemnet");
            System.out.println(token[0]);
            System.out.println(token[1]);

            if(token[0].equals("<-"))
            {
                attachStr = " <-";
                outputParser(writer, attachStr);

                token = nextToken(reader);
                attachStr = " Value";
                outputParser(writer, attachStr);
                String val = Value(token, writer, reader);

                tacStr = tacStr + " = " + val;
                outputTac(tacStr);

                token = nextToken(reader);
                if(token[0].equals(";"))
                {
                    attachStr = " ;";
                    outputParser(writer, attachStr);
                }
                else if(forflag3==true && token[0].equals(":"))
                {
                    attachStr = " :";
                    outputParser(writer, attachStr);
                }
                else
                {
                    SyntaxError();
                }
            }
            else
            {
                SyntaxError();
            }
        }

        parsetreeDepth--;
    }

    static String Value(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr;
        String getValue = "";

        System.out.println("Value called");
        System.out.println(forflag);
        System.out.println(forflag2);
         /*if(forflag2==true){
         //reader.next();
         //reader.next();
         token=nextToken(reader);
         token=nextToken(reader);
         System.out.println(token[0]);
         System.out.println(token[1]);
         forflag2=false;
         }*/


        if(reader.hasNext(Pattern.quote("(+,^)")) || reader.hasNext(Pattern.quote("(-,^)"))
                || reader.hasNext(Pattern.quote("(*,^)")) || reader.hasNext(Pattern.quote("(/,^)"))
                || reader.hasNext(Pattern.quote("(%,^)")) || forflag2==true)
        {
            forflag2=false;
            forflag3=true;
            System.out.println("loop true hui ya nhi?");
            attachStr = " Expression";
            outputParser(writer, attachStr);
            getValue = Expression(token, writer, reader);
        }
        else if(token[0].equals("ID"))
        {
            //checkID(token[1]);
            attachStr = " ID(" + token[1] + ")";
            outputParser(writer, attachStr);
            getValue = token[1];
        }
        else if(token[0].equals("INT"))
        {
            attachStr = " INT(" + token[1] + ")";
            outputParser(writer, attachStr);
            getValue = token[1];
        }
        else if(token[0].equals("LIT"))
        {
            attachStr = " LIT(" + token[1] + ")";
            outputParser(writer, attachStr);
            getValue = token[1];
        }
        else
        {
            SyntaxError();
        }

        parsetreeDepth--;
        System.out.println("Returnung from Value??");
        return getValue;
    }

    static void DT(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr;

        if(token[0].equals("INT"))
        {
            attachStr = " int";
            outputParser(writer, attachStr);
        }
        else if(token[0].equals("CHAR"))
        {
            attachStr = " char";
            outputParser(writer, attachStr);
        }
        else
        {
            SyntaxError();
        }

        parsetreeDepth--;
    }

    static void optionAssign(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr;

        if(token[0].equals("ID"))
        {
            System.out.println("OptionAssign main aa gia");
            String tacStr = token[1];


            token = nextToken(reader);
            System.out.println(token[0]);
            if(token[0].equals("<-"))
            {
                attachStr = " <-";
                outputParser(writer, attachStr);

                token = nextToken(reader);
                System.out.println(token[0]);
                attachStr = " Value";
                outputParser(writer, attachStr);
                String temp = Value(token, writer, reader);

                if(temp.length()>0)
                {
                    System.out.println(temp);
                    tacStr = tacStr + " <- " + temp;
                    System.out.println(tacStr);
                    outputTac(tacStr);
                }
            }
            else
            {
                SyntaxError();
            }
        }

        parsetreeDepth--;
    }

    static void Variable(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr, tacStr;
        String id="";


        //token = nextToken(reader);
        if(token[0].equals("ID"))
        {
            System.out.println("Variable call ho gia hai");
            attachStr = " ID("+token[1]+")";
            id=token[1];
            System.out.println(token[1]);
            outputParser(writer, attachStr);
            ////
            //writeToSymbolTable(token[1]+"\t"+type);

            // writeToTranslatorSymbolTable(token[1], type, "");
            // tacStr = type.toLowerCase() + " " + token[1];
            //outputTac(tacStr);
        }
        else
        {
            SyntaxError();
        }

        if(reader.hasNext(Pattern.quote("(<-,^)")))
        {
            System.out.println("reader.hasNext(Pattern.quote");
            optionAssign(token, writer, reader);
        }

        System.out.println("Wapis variable main aa gia hai");
        forflag2=false;
        token = nextToken(reader);
        System.out.println(token[0]);
        attachStr = " VariableDelimiter";
        outputParser(writer, attachStr);
        VariableDelimiter(token, writer, reader, id);

        parsetreeDepth--;
    }

    static void VariableDelimiter(String[] token, FileWriter writer, Scanner reader, String id) throws IOException
    {
        parsetreeDepth++;
        String attachStr,tacStr;
        String type="";

        if(forflag2==true)
        {
            attachStr = " Condition";
            outputParser(writer, attachStr);
            tacStr = "if " + Condition(token, writer, reader) + " goto ";
            int trackLine1 = n;
            outputTac(tacStr);
            tacStr = "goto ";
            int trackLine2 = n;
            outputTac(tacStr);
            forflag2=false;

        }
        if(forflag==true)
        {
            writeToSymbolTable(id+"\t");
            tacStr = " " + id;
            outputTac(tacStr);
            attachStr = " ;";
            outputParser(writer, attachStr);
            forflag=false;
            forflag2=true;
            System.out.println("yahan aana chahiye");
        }

        else if(token[0].equals(","))
        {
            System.out.println("yahan nhi aana chahiye");
            attachStr = " ,";
            outputParser(writer, attachStr);
            token = nextToken(reader);
            attachStr = " nextVariable";
            outputParser(writer, attachStr);
            nextVariable(token, writer, reader);
        }

        else{
            if(expressionflag==false){
                System.out.println("Variable Delimete main aa gia");
                attachStr = " DT";
                outputParser(writer, attachStr);
                DT(token, writer, reader);
                type = token[0];

                token = nextToken(reader);
                System.out.println(token[0]);}

            if(token[0].equals(";"))
            {
                writeToSymbolTable(id+"\t"+type);
                tacStr = type.toLowerCase() + " " + id;
                outputTac(tacStr);
                attachStr = " ;";
                outputParser(writer, attachStr);
            }
        }

        /*else
        {
            SyntaxError();
        }*/

        System.out.println("returnnnnedddddddddddd");
        parsetreeDepth--;
    }

    static void nextVariable(String[] token, FileWriter writer, Scanner reader) throws IOException
    {
        parsetreeDepth++;
        String attachStr, tacStr;
        String type=" type not known";

        if(token[0].equals("ID"))
        {
            attachStr = " ID(" + token[1] + ")";
            outputParser(writer, attachStr);
            ////
            writeToSymbolTable(token[1]+"\t"+type);
            tacStr = type.toLowerCase() + " " + token[1];
            outputTac(tacStr);

            if(reader.hasNext(Pattern.quote("(<-,^)")))
            {
                optionAssign(token, writer, reader);
            }

            token = nextToken(reader);
            attachStr = " VariableDelimiter";
            outputParser(writer, attachStr);
            VariableDelimiter(token, writer, reader, type);
        }
        else
        {
            SyntaxError();
        }

        parsetreeDepth--;
    }

    public void startParsingAndTranslating() throws IOException
    {
        File tokenFile = new File("tokens.txt"); //file for input from lexer

        //creating files, writers and readers
        Scanner tokenReader = new Scanner(tokenFile);
        FileWriter parserWriter = new FileWriter("parsetree.txt");
        FileWriter writer = new FileWriter("parser-symboltable.txt");
        writer.close();
        writer = new FileWriter("tac.txt");
        writer.close();

        String[] token; //Token variable

        token = nextToken(tokenReader);

        parserWriter.write("Start\n");
        writeToSymbolTable("SCOPE START");

        Start(token,parserWriter,tokenReader);

        writeToSymbolTable("SCOPE END");

        //closing readers and writers
        tokenReader.close();
        parserWriter.close();

        System.out.println("\nParser execution successful! Check parser-symboltable.txt file.");
        System.out.println("\nThree Address Code successfully generated! Check tac.txt file.");
    }
}
