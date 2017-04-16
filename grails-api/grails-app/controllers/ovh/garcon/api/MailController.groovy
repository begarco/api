package ovh.garcon.api

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MailController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Mail.list(params), model:[mailCount: Mail.count()]
    }

    def show(Mail mail) {
        respond mail
    }

    @Transactional
    def save(Mail mail) {
        if (mail == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (mail.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond mail.errors, view:'create'
            return
        }

        mail.save flush:true

        respond mail, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(Mail mail) {
        if (mail == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (mail.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond mail.errors, view:'edit'
            return
        }

        mail.save flush:true

        respond mail, [status: OK, view:"show"]
    }

    @Transactional
    def delete(Mail mail) {

        if (mail == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        mail.delete flush:true

        render status: NO_CONTENT
    }
}
