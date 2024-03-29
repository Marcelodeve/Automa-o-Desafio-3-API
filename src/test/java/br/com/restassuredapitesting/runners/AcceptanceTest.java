package br.com.restassuredapitesting.runners;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(br.com.restassuredapitesting.suites.AcceptanceTest.class)
@Suite.SuiteClasses({
        AcceptanceTest.class,
})

public class AcceptanceTest {
}
