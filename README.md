## What's bdd4J ? ##
Bdd4J is a BDD (Behaviour Drivven Development) test runner for **JUnit** inspired on Machine.Specifications (MSpec). It means, that anywhere you can use JUnit, you can use bdd4J without any effort.

### How can i use it? ###
All you have to do is to add simple annotation to your test class.
> @RunWith(Bdd4J.class)

Of course, bdd4j requires also JUnit to work so ensure it is included in your project. Besides this, you have to have at least Java 8 to use bdd4J (because of lambdas).

### Example test ###

	@RunWith(Bdd4J.class) 
	public class When_using_bdd4J {

    Estabilish estabilishes_runs_first = () 
		-> createSomeMocksAndOtherStuff();

    Because of_doing_some_stuff = () 
		-> sut.doStuff();

    public class you_can_use_nested_classes {
		//You can create nested classes, too
        Estabilish first_nested_estabilish = 
			() -> createMocksEtc();
		
        It true_should_be_true = () 
			-> Assert.assertTrue(true);

		It same_strings_should_be_equal = () 
			-> Assert.assertEquals("same", "same");

        Cleanup first_nested_cleanup = 
			() -> cleanupSomeStuff();

    }

    Cleanup some_stuff = () -> {
			File file = new File("temp.tmp");
			file.delete();
		};

}

### Delegate types ###
#### Estabilish ####
There are invoked first. This is the place when you can set up all the stuff for your test (like mocks)
#### Because ####
This is, where the Act part executes. You should do there all the actual stuff
#### It ####
This is where you assert. It is invoked after all preceding Estabilishes and Becauses
#### Cleanup ####
If your test needs some cleanup after work, you can define it here. It will be invoked after all the it's in Cleanups scope executes.
