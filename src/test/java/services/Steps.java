package services;

import io.cucumber.java.en.Given;

public class Steps extends SampleTest {
    Steps steps = new Steps();
    @Given("Test detail")
    public void click() { steps.getPetDetail();}


}
