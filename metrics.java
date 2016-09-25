/** CS474
 * Ying liu
 * Program computes the Halstead complexity measures
 * Created by Yliu222 on 9/17/14.
 */

import java.util.*;
import java.math.*;

public class Halstead {
    class HalsteadResults {
        // initializing variables
        int length = 0;
        int vocab = 0;
        double volume = 0;
        double difficulty = 0;
        double idifficulty = 0;
        double effort = 0;
        double mentalDisc = 0;
        HalsteadResults(){}

        HalsteadResults(int length, int vocab, double volume, double difficulty, double idifficulty, double effort, double mentalDisc) {
            this.length       = length      ;
            this.vocab        = vocab       ;
            this.volume       = volume      ;
            this.difficulty   = difficulty  ;
            this.idifficulty  = idifficulty ;
            this.effort       = effort      ;
            this.mentalDisc   = mentalDisc  ;
        }
        public Map<String,Number> toMap() {
            Map<String,Number> map = new TreeMap<String,Number>();
            map.put( "length",      new Integer( length ) );
            map.put( "vocab",       new Integer( vocab ));
            map.put( "volume",      new Double( volume ));
            map.put( "difficulty",  new Double( difficulty ));
            map.put( "idifficulty", new Double( idifficulty ));
            map.put( "effort",      new Double( effort ));
            map.put( "mentalDisc",  new Double( mentalDisc ));
            return map;
        }
    }

    // sum functions
    int sum( Collection<Integer> c ) {
        int result = 0;
        for (int i : c)
            result += i;
        return result;
    }

    // declare and count the sum of operands, operators,unique operators and uniqueoperands
    public HalsteadResults calculateMetrics( Map<String,Integer> operatorMap,  Map<String,Integer> operandMap ) {
        int totalOperands        = sum( operatorMap.values() );
        int totalOperators       = sum(  operandMap.values() );
        int totalUniqueOperators = operatorMap.keySet().size();
        int totalUniqueOperands  =  operandMap.keySet().size();


        int n1 = totalUniqueOperators;
        int n2 = totalUniqueOperands;
        int tN1 = totalOperators;
        int tN2 = totalOperands;

        int programLength = tN1 + tN2;
        int programVocab  = n1 + n2;
        int N = programLength; //pg_length
        int n = programVocab;
        double volume =  N * (Math.log(n) / Math.log(2)); // find volume
        double V = volume;
        double N2divn2 = (0 == n2)? 0 : (tN2 / n2);
        double difficulty = (n1 / 2) * N2divn2 ; // difficulty level
        double D = difficulty;
        double Di = (0 == D) ? 0 : ( 1.0 / D);
        double effort = D * V;

        double mentalDisc = (0.0 == Di) ? 0 : (V / Di) ; //find discriminations: E^ = V / L
        double E = effort;

        return new HalsteadResults( programLength, n, V, D, Di, E, mentalDisc);
    }

