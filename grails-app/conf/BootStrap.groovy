import com.pollworthy.*

class BootStrap {

    def init = { servletContext ->
         if (!User.count()) {
            def user1 = new User(name: "Don").save(failOnError: true)
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
            user1.addToComments(comment1)
            poll1.addToComments(comment1)

        }
    }
    def destroy = {
    }
}
