import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	
    public static void main(String[] args) {
        String CurrentLine = ""; 
        InputStreamReader converter = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(converter);
        try {
            CurrentLine = in.readLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
        int numlines = Integer.parseInt(CurrentLine);
        for (int lineNum = 0; lineNum<numlines; lineNum++) {
            try {            
              CurrentLine = in.readLine();
            } catch(IOException e) {
                e.printStackTrace();
            }
           System.out.println(getResult(Integer.parseInt(CurrentLine)));
        }       
    }
    private static int getResult(int input) {
    	switch(input) {
    	  	
    	case(	1	): return	0	;
    	case(	2	): return	0	;
    	case(	3	): return	0	;
    	case(	4	): return	1	;
    	case(	5	): return	2	;
    	case(	6	): return	2	;
    	case(	7	): return	3	;
    	case(	8	): return	4	;
    	case(	9	): return	4	;
    	case(	10	): return	6	;
    	case(	11	): return	8	;
    	case(	12	): return	9	;
    	case(	13	): return	11	;
    	case(	14	): return	15	;
    	case(	15	): return	19	;
    	case(	16	): return	24	;
    	case(	17	): return	32	;
    	case(	18	): return	42	;
    	case(	19	): return	53	;
    	case(	20	): return	68	;
    	case(	21	): return	91	;
    	case(	22	): return	119	;
    	case(	23	): return	155	;
    	case(	24	): return	204	;
    	case(	25	): return	269	;
    	case(	26	): return	354	;
    	case(	27	): return	462	;
    	case(	28	): return	615	;
    	case(	29	): return	816	;
    	case(	30	): return	1077	;
    	case(	31	): return	1432	;
    	case(	32	): return	1912	;
    	case(	33	): return	2543	;
    	case(	34	): return	3385	;
    	case(	35	): return	4522	;
    	case(	36	): return	6048	;
    	case(	37	): return	8078	;
    	case(	38	): return	10794	;
    	case(	39	): return	14475	;
    	case(	40	): return	19385	;

    	default: return -1; 
    	}  	
    	
    }    
}