package com.pollworthy



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(CommentController)
@Mock(Comment)
class CommentControllerTests {

    void testIndex() {
        controller.index()
        assert "/comment/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.commentInstanceList.size() == 0
        assert model.commentInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.commentInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.commentInstance != null
        assert view == '/comment/create'

        response.reset()

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/comment/show/1'
        assert controller.flash.message != null
        assert Comment.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/comment/list'


        def comment = new Comment()

        // TODO: populate domain properties

        assert comment.save() != null

        params.id = comment.id

        def model = controller.show()

        assert model.commentInstance == comment
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/comment/list'


        def comment = new Comment()

        // TODO: populate valid domain properties

        assert comment.save() != null

        params.id = comment.id

        def model = controller.edit()

        assert model.commentInstance == comment
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/comment/list'

        response.reset()


        def comment = new Comment()

        // TODO: populate valid domain properties

        assert comment.save() != null

        // test invalid parameters in update
        params.id = comment.id

        controller.update()

        assert view == "/comment/edit"
        assert model.commentInstance != null

        comment.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/comment/show/$comment.id"
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/comment/list'

        response.reset()

        def comment = new Comment()

        // TODO: populate valid domain properties
        assert comment.save() != null
        assert Comment.count() == 1

        params.id = comment.id

        controller.delete()

        assert Comment.count() == 0
        assert Comment.get(comment.id) == null
        assert response.redirectedUrl == '/comment/list'
    }
}
