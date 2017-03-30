package pl.edu.amu.bawsj.refactoring.c;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CTest
{
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams()
    {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams()
    {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void shouldFindNames() throws Exception
    {
        C.checkIfNameExist("Jan","Tomasz","Jan");
        Assert.assertEquals("Jan istnieje!\r\nTomasz istnieje!\r\nJan istnieje!\r\n", outContent.toString());
    }

    @Test
    public void shouldNotFindText() throws Exception
    {
        C.checkIfNameExist("testtest");
        Assert.assertEquals("testtest nie istnieje!\r\n", outContent.toString());
    }

    @Test
    public void shouldThrowNullPointerException() throws Exception
    {
        try
        {
            C.checkIfNameExist(null);
            Assert.fail();
        }catch(Exception NullPointerException)
        {
        }
    }


}