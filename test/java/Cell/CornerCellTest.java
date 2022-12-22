package Cell;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CornerCellTest {

    @Test
    void findNeighbors() {
        CornerCell testCell = new CornerCell(4,4);
        ArrayList<int[]> actualResult = new ArrayList<>();
        actualResult = testCell.findNeighbors(5,5);
        ArrayList<int[]> expectedRes = new ArrayList<>();
        Collections.addAll(expectedRes,new int[]{3,3}, new int[]{3,4},new int[]{4,3});
        assertEquals(expectedRes.size(),actualResult.size());
        for(int i= 0;i<expectedRes.size();i++){
            boolean flag = false;
            for(int j=0;j< expectedRes.size();j++){
                if(expectedRes.get(i)[0]== actualResult.get(j)[0] && expectedRes.get(i)[1]== actualResult.get(j)[1] ){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                throw new IndexOutOfBoundsException();
            }
        }
    }
}