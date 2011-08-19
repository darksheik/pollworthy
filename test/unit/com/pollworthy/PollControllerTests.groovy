package com.pollworthy



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(PollController)
@Mock(Poll)
class PollControllerTests {

    void testIndex() {
        controller.index()
        assert "/poll/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pollInstanceList.size() == 0
        assert model.pollInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.pollInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.pollInstance != null
        assert view == '/poll/create'

        response.reset()

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/poll/show/1'
        assert controller.flash.message != null
        assert Poll.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/poll/list'


        def poll = new Poll()

        // TODO: populate domain properties

        assert poll.save() != null

        params.id = poll.id

        def model = controller.show()

        assert model.pollInstance == poll
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/poll/list'


        def poll = new Poll()

        // TODO: populate valid domain properties

        assert poll.save() != null

        params.id = poll.id

        def model = controller.edit()

        assert model.pollInstance == poll
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/poll/list'

        response.reset()


        def poll = new Poll()

        // TODO: populate valid domain properties

        assert poll.save() != null

        // test invalid parameters in update
        params.id = poll.id

        controller.update()

        assert view == "/poll/edit"
        assert model.pollInstance != null

        poll.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/poll/show/$poll.id"
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/poll/list'

        response.reset()

        def poll = new Poll()

        // TODO: populate valid domain properties
        assert poll.save() != null
        assert Poll.count() == 1

        params.id = poll.id

        controller.delete()

        assert Poll.count() == 0
        assert Poll.get(poll.id) == null
        assert response.redirectedUrl == '/poll/list'
    }
}
