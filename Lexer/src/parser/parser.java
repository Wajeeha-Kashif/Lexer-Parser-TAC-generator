package Parser;

import Lexer.*;
import java.io.IOException;

import static java.lang.System.exit;

public class parser {
    public Lexer obj;


    public void callParser( String filename) throws IOException {
         obj.readFile(filename);
        // it will read tokens.txt file and proceed with each token
    }

}
