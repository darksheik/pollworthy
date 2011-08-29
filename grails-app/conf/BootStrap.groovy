import com.pollworthy.*

class BootStrap {

    def init = { servletContext ->
         if (!User.count()) {

            def user1 = new User(name: "Don",email:"don@don.com",password:"password").save(failOnError: true)
            def user2 = new User(name: "Griffin",email:"griffin@griffin.com",password:"password").save(failOnError: true)

            def poll1 = new Poll(name: "A Lot About Chickens",user:user1)
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


            def comment1 = new Comment(content: "This poll is really demented.",user:user2,poll:poll1).save(failOnError: true)

            def poll2 = new Poll(name: "Griffin's Quiz",user:user2)
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

            def comment2 = new Comment(content: "This poll is pretty good.",user:user1,poll:poll2).save(failOnError: true)
            def comment3 = new Comment(content: "Hey, thanks!",user:user2,poll:poll2).save(failOnError: true)


        }
    }
    def destroy = {
    }
}
