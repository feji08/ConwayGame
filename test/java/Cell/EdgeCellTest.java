package Cell;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class EdgeCellTest {

    @Test
    void findNeighbors() throws NoSuchFieldException {
        EdgeCell testCell = new EdgeCell(9,8);
        ArrayList<int[]> actualResult = new ArrayList<>();
        actualResult = testCell.findNeighbors(10,10);
        ArrayList<int[]> expectedRes = new ArrayList<>();
        Collections.addAll(expectedRes,new int[]{9,9}, new int[]{9,7},new int[]{8,9},
                new int[]{8,8},new int[]{8,7});
        assertEquals(expectedRes.size(),actualResult.size());
        for(int i= 0;i<expectedRes.size();i++) {
            boolean flag = false;
            for (int j = 0; j < expectedRes.size(); j++) {
                if (expectedRes.get(i)[0] == actualResult.get(j)[0] && expectedRes.get(i)[1] == actualResult.get(j)[1]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                throw new NoSuchFieldException();
            }
        }
    }
}