package com.pollworthy



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(QuestionController)
@Mock(Question)
class QuestionControllerTests {

    void testIndex() {
        controller.index()
        assert "/question/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.questionInstanceList.size() == 0
        assert model.questionInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.questionInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.questionInstance != null
        assert view == '/question/create'

        response.reset()

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/question/show/1'
        assert controller.flash.message != null
        assert Question.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/question/list'


        def question = new Question()

        // TODO: populate domain properties

        assert question.save() != null

        params.id = question.id

        def model = controller.show()

        assert model.questionInstance == question
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/question/list'


        def question = new Question()

        // TODO: populate valid domain properties

        assert question.save() != null

        params.id = question.id

        def model = controller.edit()

        assert model.questionInstance == question
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/question/list'

        response.reset()


        def question = new Question()

        // TODO: populate valid domain properties

        assert question.save() != null

        // test invalid parameters in update
        params.id = question.id

        controller.update()

        assert view == "/question/edit"
        assert model.questionInstance != null

        question.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/question/show/$question.id"
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/question/list'

        response.reset()

        def question = new Question()

        // TODO: populate valid domain properties
        assert question.save() != null
        assert Question.count() == 1

        params.id = question.id

        controller.delete()

        assert Question.count() == 0
        assert Question.get(question.id) == null
        assert response.redirectedUrl == '/question/list'
    }
}
