package com.pollworthy

import org.springframework.dao.DataIntegrityViolationException

class PollController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [pollInstanceList: Poll.list(params), pollInstanceTotal: Poll.count()]
    }

    def create() {
        [pollInstance: new Poll(params)]
    }

    def save() {
        def pollInstance = new Poll(params)
        if (!pollInstance.save(flush: true)) {
            render(view: "create", model: [pollInstance: pollInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'poll.label', default: 'Poll'), pollInstance.id])
        redirect(action: "show", id: pollInstance.id)
    }

    def show() {
        def pollInstance = Poll.get(params.id)
        if (!pollInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'poll.label', default: 'Poll'), params.id])
            redirect(action: "list")
            return
        }

        [pollInstance: pollInstance]
    }

    def edit() {
        def pollInstance = Poll.get(params.id)
        if (!pollInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'poll.label', default: 'Poll'), params.id])
            redirect(action: "list")
            return
        }

        [pollInstance: pollInstance]
    }

    def update() {
        def pollInstance = Poll.get(params.id)
        if (!pollInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'poll.label', default: 'Poll'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (pollInstance.version > version) {
                pollInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'poll.label', default: 'Poll')] as Object[],
                          "Another user has updated this Poll while you were editing")
                render(view: "edit", model: [pollInstance: pollInstance])
                return
            }
        }

        pollInstance.properties = params

        if (!pollInstance.save(flush: true)) {
            render(view: "edit", model: [pollInstance: pollInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'poll.label', default: 'Poll'), pollInstance.id])
        redirect(action: "show", id: pollInstance.id)
    }

    def delete() {
        def pollInstance = Poll.get(params.id)
        if (!pollInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'poll.label', default: 'Poll'), params.id])
            redirect(action: "list")
            return
        }

        try {
            pollInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'poll.label', default: 'Poll'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'poll.label', default: 'Poll'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
