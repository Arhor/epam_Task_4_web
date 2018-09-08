package test.by.epam.task4.service.factory;

import org.testng.annotations.Test;

import by.epam.task4.exception.ParserNotPresentedException;
import by.epam.task4.service.factory.MedicinsBuilderFactory;
import by.epam.task4.service.parsing.MedicinsAbstractBuilder;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class MedicinsBuilderFactoryTest {

    private static final String UNDEFINED_PARSER = "undefined parser";

    private MedicinsBuilderFactory factory;

    @Test(dataProvider = "parsers")
    public void getBuilderTest(String name) throws ParserNotPresentedException {
        MedicinsAbstractBuilder builder = factory.getBuilder(name);
        String actual = builder.getClass()
                               .getSimpleName()
                               .replaceAll("(Medicins)|(Builder)", "")
                               .toUpperCase();
        String expected = name;
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ParserNotPresentedException.class)
    public void getBuilderExceptionTest() throws ParserNotPresentedException {
        factory.getBuilder(UNDEFINED_PARSER);
    }

    @DataProvider(name = "parsers")
    public Object[][] createParsers() {
        return new Object[][] {
                new Object[] { "SAX" },
                new Object[] { "DOM" },
                new Object[] { "STAX" },
        };
    }

    @BeforeClass
    public void beforeClass() {
        factory = new MedicinsBuilderFactory();
    }

    @AfterClass
    public void afterClass() {
        factory = null;
    }
}
