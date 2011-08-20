package com.pollworthy



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(AnswerController)
@Mock(Answer)
class AnswerControllerTests {

    void testIndex() {
        controller.index()
        assert "/answer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.answerInstanceList.size() == 0
        assert model.answerInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.answerInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.answerInstance != null
        assert view == '/answer/create'

        response.reset()

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/answer/show/1'
        assert controller.flash.message != null
        assert Answer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/answer/list'


        def answer = new Answer()

        // TODO: populate domain properties

        assert answer.save() != null

        params.id = answer.id

        def model = controller.show()

        assert model.answerInstance == answer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/answer/list'


        def answer = new Answer()

        // TODO: populate valid domain properties

        assert answer.save() != null

        params.id = answer.id

        def model = controller.edit()

        assert model.answerInstance == answer
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/answer/list'

        response.reset()


        def answer = new Answer()

        // TODO: populate valid domain properties

        assert answer.save() != null

        // test invalid parameters in update
        params.id = answer.id

        controller.update()

        assert view == "/answer/edit"
        assert model.answerInstance != null

        answer.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/answer/show/$answer.id"
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/answer/list'

        response.reset()

        def answer = new Answer()

        // TODO: populate valid domain properties
        assert answer.save() != null
        assert Answer.count() == 1

        params.id = answer.id

        controller.delete()

        assert Answer.count() == 0
        assert Answer.get(answer.id) == null
        assert response.redirectedUrl == '/answer/list'
    }
}
