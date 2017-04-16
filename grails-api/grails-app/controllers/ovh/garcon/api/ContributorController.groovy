package ovh.garcon.api

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ContributorController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Contributor.list(params), model:[contributorCount: Contributor.count()]
    }

    def show(Contributor contributor) {
        respond contributor
    }

    @Transactional
    def save(Contributor contributor) {
        if (contributor == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (contributor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contributor.errors, view:'create'
            return
        }

        contributor.save flush:true

        respond contributor, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Contributor contributor) {
        if (contributor == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (contributor.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond contributor.errors, view:'edit'
            return
        }

        contributor.save flush:true

        respond contributor, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Contributor contributor) {

        if (contributor == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        contributor.delete flush:true

        render status: NO_CONTENT
    }
}
