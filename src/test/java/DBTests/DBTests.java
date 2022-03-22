package DBTests;

import dbHandler.DBController;
import org.junit.Assert;
import org.junit.Test;
import rssParser.News;

import java.util.ArrayList;

public class DBTests {
    @Test
    public void GetSubsTest(){

        Assert.assertEquals(ArrayList.class, DBController.getSubs().getClass());
        ArrayList<String> arrayList =DBController.getSubs();
        String id = arrayList.get(0);
        Assert.assertEquals("337850598",id);
    }
    @Test
    public void GetSubTest(){


    }
}
