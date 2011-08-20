import com.pollworthy.*

class BootStrap {

    def init = { servletContext ->
         if (!User.count()) {
            def user1 = new User(name: "Don").save(failOnError: true)
            def user2 = new User(name: "Griffin").save(failOnError: true)

            def poll1 = new Poll(name: "A Lot About Chickens")
                  .addToQuestions(new Question(text: "How many chickens would you like to have?")
                      .addToAnswers(new Answer(text: "5"))
                      .addToAnswers(new Answer(text: "10"))
                      .addToAnswers(new Answer(text: "400"))
                      )
                  .addToQuestions(new Question(text: "Where would you keep your chickens?")
                      .addToAnswers(new Answer(text: "In the coop"))
                      .addToAnswers(new Answer(text: "In the family room"))
                      .addToAnswers(new Answer(text: "In the attic"))
                      )
                  .addToQuestions(new Question(text: "Where do you see your chickens in five years?")
                      .addToAnswers(new Answer(text: "Still in my loving care and tutelage"))
                      .addToAnswers(new Answer(text: "On my plate"))
                      ).save(failOnError: true)
               
            def comment1 = new Comment(content: "This poll is really demented.").save(failOnError: true)
            user2.addToComments(comment1)
            poll1.addToComments(comment1)


            def poll2 = new Poll(name: "Griffin's Quiz")
                  .addToQuestions(new Question(text: "What's your sign?")
                      .addToAnswers(new Answer(text: "Cancer"))
                      .addToAnswers(new Answer(text: "Taurus"))
                      .addToAnswers(new Answer(text: "Stop"))
                      )
                  .addToQuestions(new Question(text: "What's your favorite food?")
                      .addToAnswers(new Answer(text: "Escargot"))
                      .addToAnswers(new Answer(text: "Foie Gras"))
                      .addToAnswers(new Answer(text: "Cheeseburgers"))
                      ).save(failOnError: true)
               
            def comment2 = new Comment(content: "This poll is pretty good.").save(failOnError: true)
            def comment3 = new Comment(content: "Hey, thanks!").save(failOnError: true)
            user1.addToComments(comment2)
            poll2.addToComments(comment2)
            user2.addToComments(comment3)
            poll2.addToComments(comment3)

        }
    }
    def destroy = {
    }
}
